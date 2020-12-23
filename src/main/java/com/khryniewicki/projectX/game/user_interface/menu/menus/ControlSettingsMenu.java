package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ControlSettingsMenuFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Getter
@Setter
public class ControlSettingsMenu extends MenuImp {

    private static final ControlSettingsMenu instance = new ControlSettingsMenu();
    private ControlSettingsMenuFactory factory;

    public static ControlSettingsMenu getInstance() {
        return instance;
    }

    private ControlSettingsMenu() {
        super();
        factory = ControlSettingsMenuFactory.getInstance();
        start();
    }

    @Override
    public void init() {
        super.setVolatileImages(factory.listWithControlSettingsIcons);
        super.setButtons(new ArrayList<>(Collections.singletonList(ButtonsFactory.RETURN_BUTTON2)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String btnName = (String) evt.getNewValue();
        if (btnName.equals("Return2")) {
            runMenu(MainMenu.getInstance(),MenuCard.MAIN_MENU);
        }
    }

}
