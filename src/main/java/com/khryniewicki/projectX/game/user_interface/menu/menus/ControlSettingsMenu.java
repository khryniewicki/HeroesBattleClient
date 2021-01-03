package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Buttons;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ControlSettingsMenuFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;

import static com.khryniewicki.projectX.game.user_interface.menu.buttons.Buttons.CONROL_SETTINGS_RETURN;

@Slf4j
@Getter
@Setter
public class ControlSettingsMenu extends AbstractMenu {

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
        setVolatileImages(factory.get_list_with_icons());
        setButtons(new ArrayList<>(Collections.singletonList(ButtonsFactory.RETURN_BUTTON2)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Buttons btnName = (Buttons) evt.getNewValue();
        if (btnName.equals(CONROL_SETTINGS_RETURN)) {
            runMenu(MainMenu.getInstance(), MenuCard.MAIN_MENU);
        }
    }

}
