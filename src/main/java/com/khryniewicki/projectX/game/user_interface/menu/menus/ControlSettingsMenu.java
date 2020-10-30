package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Getter
@Setter
public class ControlSettingsMenu extends MenuImp {

    private static final ControlSettingsMenu instance = new ControlSettingsMenu();

    public static ControlSettingsMenu getInstance() {
        return instance;
    }

    private ControlSettingsMenu() {
        super();
        start();
    }

    @Override
    public void init() {
        MenuSymbol returnButton = Buttons.RETURN_BUTTON2;
        MenuSymbol mouse = Buttons.MOUSE;
        MenuSymbol up = Buttons.UP;
        MenuSymbol down = Buttons.DOWN;
        MenuSymbol right = Buttons.RIGHT;
        MenuSymbol left = Buttons.LEFT;
        MenuSymbol text_up = Buttons.TEXT_UP;
        MenuSymbol text_down = Buttons.TEXT_DOWN;
        MenuSymbol text_right = Buttons.TEXT_RIGHT;
        MenuSymbol text_left = Buttons.TEXT_LEFT;
        MenuSymbol basicAttack = Buttons.BASIC_ATTACK;
        MenuSymbol ultimateAttack = Buttons.ULTIMATE_ATTACK;
        super.setButtons(new ArrayList<>(Arrays.asList(returnButton, mouse,up, down, right, left,text_up,text_down,text_left,text_right,basicAttack,ultimateAttack)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String btnName = (String) evt.getNewValue();
        log.info(btnName);
        if (btnName.equals("Return2")) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.render();
        }
    }

}
