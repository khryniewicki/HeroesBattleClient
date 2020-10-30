package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.KeyboardSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.Buttons;
import com.khryniewicki.projectX.utils.CreateText;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Getter
@Setter
public class CharacterMenu extends MenuImp {
    private final HeroesInstances heroesInstances;
    private static final CharacterMenu instance = new CharacterMenu();
    private static TextMenuFactory textMenuFactory;

    public static CharacterMenu getInstance() {
        return instance;
    }

    private static KeyboardSettings keyboardSettings;
    private MenuSymbol heroNameButton;

    private CharacterMenu() {
        super();
        start();
        heroesInstances = HeroesInstances.getInstance();
        textMenuFactory = TextMenuFactory.getInstance();
        keyboardSettings = new KeyboardSettings();
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
        heroNameButton = Buttons.HERO_NAME;

        List<MenuSymbol> buttonList = Collections.synchronizedList(
                new ArrayList<>(Arrays.asList(fireWizard, iceWizard, thunderWizard, fallenKing, fallenMonk, fallenWitcher, returnButton, heroNameButton)));
        super.setButtons(buttonList);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("heroName")) {
            updateName(heroNameButton, (String) evt.getNewValue());
        } else {
            String btnName = (String) evt.getNewValue();

            MainMenu mainMenu = MainMenu.getInstance();

            if (!btnName.equals("Return")) {
                heroesInstances.setHeroType(btnName);
                MenuSymbol menuSymbol = textMenuFactory.getText(btnName);
                mainMenu.showMessage(menuSymbol);
            }
            mainMenu.render();
        }
    }

    @Override
    public void addEventClick() {
        super.addEventClick();
        keyboardSettings.insert(heroNameButton);
    }

    public void updateName(MenuSymbol symbol, String name) {
        List<MenuSymbol> menuSymbols = super.getButtons()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        symbol.setTexture(CreateText.textToImage(name,30));
                    }
                })
                .collect(Collectors.toList());
        super.setButtons(menuSymbols);
        render();
    }
}