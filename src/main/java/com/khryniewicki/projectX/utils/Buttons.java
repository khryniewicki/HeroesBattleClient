package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;

public class Buttons {
    public static  final Button CHOOSE_CHARACTER = new Button.Builder()
            .withPath("ChooseCharacter.png")
            .withName("ChooseCharacter")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(3f)
            .build();
    public static  final Button STARTING_BUTTON = new Button.Builder()
            .withPath("buttonStart.png")
            .withName("Start")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(0f)
            .build();

    public static  final Button FIRE_WIZARD_BUTTON = new Button.Builder()
            .withPath("FireWizardBtn.png")
            .withName("FireWizard")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(3f)
            .build();
    public static  final Button ICE_WIZARD_BUTTON = new Button.Builder()
            .withPath("IceWizardBtn.png")
            .withName("IceWizard")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(1.9f)
            .build();
    public static  final Button THUNDER_WIZARD_BUTTON = new Button.Builder()
            .withPath("ThunderWizardBtn.png")
            .withName("ThunderWizard")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(0.8f)
            .build();
    public static  final Button FALLEN_KING_BUTTON = new Button.Builder()
            .withPath("FallenKingBtn.png")
            .withName("FallenKing")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(-0.3f)
            .build();
    public static  final Button FALLEN_WITCHER_BUTTON = new Button.Builder()
            .withPath("FallenWitcherBtn.png")
            .withName("FallenWitcher")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(-1.4f)
            .build();
    public static  final Button FALLEN_MONK_BUTTON = new Button.Builder()
            .withPath("FallenMonkBtn.png")
            .withName("FallenMonk")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(-2.5f)
            .build();
    public static  final Button RETURN_BUTTON = new Button.Builder()
            .withPath("ReturnBtn.png")
            .withName("Return")
            .withHeight(1f)
            .withWidth(3f)
            .withPositionX(-1f)
            .withPositionY(-3.6f)
            .build();
}
