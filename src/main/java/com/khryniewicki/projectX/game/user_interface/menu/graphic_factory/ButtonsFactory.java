package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
            listWithCharacterMenuButtons = new ArrayList<>(Arrays.asList(RETURN_BUTTON, FIRE_WIZARD_BUTTON, ICE_WIZARD_BUTTON, THUNDER_WIZARD_BUTTON, FALLEN_KING_BUTTON, FALLEN_MONK_BUTTON, FALLEN_WITCHER_BUTTON));
        }
        return listWithCharacterMenuButtons;
    }

    public static final MenuSymbol STARTING_BUTTON = new Button.Builder()
            .withPath("buttonStart.png")
            .withName("Start")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-1.4f)
            .build();

    public static final MenuSymbol CHOOSE_CHARACTER = new Button.Builder()
            .withPath("ChooseCharacter.png")
            .withName("ChooseCharacter")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-2.6f)
            .build();
    public static final MenuSymbol CONTROL_SETTINGS = new Button.Builder()
            .withPath("controlSettings.png")
            .withName("ControlSettings")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-3.8f)
            .build();
    public static final MenuSymbol QUIT_BUTTON = new Button.Builder()
            .withPath("quitGame.png")
            .withName("QuitGame")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();

    public static final MenuSymbol FIRE_WIZARD_BUTTON = new Button.Builder()
            .withPath("FireWizardBtn.png")
            .withName("FireWizard")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(2.2f)
            .build();
    public static final MenuSymbol ICE_WIZARD_BUTTON = new Button.Builder()
            .withPath("IceWizardBtn.png")
            .withName("IceWizard")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(1.0f)
            .build();
    public static final MenuSymbol THUNDER_WIZARD_BUTTON = new Button.Builder()
            .withPath("ThunderWizardBtn.png")
            .withName("ThunderWizard")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-0.2f)
            .build();
    public static final MenuSymbol FALLEN_KING_BUTTON = new Button.Builder()
            .withPath("FallenKingBtn.png")
            .withName("FallenKing")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-1.4f)
            .build();
    public static final MenuSymbol FALLEN_WITCHER_BUTTON = new Button.Builder()
            .withPath("FallenWitcherBtn.png")
            .withName("FallenWitcher")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-2.6f)
            .build();
    public static final MenuSymbol FALLEN_MONK_BUTTON = new Button.Builder()
            .withPath("FallenMonkBtn.png")
            .withName("FallenMonk")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-3.8f)
            .build();
    public static final MenuSymbol RETURN_BUTTON = new Button.Builder()
            .withPath("ReturnBtn.png")
            .withName("Return")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();
    public static final MenuSymbol RETURN_BUTTON2 = new Button.Builder()
            .withPath("ReturnBtn.png")
            .withName("Return2")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();
    public static final MenuSymbol RETURN_BUTTON3 = new Button.Builder()
            .withPath("ReturnBtn.png")
            .withName("Return3")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(5f)
            .withPositionY(-5.0f)
            .build();
    public static final MenuSymbol HERO_NAME = new Button.Builder()
            .withPath("blankTextWindow.png")
            .withName("HeroName")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-2f)
            .withPositionY(0.0f)
            .build();

}
