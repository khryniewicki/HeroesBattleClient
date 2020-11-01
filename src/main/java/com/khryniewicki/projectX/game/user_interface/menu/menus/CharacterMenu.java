package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.KeyboardSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.CreateTable;
import com.khryniewicki.projectX.utils.CreateText;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Getter
@Setter
public class CharacterMenu extends MenuImp {

    private final HeroesInstances heroesInstances;
    private final TextMenuFactory textMenuFactory;
    private final KeyboardSettings keyboardSettings;
    private final ButtonsFactory buttonsFactory;
    private MenuSymbol heroNameButton;
    private MenuSymbol table;
    private static final CharacterMenu instance = new CharacterMenu();

    public static CharacterMenu getInstance() {
        return instance;
    }

    private CharacterMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textMenuFactory = TextMenuFactory.getInstance();
        buttonsFactory = ButtonsFactory.getInstance();
        keyboardSettings = new KeyboardSettings();
        start();
    }

    @Override
    public void init() {
        List<MenuSymbol> buttonList = buttonsFactory.getListWithCharacterMenuButtons();
        heroNameButton = ButtonsFactory.HERO_NAME;
        buttonList.add(heroNameButton);
        table = TextMenuFactory.TABLE;
        super.setButtons(buttonList);
        super.setMessages(Collections.singletonList(table));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String btnName = (String) evt.getNewValue();
        MainMenu mainMenu = MainMenu.getInstance();

        if (evt.getPropertyName().equals("heroName")) {
            updateName(heroNameButton, btnName);
        } else if (btnName.equals("Return")) {
            mainMenu.render();
        } else {
            updateTable(table, btnName);
            heroesInstances.setHeroType(btnName);
            MenuSymbol menuSymbol = textMenuFactory.getText(btnName);
            mainMenu.showMessage(menuSymbol);
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
                        symbol.setTexture(CreateText.textToImageWithLine(name, 30));
                    }
                })
                .collect(Collectors.toList());
        super.setButtons(menuSymbols);
        render();
    }

    public void updateTable(MenuSymbol symbol, String btnName) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        log.info("UPDATE TABLE");
                        symbol.setTexture(CreateTable.tableImage(btnName));
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }
}