package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class CharacterMenu extends MenuImp {

    private static final CharacterMenu instance = new CharacterMenu();
    private HeroesInstances heroesInstances;

    private CharacterMenu() {
        super();
        init();
        addEventClick();
        subscribe();
        heroesInstances = HeroesInstances.getInstance();
    }

    public static CharacterMenu getInstance() {
        return instance;
    }

    @Override
    public void init() {
        Button fireWizard = Buttons.FIRE_WIZARD_BUTTON;
        Button iceWizard = Buttons.ICE_WIZARD_BUTTON;
        Button thunderWizard = Buttons.THUNDER_WIZARD_BUTTON;
        Button fallenKing = Buttons.FALLEN_KING_BUTTON;
        Button fallenWitcher = Buttons.FALLEN_WITCHER_BUTTON;
        Button fallenMonk = Buttons.FALLEN_MONK_BUTTON;
        Button returnButton = Buttons.RETURN_BUTTON;
        setButtons(new ArrayList<>(Arrays.asList(fireWizard, iceWizard, thunderWizard, fallenKing, fallenMonk, fallenWitcher, returnButton)));
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
        String btnName = bto.getName();
        setButtonTransferObject(bto);

        if (btnName.equals("Return")) {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.addEventClick();
            mainMenu.render();
        } else {
            heroesInstances.getHeroFactory().create(btnName);
        }
    }
}
