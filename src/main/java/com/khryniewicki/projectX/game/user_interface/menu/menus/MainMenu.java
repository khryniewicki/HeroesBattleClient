package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.utils.CreateText;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.PLAYERS_ONLINE_LABEL;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.TEXT_NO_HERO;
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
    private final TextMenuFactory textMenuFactory;
    private final ButtonsFactory buttonsFactory;
    private final WebsocketScheduler websocketScheduler;
    private MenuSymbol noHero;
    private MenuSymbol playersOnlineLabel;
    private volatile String numberOfPlayers;

    public static MainMenu getInstance() {
        return instance;
    }

    private MainMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textMenuFactory = TextMenuFactory.getInstance();
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
                String newValue = (String) evt.getNewValue();
                if (newValue.equals("Over")) {
                    setConnection(true);
                    websocketScheduler.removePropertyChangeListener(this);
                } else {
                    numberOfPlayers = newValue;
                }
            }
        });
    }

    @Override
    public void init() {
        initButtons();
        initMessages();
    }

    public void runMenu(){
        String tmpNumbers="";
        do {
            if (Objects.nonNull(numberOfPlayers) && !numberOfPlayers.equals(tmpNumbers)){
                updateLabel(playersOnlineLabel, numberOfPlayers);
                tmpNumbers = numberOfPlayers;
            }
            glfwPollEvents();
        } while (!running);
    }

    private void initButtons() {
        List<MenuSymbol> buttonList = buttonsFactory.getListWithMainMenuButtons();
        super.setButtons(buttonList);
    }

    private void initMessages() {
        List<MenuSymbol> listWithMenuSymbols = textMenuFactory.getListWithTextMainMenuSymbols();
        noHero = TEXT_NO_HERO;
        playersOnlineLabel = PLAYERS_ONLINE_LABEL;
        listWithMenuSymbols.add(noHero);
        listWithMenuSymbols.add(PLAYERS_ONLINE_LABEL);
        super.setMessages(listWithMenuSymbols);
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
                if (Objects.nonNull(heroesInstances.getHeroType())) {
                    setRunning(true);
                } else {
                    showMessage(noHero);
                    render();
                }
                break;
        }
    }

    public void showMessage(MenuSymbol symbol) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        menuSymbol.setDisabled(false);
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
    }

    public void updateLabel(MenuSymbol symbol, String btnName) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        symbol.setTexture(CreateText.textToImageMenu(btnName));
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }

    private void disableAllMessages() {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals("menu_image")) {
                        menuSymbol.setDisabled(false);
                    } else if (menuSymbol.getName().equals("label")) {
                        menuSymbol.setDisabled(false);
                    } else {
                        menuSymbol.setDisabled(true);
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }

}
