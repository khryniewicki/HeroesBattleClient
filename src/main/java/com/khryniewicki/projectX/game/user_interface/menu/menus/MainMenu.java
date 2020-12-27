package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ServerState;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.observers.Subject;
import com.khryniewicki.projectX.graphics.Colors;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.*;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
@Getter
@Setter
public class MainMenu extends AbstractMenu {

    private static final MainMenu instance = new MainMenu();
    private final HeroesInstances heroesInstances;
    private final TextureMenuFactory textureMenuFactory;
    private final ButtonsFactory buttonsFactory;
    private final WebsocketScheduler websocketScheduler;
    private MenuSymbol noHero;
    private MenuSymbol playersDescriptionLabel;
    private MenuSymbol playersBarLabel;
    private volatile ServerState state;
    private ServerState currentState;
    private boolean subscribed;

    public static MainMenu getInstance() {
        return instance;
    }

    private MainMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textureMenuFactory = TextureMenuFactory.getInstance();
        buttonsFactory = ButtonsFactory.getInstance();
        websocketScheduler = WebsocketScheduler.getInstance();
        start();
        addObserver();
        subscribePlayersInGame();
        websocketScheduler.observePlayers();
    }

    private void addObserver() {
        subject = new Subject();
        subject.addPropertyChangeListener(MultiplayerController.getMultiplayerInstance());
    }

    private void subscribePlayersInGame() {
        if (!subscribed) {
            websocketScheduler.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    String propertyName = evt.getPropertyName();
                    if (propertyName.equals("sever")) {
                        ServerState serverState = (ServerState) evt.getNewValue();
//                        log.info("{}", serverState);
                        if (serverState == ServerState.JOIN_GAME) {
                            websocketScheduler.removePropertyChangeListener(this);
                            setSubscribed(false);
                        } else {
                            state = serverState;
                        }
                    }
                }
            });
            setSubscribed(true);
        }
    }

    @Override
    public void init() {
        setButtons(buttonsFactory.getListWithMainMenuButtons());
        setVolatileImages(textureMenuFactory.getListWithTextMainMenuSymbols());
        playersBarLabel = PLAYERS_BAR_LABEL;
        playersDescriptionLabel = PLAYERS_DESCRIPTION_LABEL;
        setPermanentImages(new ArrayList<>(Arrays.asList(playersBarLabel, playersDescriptionLabel, BG_ANIMATION, MENU_IMAGE)));
    }

    @Override
    public void execute() {
        prepare();
        loop();
    }

    @Override
    public void prepare() {
        log.info("PREPARE");
        addEventClick();
        currentState = null;
        setCurrentView(MenuCard.MAIN_MENU);
        subscribePlayersInGame();
        begin();
    }

    @Override
    public void insideLoop() {
        do {
            update();
            windowsShouldClose();
        } while (running);
    }

    @Override
    public void update() {
        if (Objects.nonNull(state) && !state.equals(currentState) && currentView.equals(MenuCard.MAIN_MENU)) {
            log.info("STATE {}", currentState);
            log.info("VIEW {}", currentView);
            updateLabel(playersDescriptionLabel, state);
            updateLabelDescription(playersBarLabel, state);
            currentState = state;
            render();
        }
        glfwPollEvents();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String buttonName = (String) evt.getNewValue();
        showMenu(buttonName);
    }

    private void showMenu(String buttonName) {
        //disable all messages
        volatileImages.forEach(s -> toggleImage(s, true));

        switch (buttonName) {
            case "ChooseCharacter":
                runMenu(CharacterMenu.getInstance(), MenuCard.CHARACTER_MENU);
                break;
            case "ControlSettings":
                runMenu(ControlSettingsMenu.getInstance(), MenuCard.CONTROL_SETTINGS);
                break;
            case "QuitGame":
                stop();
                finish_game();
                break;
            case "Start":
                if (state.equals(ServerState.SERVER_OFFLINE)) {
                    showMessage(TEXT_SERVER_OFFLINE);
                } else if (state.equals(ServerState.TWO_PLAYERS)) {
                    showMessage(TEXT_ROOM_IS_FULL);
                } else {
                    if (Objects.nonNull(heroesInstances.getHero())) {
                        stop();
                        subject.setNews(MultiplayerState.CONNECT);
                    } else {
                        showMessage(TEXT_NO_HERO);
                    }
                }
                break;
        }
    }

    public void showMessage(MenuSymbol symbol) {
        toggleImage(symbol, false);
    }

    public void updateLabel(MenuSymbol symbol, ServerState state) {
        List<MenuSymbol> menuSymbols = permanentImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        Color color = Colors.BRIGHT_YELLOW;
                        if (state.equals(ServerState.SERVER_OFFLINE)) {
                            color = Colors.BRIGHT_RED;
                        }
                        symbol.setTexture(TextFactory.textInPlayersMenuToImage(state.getTitle(), color));
                    }
                })
                .collect(Collectors.toList());
        setPermanentImages(menuSymbols);
    }

    public void updateLabelDescription(MenuSymbol symbol, ServerState state) {
        List<MenuSymbol> menuSymbols = permanentImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        switch (state) {
                            case NO_PLAYERS:
                                symbol.setTexture(SERVER_EMPTY);
                                break;
                            case ONE_PLAYER:
                                symbol.setTexture(SERVER_HALF_FULL);
                                break;
                            case TWO_PLAYERS:
                                symbol.setTexture(SERVER_FULL);
                                break;
                            case SERVER_OFFLINE:
                                symbol.setTexture(SERVER_OFFLINE);
                                break;
                        }
                    }
                })
                .collect(Collectors.toList());
        setPermanentImages(menuSymbols);
    }

}
