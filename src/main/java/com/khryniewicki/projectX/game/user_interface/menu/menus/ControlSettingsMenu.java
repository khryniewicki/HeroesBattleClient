package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;

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
        MenuSymbol returnButton = Buttons.RETURN_BUTTON;
        returnButton.setClassName(this.getClass().getName());
        super.getButtons().add(returnButton);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        ButtonTransferObject bto = (ButtonTransferObject) evt.getNewValue();
        String btnName = bto.getName();
        setButtonTransferObject(bto);

        if (btnName.equals("Return")) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.render();
        }
    }

}
