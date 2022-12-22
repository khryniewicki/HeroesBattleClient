package com.khryniewicki.projectX.game.ui.menu.menus;

import com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.ControlSettingsMenuFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.Collections;

import static com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory.CONROL_SETTINGS_RETURN;
import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory.RETURN_BUTTON2;

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
        setVolatileImages(factory.getListWithIcons());
        setButtons(Collections.singletonList(RETURN_BUTTON2));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        ButtonsFactory btnName = (ButtonsFactory) evt.getNewValue();
        if (btnName.equals(CONROL_SETTINGS_RETURN)) {
            runMenu(MainMenu.getInstance(), MenuCard.MAIN_MENU);
        }
    }

}
