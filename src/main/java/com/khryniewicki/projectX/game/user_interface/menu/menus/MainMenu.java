package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.TEXT_NO_HERO;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;

@Slf4j
@Getter
@Setter
public class MainMenu extends MenuImp {
    private static final MainMenu instance = new MainMenu();
    private final HeroesInstances heroesInstances;
    private final TextMenuFactory textMenuFactory;
    private final ButtonsFactory buttonsFactory;
    private MenuSymbol noHero;

    public static MainMenu getInstance() {
        return instance;
    }

    private MainMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textMenuFactory = TextMenuFactory.getInstance();
        buttonsFactory = ButtonsFactory.getInstance();
        start();
    }

    @Override
    public void init() {
        initButtons();
        initMessages();
    }

    private void initButtons() {
        List<MenuSymbol> buttonList = buttonsFactory.getListWithMainMenuButtons();
        super.setButtons(buttonList);
    }

    private void initMessages() {
        List<MenuSymbol> listWithMenuSymbols = textMenuFactory.getListWithTextMenuSymbols();
        noHero = TEXT_NO_HERO;
        listWithMenuSymbols.add(noHero);
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

    private void disableAllMessages() {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals("menu_image")) {
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
