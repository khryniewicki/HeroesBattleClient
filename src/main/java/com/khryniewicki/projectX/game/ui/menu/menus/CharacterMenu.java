package com.khryniewicki.projectX.game.ui.menu.menus;

import com.khryniewicki.projectX.game.control.settings.keyboard.KeyboardSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.ui.menu.animation.Animation;
import com.khryniewicki.projectX.game.ui.menu.buttons.Button;
import com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextFactory;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.Objects;

import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory.CHARACTER_SKILLS;
import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory.TYPE_YOUR_NAME;
import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory.HERO_NAME;
import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory.TABLE;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;


@Slf4j
@Getter
@Setter
public class CharacterMenu extends AbstractMenu {
    private static final CharacterMenu instance = new CharacterMenu();
    private final HeroesInstances heroesInstances;
    private final TextureMenuFactory textureMenuFactory;
    private final KeyboardSettings keyboardSettings;
    private final com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory buttonsFactory;
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
        buttonsFactory = com.khryniewicki.projectX.game.ui.menu.graphic.factory.ButtonsFactory.getInstance();
        keyboardSettings = new KeyboardSettings();
        animation = new Animation();
        start();
    }

    @Override
    public void init() {
        initButtons();
        initVolatiles();
    }

    public void initButtons() {
        super.setButtons(buttonsFactory.getListWithCharacterMenuButtons());
    }

    public void initVolatiles() {
        HERO_NAME.addPropertyChangeListener(evt -> {
            String newValue = (String) evt.getNewValue();
            update_volatile(HERO_NAME, getTextureFromTextFactory(newValue));
            set_hero_name(newValue);
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
        ButtonsFactory buttonFactory = (ButtonsFactory) evt.getNewValue();
        mainMenu = MainMenu.getInstance();
        switch (buttonFactory) {
            case SELECT_CHARACTER_RETURN:
                restart();
                break;
            case TABLE_WITH_SKILLS:
                boolean disabled = TABLE.isDisabled();
                animation.toggle_table(!disabled);
                update_button(CHARACTER_SKILLS, disabled ? HIDE_SKILLS : SHOW_SKILLS);
                break;
            case WRITE_HERO_NAME:
                update_volatile(HERO_NAME, false);
                this.activeWriting = !activeWriting;
                update_button(TYPE_YOUR_NAME, activeWriting ? CONFIRM : TYPE_NAME);
                break;
            default:
                setChosenHero(buttonFactory.getName());
                add_button(CHARACTER_SKILLS);
                start_animation(buttonFactory.getName());
                set_hero_type(chosenHero);
                show_message_in_main_menu(chosenHero);
                break;
        }
    }

    @Override
    public void restart() {
        animation.restart();
        update_volatile(HERO_NAME, !verify_hero_name());
        update_button(CHARACTER_SKILLS, SHOW_SKILLS);
        update_button(TYPE_YOUR_NAME, TYPE_NAME);
        remove_button(CHARACTER_SKILLS);
        setActiveWriting(false);
        runMenu(MainMenu.getInstance(), MenuCard.MAIN_MENU);
    }

    private void set_hero_name(String name) {
        heroesInstances.setHeroName(name);
    }

    private boolean verify_hero_name() {
        String heroName = heroesInstances.getHeroName();
        return Objects.nonNull(heroName) && !heroName.isEmpty();
    }

    private void set_hero_type(String heroType) {
        heroesInstances.set_hero_type(heroType);
    }


    private void show_message_in_main_menu(String btnName) {
        mainMenu.enable_message(textureMenuFactory.getText(btnName));
    }

    public void start_animation(String character) {
        animation.execute(character);
    }

    public void remove_button(Button button) {
        button.removePropertyChangeListener(this);
        buttons.removeIf(b -> button.getButtonName().equals(b.getButtonName()));
    }

    public void add_button(Button button) {
        boolean exist = buttons.stream().anyMatch(b -> b.equals(button));
        if (!exist) {
            buttons.add(button);
            button.addPropertyChangeListener(this);
        }
    }

    private Texture getTextureFromTextFactory(String name) {
        return TextFactory.textToImageWithLine(name, 43);
    }


}
