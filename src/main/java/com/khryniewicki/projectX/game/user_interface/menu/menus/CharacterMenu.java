package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.KeyboardSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.Animation;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.CreateTable;
import com.khryniewicki.projectX.utils.CreateText;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.*;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.HERO_NAME;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.TABLE;


@Slf4j
@Getter
@Setter
public class CharacterMenu extends MenuImp {
    private boolean activeWriting;
    private final HeroesInstances heroesInstances;
    private final TextMenuFactory textMenuFactory;
    private final KeyboardSettings keyboardSettings;
    private final ButtonsFactory buttonsFactory;
    private static final CharacterMenu instance = new CharacterMenu();
    private final Animation animation;
    private MainMenu mainMenu;


    public static CharacterMenu getInstance() {
        return instance;
    }

    private CharacterMenu() {
        super();
        heroesInstances = HeroesInstances.getInstance();
        textMenuFactory = TextMenuFactory.getInstance();
        buttonsFactory = ButtonsFactory.getInstance();
        keyboardSettings = new KeyboardSettings();
        animation = new Animation();
        start();
    }

    @Override
    public void init() {
        initButtons();
        initMessages();
    }

    public void initButtons() {
        super.setButtons(buttonsFactory.getListWithCharacterMenuButtons());
    }

    public void initMessages() {
        HERO_NAME.addPropertyChangeListener(evt -> updateName(HERO_NAME, (String) evt.getNewValue()));
        super.setMessages(textMenuFactory.getListWithCharacterMenuMessages());
    }

    public void initAnimation(String character) {
        animation.removeIfSpellExists();
        animation.play(character);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String btnName = (String) evt.getNewValue();
        mainMenu = MainMenu.getInstance();
        log.info("{}", btnName);
        switch (btnName) {
            case "Return":
                animation.stop();
                mainMenu.render();
                TABLE.setDisabled(true);
                removeButton(CHARACTER_SKILLS);
                break;
            case "showTable":
                CHARACTER_SKILLS.setTexture(TABLE.isDisabled() ? HIDE_SKILLS : SKILLS);
                toggleMessagesVisibility(TABLE);
                break;
            case "typeYourName":
                HERO_NAME.setDisabled(activeWriting);
                TYPE_YOUR_NAME.setTexture(activeWriting ? TYPE_NAME : CONFIRM);
                this.activeWriting = !activeWriting;
                render();
                break;
            default:
                addButton(CHARACTER_SKILLS);
                updateTable(TABLE, btnName);
                heroesInstances.setHero(btnName);
                initAnimation(btnName);
                showMessageInMainMenu(btnName);
                break;
        }
    }

    private void showMessageInMainMenu(String btnName) {
        MenuSymbol text = textMenuFactory.getText(btnName);
        mainMenu.showMessage(text);
    }


    @Override
    public void addEventClick() {
        super.addEventClick();
        keyboardSettings.insert(HERO_NAME);
    }

    public void removeButton(MenuSymbol symbol) {
        List<MenuSymbol> buttons = super.getButtons();
        symbol.removePropertyChangeListener(this);
        buttons.removeIf(s -> symbol.getName().equals(s.getName()));
    }

    public void addButton(MenuSymbol symbol) {
        List<MenuSymbol> buttons = super.getButtons();
        boolean exist = buttons
                .stream()
                .anyMatch(menuSymbol -> menuSymbol.equals(symbol));
        if (!exist) {
            buttons.add(symbol);
            symbol.addPropertyChangeListener(this);
        }
        render();
    }

    public void toggleMessagesVisibility(MenuSymbol symbol) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        boolean disabled = menuSymbol.isDisabled();
                        menuSymbol.setDisabled(!disabled);
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }

    public void updateName(MenuSymbol symbol, String name) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        symbol.setTexture(CreateText.textToImageWithLine(name, 30));
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }

    public void updateTable(MenuSymbol symbol, String btnName) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        symbol.setTexture(CreateTable.tableImage(btnName));
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }
}