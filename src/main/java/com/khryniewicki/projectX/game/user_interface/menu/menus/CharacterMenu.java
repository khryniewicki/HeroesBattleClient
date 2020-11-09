package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.KeyboardSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.*;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.*;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.HERO_NAME;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.TABLE;


@Slf4j
@Getter
@Setter
public class CharacterMenu extends MenuImp {
    private boolean activeWriting;
    private final HeroesInstances heroesInstances;
    private final TextureMenuFactory textureMenuFactory;
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
        textureMenuFactory = TextureMenuFactory.getInstance();
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
        HERO_NAME.addPropertyChangeListener(evt -> {
            String newValue = (String) evt.getNewValue();
            updateSymbol(HERO_NAME, getTextureFromTextFactory(newValue));
        });
        super.setMessages(textureMenuFactory.getListWithCharacterMenuMessages());
    }


    @Override
    public void addEventClick() {
        super.addEventClick();
        keyboardSettings.insert(HERO_NAME);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String btnName = (String) evt.getNewValue();
        mainMenu = MainMenu.getInstance();
        log.info("{}", btnName);
        switch (btnName) {
            case "Return":
                animation.stop();
                setMessagesVisibility(TABLE, true);
                removeButton(CHARACTER_SKILLS);
                mainMenu.render();
                break;
            case "showTable":
                setMessagesVisibility(TABLE, !TABLE.isDisabled());
                updateSymbol(CHARACTER_SKILLS, TABLE.isDisabled() ? HIDE_SKILLS : SKILLS);
                break;
            case "typeYourName":
                setMessagesVisibility(HERO_NAME, activeWriting);
                updateSymbol(TYPE_YOUR_NAME, activeWriting ? TYPE_NAME : CONFIRM);
                this.activeWriting = !activeWriting;
                break;
            default:
                addButton(CHARACTER_SKILLS);
                updateSymbol(TABLE, getTextureFromTableFactory(btnName));
                setHero(btnName);
                initAnimation(btnName);
                showMessageInMainMenu(btnName);
                break;
        }
    }

    private void setHero(String btnName) {
        heroesInstances.setHero(btnName);
    }

    private void showMessageInMainMenu(String btnName) {
        MenuSymbol text = textureMenuFactory.getText(btnName);
        mainMenu.showMessage(text);
    }

    public void initAnimation(String character) {
        animation.removeSpellIfExists();
        animation.play(character);
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
    }

    public void updateSymbol(MenuSymbol symbol, Texture texture) {
        List<MenuSymbol> menuSymbols = super.getMessages()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        symbol.setTexture(texture);
                    }
                })
                .collect(Collectors.toList());
        super.setMessages(menuSymbols);
        render();
    }

    private Texture getTextureFromTextFactory(String name) {
        return TextFactory.textToImageWithLine(name, 30);
    }

    private Texture getTextureFromTableFactory(String btnName) {
        return TableFactory.tableImage(btnName);
    }
}