package com.khryniewicki.projectX.game.ui.menu.graphic.factory;

import com.khryniewicki.projectX.game.ui.menu.buttons.Button;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory.*;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;
import static java.util.Arrays.asList;

@Data
public class ButtonsFactory {
    private static final ButtonsFactory instance = new ButtonsFactory();
    public List<Button> listWithMainMenuButtons;
    public List<Button> listWithCharacterMenuButtons;


    private ButtonsFactory() {
    }

    public static ButtonsFactory getInstance() {
        return ButtonsFactory.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static ButtonsFactory INSTANCE = new ButtonsFactory();
    }

    public List<Button> getListWithMainMenuButtons() {
        if (Objects.isNull(listWithMainMenuButtons)) {
            listWithMainMenuButtons = new ArrayList<>(asList(STARTING_BUTTON, CHOOSE_CHARACTER, CONTROL_SETTINGS, QUIT_BUTTON));
        }
        return listWithMainMenuButtons;
    }

    public List<Button> getListWithCharacterMenuButtons() {
        if (Objects.isNull(listWithCharacterMenuButtons)) {
            listWithCharacterMenuButtons = new ArrayList<>(asList(TYPE_YOUR_NAME, RETURN_BUTTON, FIRE_WIZARD_BUTTON, ICE_WIZARD_BUTTON, THUNDER_WIZARD_BUTTON, FALLEN_KING_BUTTON, FALLEN_MONK_BUTTON, FALLEN_WITCHER_BUTTON));
        }
        return listWithCharacterMenuButtons;
    }


    public static final Button STARTING_BUTTON = new Button.Builder()
            .withTexture(START_BUTTON_TEXTURE)
            .withButtonName(START)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-0.9f)
            .build();

    public static final Button CHOOSE_CHARACTER = new Button.Builder()
            .withTexture(CHOOSE_CHARACTER_TEXTURE)
            .withButtonName(SELECT_CHARACTER)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-2.1f)
            .build();
    public static final Button CONTROL_SETTINGS = new Button.Builder()
            .withTexture(CONTROL_SETTINGS_TEXTURE)
            .withButtonName(com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory.CONTROL_SETTINGS)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-3.3f)
            .build();
    public static final Button QUIT_BUTTON = new Button.Builder()
            .withTexture(QUIT_GAME_TEXTURE)
            .withButtonName(com.khryniewicki.projectX.game.ui.menu.buttons.ButtonsFactory.QUIT)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-4.5f)
            .build();

    public static final Button FIRE_WIZARD_BUTTON = new Button.Builder()
            .withTexture(FIRE_WIZARD_BUTTON_TEXTURE)
            .withButtonName(FIRE_WIZARD)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(2.2f)
            .build();
    public static final Button ICE_WIZARD_BUTTON = new Button.Builder()
            .withTexture(ICE_WIZARD_BUTTON_TEXTURE)
            .withButtonName(ICE_WIZARD)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(1.0f)
            .build();
    public static final Button THUNDER_WIZARD_BUTTON = new Button.Builder()
            .withTexture(THUNDER_WIZARD_BUTTON_TEXTURE)
            .withButtonName(THUNDER_WIZARD)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-0.2f)
            .build();
    public static final Button FALLEN_KING_BUTTON = new Button.Builder()
            .withTexture(FALLEN_KING_BUTTON_TEXTURE)
            .withButtonName(FALLEN_KING)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-1.4f)
            .build();
    public static final Button FALLEN_WITCHER_BUTTON = new Button.Builder()
            .withTexture(FALLEN_WITCHER_BUTTON_TEXTURE)
            .withButtonName(FALLEN_WITCHER)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-2.6f)
            .build();
    public static final Button FALLEN_MONK_BUTTON = new Button.Builder()
            .withTexture(FALLEN_MONK_BUTTON_TEXTURE)
            .withButtonName(FALLEN_MONK)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-3.8f)
            .build();
    public static final Button CHARACTER_SKILLS = new Button.Builder()
            .withTexture(SHOW_SKILLS)
            .withButtonName(TABLE_WITH_SKILLS)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(0f)
            .withPositionY(-3.8f)
            .build();

    public static final Button TYPE_YOUR_NAME = new Button.Builder()
            .withTexture(TYPE_NAME)
            .withButtonName(WRITE_HERO_NAME)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(0f)
            .withPositionY(-5f)
            .build();

    public static final Button RETURN_BUTTON = new Button.Builder()
            .withTexture(RETURN_BUTTON_TEXTURE)
            .withButtonName(SELECT_CHARACTER_RETURN)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();
    public static final Button RETURN_BUTTON2 = new Button.Builder()
            .withTexture(RETURN_BUTTON_TEXTURE)
            .withButtonName(CONTROL_SETTINGS_RETURN)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();

    public static final Button MAIN_MENU = new Button.Builder()
            .withTexture(GO_TO_MAIN_MENU)
            .withButtonName(RESTART_GO_TO_MAIN_MENU)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-2f)
            .withPositionY(-1f)
            .build();
    public static final Button QUIT = new Button.Builder()
            .withTexture(QUIT_TEXTURE)
            .withButtonName(RESTART_QUIT)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-2f)
            .withPositionY(-2.2f)
            .build();
}
