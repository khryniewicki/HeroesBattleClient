package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Data;
import org.w3c.dom.css.RGBColor;

import java.awt.*;

@Data
public class Buttons {
    private static final Buttons instance = new Buttons();


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

    public static final MenuSymbol MOUSE = new Button.Builder()
            .withPath("mouse.png")
            .withName("mouse")
            .withHeight(6f)
            .withWidth(3f)
            .withPositionX(1.0f)
            .withPositionY(-2.0f)
            .build();
    public static final MenuSymbol UP = new Button.Builder()
            .withPath("up_arrow.png")
            .withName("up_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol DOWN = new Button.Builder()
            .withPath("down_arrow.png")
            .withName("down_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol RIGHT = new Button.Builder()
            .withPath("right_arrow.png")
            .withName("right_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol LEFT = new Button.Builder()
            .withPath("left_arrow.png")
            .withName("left_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol TEXT_UP = new Button.Builder()
            .withTexture(CreateText.textToImage("MOVE UP",30, Color.WHITE))
            .withName("TEXT_up_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol TEXT_DOWN = new Button.Builder()
            .withTexture(CreateText.textToImage("MOVE DOWN",30, Color.WHITE))
            .withName("TEXT_down_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol TEXT_RIGHT = new Button.Builder()
            .withTexture(CreateText.textToImage("MOVE RIGHT",30, Color.WHITE))
            .withName("TEXT_right_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol TEXT_LEFT = new Button.Builder()
            .withTexture(CreateText.textToImage("MOVE LEFT",30, Color.WHITE))
            .withName("TEXT_left_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol BASIC_ATTACK= new Button.Builder()
            .withTexture(CreateText.textToImage("BASIC ATTACK",30, new Color(196,255,14)))
            .withName("TEXT_right_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.5f)
            .withPositionY(2.1f)
            .build();
    public static final MenuSymbol ULTIMATE_ATTACK = new Button.Builder()
            .withTexture(CreateText.textToImage("ULTIMATE ATTACK",30, new Color(140,255,251)))
            .withName("TEXT_left_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(3.5f)
            .withPositionY(2.1f)
            .build();

}
