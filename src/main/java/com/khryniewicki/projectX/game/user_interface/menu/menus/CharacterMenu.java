package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.utils.Buttons;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Getter
@Setter
public class CharacterMenu extends MenuImp {
    private final HeroesInstances heroesInstances;
    private static final CharacterMenu instance = new CharacterMenu();

    public static CharacterMenu getInstance() {
        return instance;
    }

    private CharacterMenu() {
        super();
        start();
        heroesInstances = HeroesInstances.getInstance();
    }

    @Override
    public void init() {
        MenuSymbol fireWizard = Buttons.FIRE_WIZARD_BUTTON;
        MenuSymbol iceWizard = Buttons.ICE_WIZARD_BUTTON;
        MenuSymbol thunderWizard = Buttons.THUNDER_WIZARD_BUTTON;
        MenuSymbol fallenKing = Buttons.FALLEN_KING_BUTTON;
        MenuSymbol fallenWitcher = Buttons.FALLEN_WITCHER_BUTTON;
        MenuSymbol fallenMonk = Buttons.FALLEN_MONK_BUTTON;
        MenuSymbol returnButton = Buttons.RETURN_BUTTON;
        List<MenuSymbol> buttonList = Collections.synchronizedList(
                new ArrayList<>(Arrays.asList(fireWizard, iceWizard, thunderWizard, fallenKing, fallenMonk, fallenWitcher, returnButton)));
        buttonList.forEach(btn -> btn.setClassName(this.getClass().getName()));
        super.setButtons(buttonList);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ButtonTransferObject bto = (ButtonTransferObject) evt.getNewValue();
        String btnName = bto.getName();
        setButtonTransferObject(bto);

        if (!btnName.equals("Return")) {
            heroesInstances.setHeroType(btnName);
        }

        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.render();
    }

}
