package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.ServerState;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
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
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
@Getter
@Setter
public class MainMenu extends MenuImp {

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
        observeGame();
    }

    private void observeGame() {
        subscribePlayersInGame();
        websocketScheduler.observerPlayers();
    }

    private void subscribePlayersInGame() {
        websocketScheduler.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if (propertyName.equals("playersOnline")) {
                    ServerState serverState = (ServerState) evt.getNewValue();
                    if (serverState == ServerState.JOIN_GAME) {
                        websocketScheduler.removePropertyChangeListener(this);
                    } else {
                        state = serverState;
                    }
                }
            }
        });
    }

    @Override
    public void init() {
        initButtons();
        initVolatileMessages();
        initPermanentImages();
    }

    private void initButtons() {
        setButtons(buttonsFactory.getListWithMainMenuButtons());
    }

    private void initVolatileMessages() {
        setVolatileImages(textureMenuFactory.getListWithTextMainMenuSymbols());
    }

    private void initPermanentImages() {
        playersBarLabel = PLAYERS_BAR_LABEL;
        playersDescriptionLabel = PLAYERS_DESCRIPTION_LABEL;
        setPermanentImages(new ArrayList<>(Arrays.asList(playersBarLabel, playersDescriptionLabel, BG_ANIMATION, MENU_IMAGE)));
    }

    @Override
    public void execute() {
        currentState = null;
        addEventClick();
        begin();
        loop();
    }

    @Override
    public void insideLoop() {
        do {
            update();
            windowsShouldClose();
        } while (running);
        terminateIfWindowShutDown();
    }

    @Override
    public void update() {
        if (Objects.nonNull(state) && !state.equals(currentState)) {
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
        disableAllMessages();

        switch (buttonName) {
            case "ChooseCharacter":
                runMenu(CharacterMenu.getInstance());
                break;
            case "ControlSettings":
                runMenu(ControlSettingsMenu.getInstance());
                break;
            case "QuitGame":
                stop();
                setFinishGame(true);
                break;
            case "Start":
                if (state.equals(ServerState.SERVER_OFFLINE)) {
                    showMessage(TEXT_SERVER_OFFLINE);
                } else if (state.equals(ServerState.TWO_PLAYERS)) {
                    showMessage(TEXT_ROOM_IS_FULL);
                } else {
                    if (Objects.nonNull(heroesInstances.getHero())) {
                        stop();
                    } else {
                        showMessage(TEXT_NO_HERO);
                    }
                }
                break;
        }
    }



    @Override
    public void stop() {
        super.stop();
        log.info("stop loop main menu");
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
                                symbol.setTexture(BAR_EMPTY);
                                break;
                            case ONE_PLAYER:
                                symbol.setTexture(BAR_HALF);
                                break;
                            case TWO_PLAYERS:
                                symbol.setTexture(BAR_FULL);
                                break;
                            case SERVER_OFFLINE:
                                symbol.setTexture(BAR_SERVER_OFFLINE);
                                break;
                        }
                    }
                })
                .collect(Collectors.toList());
        setPermanentImages(menuSymbols);
    }

    private void disableAllMessages() {
        volatileImages.forEach(s -> toggleImage(s, true));
    }

}
