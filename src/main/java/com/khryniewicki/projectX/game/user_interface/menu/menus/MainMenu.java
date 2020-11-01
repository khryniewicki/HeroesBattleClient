package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.MENU_IMAGE;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;

@Slf4j
@Getter
@Setter
public class MainMenu extends MenuImp {
    private MenuSymbol noHero;
    private MenuSymbol menuImage;
    private MenuSymbol fireWizard;
    private static final MainMenu instance = new MainMenu();
    private final HeroesInstances heroesInstances;
    private static TextMenuFactory textMenuFactory;

    public static MainMenu getInstance() {
        return instance;
    }

    private MainMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textMenuFactory = TextMenuFactory.getInstance();
        start();
    }

    @Override
    public void init() {
        initButtons();
        initMessages();
    }

    private void initButtons() {
        MenuSymbol startButton = Buttons.STARTING_BUTTON;
        MenuSymbol button = Buttons.CHOOSE_CHARACTER;
        MenuSymbol controlSettings = Buttons.CONTROL_SETTINGS;
        MenuSymbol quitGame = Buttons.QUIT_BUTTON;
        List<MenuSymbol> buttonList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(button, startButton, controlSettings, quitGame)));
        super.setButtons(buttonList);
    }

    private void initMessages() {
        noHero = TextMenuFactory.TEXT_NO_HERO;
        menuImage = MENU_IMAGE;
        List<MenuSymbol> listWithMenuSymbols = textMenuFactory.getListWithTextMenuSymbols();
        listWithMenuSymbols.add(noHero);
        listWithMenuSymbols.add(menuImage);
        super.setMessages(listWithMenuSymbols);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String bto = (String) evt.getNewValue();
        showMenu(bto);
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
        render();
    }

    private void disableAllMessages() {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(menuImage)) {
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
