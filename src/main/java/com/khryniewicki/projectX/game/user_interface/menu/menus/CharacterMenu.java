package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.KeyboardSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.animation.Animation;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.*;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.CHARACTER_SKILLS;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.TYPE_YOUR_NAME;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.HERO_NAME;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.TABLE;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;


@Slf4j
@Getter
@Setter
public class CharacterMenu extends AbstractMenu {
    private static final CharacterMenu instance = new CharacterMenu();
    private final HeroesInstances heroesInstances;
    private final TextureMenuFactory textureMenuFactory;
    private final KeyboardSettings keyboardSettings;
    private final ButtonsFactory buttonsFactory;
    private Animation animation;
    private MainMenu mainMenu;
    private volatile String chosenHero;
    private boolean activeWriting;

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
            updateImage(HERO_NAME, getTextureFromTextFactory(newValue));
            setHeroName(newValue);
        });
        setVolatileImages(textureMenuFactory.getListWithCharacterMenuMessages());
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
        switch (btnName) {
            case "Return":
                restart();
                break;
            case "showTable":
                boolean disabled = TABLE.isDisabled();
                animation.toggle_table(!disabled);
                updateButtonText(CHARACTER_SKILLS, disabled ? HIDE_SKILLS : SKILLS);
                break;
            case "typeYourName":
                toggleImage(HERO_NAME, activeWriting);
                this.activeWriting = !activeWriting;
                updateButtonText(TYPE_YOUR_NAME, activeWriting ? CONFIRM : TYPE_NAME);
                break;
            default:
                setChosenHero(btnName);
                addButton(CHARACTER_SKILLS);
                initAnimation(btnName);
                set_hero_type(chosenHero);
                showMessageInMainMenu(chosenHero);
                break;
        }
    }

    @Override
    public void restart() {
        animation.stop();
        animation.toggle_table(true);
        toggleImage(HERO_NAME, true);
        updateButtonText(CHARACTER_SKILLS, SKILLS);
        updateButtonText(TYPE_YOUR_NAME, TYPE_NAME);
        removeButton(CHARACTER_SKILLS);
        setActiveWriting(false);
        runMenu(MainMenu.getInstance(), MenuCard.MAIN_MENU);
    }

    private void setHeroName(String name) {
        heroesInstances.setHeroName(name);
    }

    private void updateButtonText(MenuSymbol characterSkills, Texture tex) {
        characterSkills.setTexture(tex);
        render();
    }

    private void set_hero_type(String heroType) {
        heroesInstances.set_hero_type(heroType);
    }

    private void showMessageInMainMenu(String btnName) {
        MenuSymbol text = textureMenuFactory.getText(btnName);
        mainMenu.showMessage(text);
    }

    public void initAnimation(String character) {
        animation.remove_spell_if_exists();
        animation.execute(character);
    }

    public void removeButton(MenuSymbol symbol) {
        symbol.removePropertyChangeListener(this);
        buttons.removeIf(s -> symbol.getName().equals(s.getName()));
    }

    public void addButton(MenuSymbol symbol) {
        boolean exist = buttons.stream().anyMatch(menuSymbol -> menuSymbol.equals(symbol));
        if (!exist) {
            buttons.add(symbol);
            symbol.addPropertyChangeListener(this);
        }
    }


    private Texture getTextureFromTextFactory(String name) {
        return TextFactory.textToImageWithLine(name, 43);
    }


}