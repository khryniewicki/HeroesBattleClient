package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;

@Data
public class ButtonsFactory {
    private static final ButtonsFactory instance = new ButtonsFactory();
    public List<MenuSymbol> listWithMainMenuButtons;
    public List<MenuSymbol> listWithCharacterMenuButtons;


    private ButtonsFactory() {
    }

    public static ButtonsFactory getInstance() {
        return ButtonsFactory.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static ButtonsFactory INSTANCE = new ButtonsFactory();
    }

    public List<MenuSymbol> getListWithMainMenuButtons() {
        if (Objects.isNull(listWithMainMenuButtons)) {
            listWithMainMenuButtons = new ArrayList<>(Arrays.asList(STARTING_BUTTON, CHOOSE_CHARACTER, CONTROL_SETTINGS, QUIT_BUTTON));
        }
        return listWithMainMenuButtons;
    }

    public List<MenuSymbol> getListWithCharacterMenuButtons() {
        if (Objects.isNull(listWithCharacterMenuButtons)) {
            listWithCharacterMenuButtons = new ArrayList<>(Arrays.asList(TYPE_YOUR_NAME, RETURN_BUTTON, FIRE_WIZARD_BUTTON, ICE_WIZARD_BUTTON, THUNDER_WIZARD_BUTTON, FALLEN_KING_BUTTON, FALLEN_MONK_BUTTON, FALLEN_WITCHER_BUTTON));
        }
        return listWithCharacterMenuButtons;
    }

    public static final MenuSymbol STARTING_BUTTON = new Button.Builder()
            .withTexture(START_BUTTON_TEXTURE)
            .withName("Start")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-1.4f)
            .build();

    public static final MenuSymbol CHOOSE_CHARACTER = new Button.Builder()
            .withTexture(CHOOSE_CHARACTER_TEXTURE)
            .withName("ChooseCharacter")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-2.6f)
            .build();
    public static final MenuSymbol CONTROL_SETTINGS = new Button.Builder()
            .withTexture(CONTROL_SETTINGS_TEXTURE)
            .withName("ControlSettings")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-3.8f)
            .build();
    public static final MenuSymbol QUIT_BUTTON = new Button.Builder()
            .withTexture(QUIT_GAME_TEXTURE)
            .withName("QuitGame")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();

    public static final MenuSymbol FIRE_WIZARD_BUTTON = new Button.Builder()
            .withTexture(FIRE_WIZARD_BUTTON_TEXTURE)
            .withName("FireWizard")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(2.2f)
            .build();
    public static final MenuSymbol ICE_WIZARD_BUTTON = new Button.Builder()
            .withTexture(ICE_WIZARD_BUTTON_TEXTURE)
            .withName("IceWizard")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(1.0f)
            .build();
    public static final MenuSymbol THUNDER_WIZARD_BUTTON = new Button.Builder()
            .withTexture(THUNDER_WIZARD_BUTTON_TEXTURE)
            .withName("ThunderWizard")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-0.2f)
            .build();
    public static final MenuSymbol FALLEN_KING_BUTTON = new Button.Builder()
            .withTexture(FALLEN_KING_BUTTON_TEXTURE)
            .withName("FallenKing")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-1.4f)
            .build();
    public static final MenuSymbol FALLEN_WITCHER_BUTTON = new Button.Builder()
            .withTexture(FALLEN_WITCHER_BUTTON_TEXTURE)
            .withName("FallenWitcher")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-2.6f)
            .build();
    public static final MenuSymbol FALLEN_MONK_BUTTON = new Button.Builder()
            .withTexture(FALLEN_MONK_BUTTON_TEXTURE)
            .withName("FallenMonk")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-3.8f)
            .build();
    public static final MenuSymbol CHARACTER_SKILLS = new Button.Builder()
            .withTexture(SKILLS)
            .withName("showTable")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(0f)
            .withPositionY(-3.8f)
            .build();

    public static final MenuSymbol TYPE_YOUR_NAME = new Button.Builder()
            .withTexture(TYPE_NAME)
            .withName("typeYourName")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(0f)
            .withPositionY(-5f)
            .build();

    public static final MenuSymbol RETURN_BUTTON = new Button.Builder()
            .withTexture(RETURN_BUTTON_TEXTURE)
            .withName("Return")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();
    public static final MenuSymbol RETURN_BUTTON2 = new Button.Builder()
            .withTexture(RETURN_BUTTON_TEXTURE)
            .withName("Return2")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();
    public static final MenuSymbol RETURN_BUTTON3 = new Button.Builder()
            .withTexture(RETURN_BUTTON_TEXTURE)
            .withName("Return3")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();

    public static final MenuSymbol MAIN_MENU = new Button.Builder()
            .withTexture(GO_TO_MAIN_MENU)
            .withName("main_menu")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-2f)
            .withPositionY(0f)
            .build();
    public static final MenuSymbol QUIT = new Button.Builder()
            .withTexture(QUIT_TEXTURE)
            .withName("quit")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-2f)
            .withPositionY(-1.2f)
            .build();
}
