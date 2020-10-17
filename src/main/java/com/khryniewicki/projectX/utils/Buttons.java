package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Data;
@Data
public class Buttons {
    private static final Buttons instance = new Buttons();


    public static final MenuSymbol STARTING_BUTTON = new Button.Builder()
            .withPath("buttonStart.png")
            .withName("Start")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(3f)
            .build();
    public static final MenuSymbol CHOOSE_CHARACTER = new Button.Builder()
            .withPath("ChooseCharacter.png")
            .withName("ChooseCharacter")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(1.8f)
            .build();
    public static final MenuSymbol CONTROL_SETTINGS = new Button.Builder()
            .withPath("controlSettings.png")
            .withName("ControlSettings")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(0.6f)
            .build();
    public static final MenuSymbol QUIT_BUTTON = new Button.Builder()
            .withPath("quitGame.png")
            .withName("QuitGame")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(-0.6f)
            .build();

    public static final MenuSymbol FIRE_WIZARD_BUTTON = new Button.Builder()
            .withPath("FireWizardBtn.png")
            .withName("FireWizard")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(3f)
            .build();
    public static final MenuSymbol ICE_WIZARD_BUTTON = new Button.Builder()
            .withPath("IceWizardBtn.png")
            .withName("IceWizard")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(1.9f)
            .build();
    public static final MenuSymbol THUNDER_WIZARD_BUTTON = new Button.Builder()
            .withPath("ThunderWizardBtn.png")
            .withName("ThunderWizard")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol FALLEN_KING_BUTTON = new Button.Builder()
            .withPath("FallenKingBtn.png")
            .withName("FallenKing")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(-0.3f)
            .build();
    public static final MenuSymbol FALLEN_WITCHER_BUTTON = new Button.Builder()
            .withPath("FallenWitcherBtn.png")
            .withName("FallenWitcher")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(-1.4f)
            .build();
    public static final MenuSymbol FALLEN_MONK_BUTTON = new Button.Builder()
            .withPath("FallenMonkBtn.png")
            .withName("FallenMonk")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(-2.5f)
            .build();
    public static final MenuSymbol RETURN_BUTTON = new Button.Builder()
            .withPath("ReturnBtn.png")
            .withName("Return")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1.5f)
            .withPositionY(-3.6f)
            .build();

}
