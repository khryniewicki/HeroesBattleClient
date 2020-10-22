package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.KeyboardSettings;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.Buttons;
import com.khryniewicki.projectX.utils.CreateText;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
public class WriteNameMenu extends MenuImp {

    private static final WriteNameMenu instance = new WriteNameMenu();
    private static KeyboardSettings keyboardSettings;
    private MenuSymbol heroNameButton;

    public static WriteNameMenu getInstance() {
        return instance;
    }

    private WriteNameMenu() {
        super();
        start();
        keyboardSettings = new KeyboardSettings();

    }

    @Override
    public void init() {
        heroNameButton = Buttons.HERO_NAME;
        MenuSymbol returnButton = Buttons.RETURN_BUTTON3;
        returnButton.setClassName(this.getClass().getName());
        super.setButtons(new ArrayList<>(Arrays.asList(returnButton, heroNameButton)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("heroName")) {
            updateName(heroNameButton, (String) evt.getNewValue());

        } else {
            ButtonTransferObject bto = (ButtonTransferObject) evt.getNewValue();
            String btnName = bto.getName();
            setButtonTransferObject(bto);

            if (btnName.equals("Return3")) {
                MainMenu mainMenu = MainMenu.getInstance();
                mainMenu.render();
            }
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
