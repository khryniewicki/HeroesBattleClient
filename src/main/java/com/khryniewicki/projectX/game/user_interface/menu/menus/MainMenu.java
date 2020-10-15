package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Data
public class MainMenu extends MenuImp {
    private static final MainMenu instance = new MainMenu();
    private boolean running;

    public static MainMenu getInstance() {
        return instance;
    }

    private MainMenu() {
        super();
        init();
        addEventClick();
        subscribe();
    }

    @Override
    public void init() {
        Button button = Buttons.CHOOSE_CHARACTER;
        Button startButton = Buttons.STARTING_BUTTON;
        setButtons(new ArrayList<>(Arrays.asList(button, startButton)));
    }
    @Override
    public void subscribe() {
        getButtons().forEach(button -> {
            button.addPropertyChangeListener(this);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ButtonTransferObject bto = (ButtonTransferObject) evt.getNewValue();
        setButtonTransferObject(bto);
        if (bto.getName().equals("ChooseCharacter")) {
            CharacterMenu characterMenu = CharacterMenu.getInstance();
            characterMenu.addEventClick();
            characterMenu.render();
        } else if (bto.getName().equals("Start")) {
            running = true;
        }
    }
}
