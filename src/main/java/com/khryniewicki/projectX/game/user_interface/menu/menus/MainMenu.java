package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.ServerState;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Colors;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
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
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
@Getter
@Setter
public class MainMenu extends MenuImp {
    private boolean running;
    private volatile boolean connection;
    private static final MainMenu instance = new MainMenu();
    private final HeroesInstances heroesInstances;
    private final TextureMenuFactory textureMenuFactory;
    private final ButtonsFactory buttonsFactory;
    private final WebsocketScheduler websocketScheduler;
    private MenuSymbol noHero;
    private MenuSymbol playersDescriptionLabel;
    private MenuSymbol playersBarLabel;
    private volatile ServerState state;

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
                ServerState serverState = (ServerState) evt.getNewValue();
                if (serverState == ServerState.JOIN_GAME) {
                    setConnection(true);
                    websocketScheduler.removePropertyChangeListener(this);
                } else {
                    state = serverState;
                }
            }
        });
    }

    @Override
    public void init() {
        initButtons();
        initMessages();
        initConstantImages();
    }


    public void execute() {
        ServerState tmpNumbers = null;
        do {
            if (Objects.nonNull(state) && !state.equals(tmpNumbers)) {
                updateLabel(playersDescriptionLabel, state);
                updateLabelDescription(playersBarLabel, state);
                tmpNumbers = state;
            }
            glfwPollEvents();
        } while (!running);
    }

    private void initButtons() {
        List<MenuSymbol> buttonList = buttonsFactory.getListWithMainMenuButtons();
        super.setButtons(buttonList);
    }

    private void initMessages() {
        List<MenuSymbol> listWithMenuSymbols = textureMenuFactory.getListWithTextMainMenuSymbols();
        noHero = TEXT_NO_HERO;
        listWithMenuSymbols.add(noHero);
        super.setVolatileImages(listWithMenuSymbols);
    }

    private void initConstantImages() {
        playersBarLabel = PLAYERS_BAR_LABEL;
        playersDescriptionLabel = PLAYERS_DESCRIPTION_LABEL;
        super.setPermanentImages(new ArrayList<>(Arrays.asList(playersBarLabel, playersDescriptionLabel,BG_ANIMATION, MENU_IMAGE)));
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
                CharacterMenu characterMenu = CharacterMenu.getInstance();
                characterMenu.render();
                break;
            case "ControlSettings":
                ControlSettingsMenu controlSettingsMenu = ControlSettingsMenu.getInstance();
                controlSettingsMenu.render();
                break;
            case "QuitGame":
                glfwDestroyWindow(Game.window);
                break;
            case "Start":
                if (Objects.nonNull(heroesInstances.getHero())) {
                    setRunning(true);
                } else {
                    showMessage(noHero);
                    render();
                }
                break;
        }
    }

    public void showMessage(MenuSymbol symbol) {
        toggleImage(symbol, false);
    }

    public void updateLabel(MenuSymbol symbol, ServerState state) {
        List<MenuSymbol> menuSymbols = super.getPermanentImages()
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
        super.setPermanentImages(menuSymbols);
        render();
    }

    public void updateLabelDescription(MenuSymbol symbol, ServerState state) {
        List<MenuSymbol> menuSymbols = super.getPermanentImages()
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
        super.setPermanentImages(menuSymbols);
        render();
    }

    private void disableAllMessages() {
        super.getVolatileImages().forEach(s -> toggleImage(s, true));
    }

}
