package com.khryniewicki.projectX.game.ui.menu.menus;

import com.khryniewicki.projectX.game.engine.Application;
import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ServerState;
import com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory;
import com.khryniewicki.projectX.game.ui.subjects.SubjectMultiplayerState;
import com.khryniewicki.projectX.game.ui.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.GameUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory.*;
import static com.khryniewicki.projectX.game.ui.subjects.Subjects.SERVER;
import static com.khryniewicki.projectX.game.ui.subjects.Subjects.VERSION_PROPERTY;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
@Getter
@Setter
public class MainMenu extends AbstractMenu {

    private static final MainMenu instance = new MainMenu();
    private final HeroesInstances heroesInstances;
    private final TextureMenuFactory textureMenuFactory;
    private final com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory buttonsFactory;
    private final WebsocketScheduler websocketScheduler;
    int counter = 0;
    private MenuSymbol noHero;
    private ServerState currentState;
    private volatile ServerState state;
    private volatile String version = GameUtil.version;
    private boolean subscribed;


    private MainMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textureMenuFactory = TextureMenuFactory.getInstance();
        buttonsFactory = com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory.getInstance();
        websocketScheduler = WebsocketScheduler.getInstance();
        start();
        addObserver();
        subscribePlayersInGame();
        websocketScheduler.observePlayers();
    }

    public static MainMenu getInstance() {
        return instance;
    }

    private void addObserver() {
        subjectMultiplayerState = new SubjectMultiplayerState();
        subjectMultiplayerState.addPropertyChangeListener(MultiplayerController.getInstance());
    }

    private void subscribePlayersInGame() {
        if (!subscribed) {
            websocketScheduler.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    String propertyName = evt.getPropertyName();
                    if (propertyName.equals(SERVER.getName())) {
                        ServerState serverState = (ServerState) evt.getNewValue();
                        if (serverState == ServerState.JOIN_GAME) {
                            websocketScheduler.removePropertyChangeListener(this);
                            setSubscribed(false);
                        } else {
                            setState(serverState);
                        }
                    } else if (propertyName.equals(VERSION_PROPERTY.getName())) {
                        version = (String) evt.getNewValue();
                        System.out.println(version);
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
        setPermanentImages(textureMenuFactory.getListWithPermamentMainMenuSymbols());
    }

    @Override
    public void execute() {
        loop();
    }

    @Override
    public void prepare() {
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
            update_label(state);
            currentState = state;
        }
        glfwPollEvents();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //disable all messages
        disable_all_messages();
        ButtonsFactory buttonName = (ButtonsFactory) evt.getNewValue();
        switch (buttonName) {
            case SELECT_CHARACTER:
                runMenu(CharacterMenu.getInstance(), MenuCard.CHARACTER_MENU);
                break;
            case CONTROL_SETTINGS:
                runMenu(ControlSettingsMenu.getInstance(), MenuCard.CONTROL_SETTINGS);
                break;
            case QUIT:
                stop();
                Application.finishGame();
                break;
            case START:
                if (state.equals(ServerState.SERVER_OFFLINE)) {
                    enable_message(TEXT_SERVER_OFFLINE);
                } else if (state.equals(ServerState.TWO_PLAYERS)) {
                    enable_message(TEXT_ROOM_IS_FULL);
                } else if (!GameUtil.version.equals(version)) {
                    enable_message(NEW_VERSION);
                } else {
                    String heroType = heroesInstances.getHeroType();
                    if (Objects.nonNull(heroType)) {
                        heroesInstances.set_hero_type(heroType);
                        stop();
                        subjectMultiplayerState.setNews(MultiplayerState.CONNECT);
                    } else {
                        enable_message(TEXT_NO_HERO);
                    }
                }
                break;
        }

    }

    private void update_label(ServerState serverState) {
        this.permanentImages = manager.update_label(permanentImages, PLAYERS_BAR_LABEL, serverState);
        this.permanentImages = manager.update_label_description(permanentImages, PLAYERS_DESCRIPTION_LABEL, serverState);
        render();
    }

    private void disable_all_messages() {
        volatileImages.forEach(images -> update_volatile(images, true));
    }

    public void enable_message(MenuSymbol symbol) {
        update_volatile(symbol, false);
    }

    public synchronized void setState(ServerState state) {
        this.state = state;
        log.info("{}", state);
    }
}
