package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.utils.Buttons;
import com.khryniewicki.projectX.utils.TextUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.*;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;

@Slf4j
@Getter
@Setter
public class MainMenu extends MenuImp {
    private MenuSymbol noHero;
    private static final MainMenu instance = new MainMenu();
    private final HeroesInstances heroesInstances;

    public static MainMenu getInstance() {
        return instance;
    }

    private MainMenu() {
        super();
        start();
        heroesInstances = HeroesInstances.getInstance();
    }

    @Override
    public void init() {
        MenuSymbol startButton = Buttons.STARTING_BUTTON;
        MenuSymbol button = Buttons.CHOOSE_CHARACTER;
        MenuSymbol controlSettings = Buttons.CONTROL_SETTINGS;
        MenuSymbol quitGame = Buttons.QUIT_BUTTON;
        noHero = TextUtil.TEXT_NO_HERO;
        List<MenuSymbol> buttonList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(button, startButton, controlSettings, quitGame)));
        buttonList.forEach(btn -> btn.setClassName(this.getClass().getName()));
        super.setButtons(buttonList);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ButtonTransferObject bto = (ButtonTransferObject) evt.getNewValue();
        super.setButtonTransferObject(bto);
        showMenu(bto);
    }

    private void showMenu(ButtonTransferObject bto) {
        List<MenuSymbol> buttons = super.getButtons();
        buttons.remove(noHero);

        switch (bto.getName()) {
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
                    buttons.add(noHero);
                    render();
                }
                break;
        }
    }
}
