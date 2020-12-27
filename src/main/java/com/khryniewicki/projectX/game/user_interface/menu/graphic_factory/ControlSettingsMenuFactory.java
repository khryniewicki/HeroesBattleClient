package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.textures.MenuTextures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.BG_ANIMATION;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_BLUE;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_YELLOW;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.START_BUTTON_TEXTURE;

public class ControlSettingsMenuFactory {
    public List<MenuSymbol> listWithControlSettingsIcons;

    public static ControlSettingsMenuFactory getInstance() {
        return ControlSettingsMenuFactory.HELPER.INSTANCE;
    }

    private ControlSettingsMenuFactory() {
        fill();
    }

    private void fill() {
        listWithControlSettingsIcons = new ArrayList<>(Arrays.asList(BG_ANIMATION,MOUSE, UP, DOWN, LEFT, RIGHT, TEXT_DOWN, TEXT_LEFT, TEXT_RIGHT,
                TEXT_UP, BASIC_ATTACK, ULTIMATE_ATTACK));
    }

    private static class HELPER {
        private final static ControlSettingsMenuFactory INSTANCE = new ControlSettingsMenuFactory();
    }

    public static final MenuSymbol MOUSE = new Button.Builder()
            .withTexture(MenuTextures.MOUSE)
            .withName("mouse")
            .withHeight(6f)
            .withWidth(3f)
            .withPositionX(1.0f)
            .withPositionY(-2.0f)
            .build();
    public static final MenuSymbol UP = new Button.Builder()
            .withTexture(MenuTextures.UP_ARROW)
            .withName("up_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol DOWN = new Button.Builder()
            .withTexture(MenuTextures.DOWN_ARROW)
            .withName("down_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol RIGHT = new Button.Builder()
            .withTexture(MenuTextures.RIGHT_ARROW)
            .withName("right_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol LEFT = new Button.Builder()
            .withTexture(MenuTextures.LEFT_ARROW)
            .withName("left_arrow")
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol TEXT_UP = new Button.Builder()
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE UP", Color.WHITE))
            .withName("TEXT_up_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol TEXT_DOWN = new Button.Builder()
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE DOWN", Color.WHITE))
            .withName("TEXT_down_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol TEXT_RIGHT = new Button.Builder()
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE RIGHT", Color.WHITE))
            .withName("TEXT_right_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol TEXT_LEFT = new Button.Builder()
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE LEFT", Color.WHITE))
            .withName("TEXT_left_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol BASIC_ATTACK = new Button.Builder()
            .withTexture(TextFactory.textInControlSettingsToImage("BASIC ATTACK", BRIGHT_YELLOW))
            .withName("TEXT_right_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.5f)
            .withPositionY(2.1f)
            .build();
    public static final MenuSymbol ULTIMATE_ATTACK = new Button.Builder()
            .withTexture(TextFactory.textInControlSettingsToImage("ULTIMATE ATTACK", BRIGHT_BLUE))
            .withName("TEXT_left_arrow")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(3.5f)
            .withPositionY(2.1f)
            .build();

}
