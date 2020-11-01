package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.utils.CreateText;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlSettingsMenuFactory {
    public List<MenuSymbol> listWithControlSettingsIcons;

    public static ControlSettingsMenuFactory getInstance() {
        return ControlSettingsMenuFactory.HELPER.INSTANCE;
    }

    private ControlSettingsMenuFactory() {
        fill();
    }

    private void fill() {
        listWithControlSettingsIcons = new ArrayList<>(Arrays.asList(MOUSE, UP, DOWN, LEFT, RIGHT, TEXT_DOWN, TEXT_LEFT, TEXT_RIGHT,
                TEXT_UP, BASIC_ATTACK, ULTIMATE_ATTACK));
    }

    private static class HELPER {
        private final static ControlSettingsMenuFactory INSTANCE = new ControlSettingsMenuFactory();
    }

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
            .withTexture(CreateText.textInControlSettingsToImage("MOVE UP", Color.WHITE))
            .withName("TEXT_up_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol TEXT_DOWN = new Button.Builder()
            .withTexture(CreateText.textInControlSettingsToImage("MOVE DOWN", Color.WHITE))
            .withName("TEXT_down_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol TEXT_RIGHT = new Button.Builder()
            .withTexture(CreateText.textInControlSettingsToImage("MOVE RIGHT", Color.WHITE))
            .withName("TEXT_right_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol TEXT_LEFT = new Button.Builder()
            .withTexture(CreateText.textInControlSettingsToImage("MOVE LEFT", Color.WHITE))
            .withName("TEXT_left_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol BASIC_ATTACK = new Button.Builder()
            .withTexture(CreateText.textInControlSettingsToImage("BASIC ATTACK", new Color(196, 255, 14)))
            .withName("TEXT_right_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.5f)
            .withPositionY(2.1f)
            .build();
    public static final MenuSymbol ULTIMATE_ATTACK = new Button.Builder()
            .withTexture(CreateText.textInControlSettingsToImage("ULTIMATE ATTACK", new Color(140, 255, 251)))
            .withName("TEXT_left_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(3.5f)
            .withPositionY(2.1f)
            .build();

}
