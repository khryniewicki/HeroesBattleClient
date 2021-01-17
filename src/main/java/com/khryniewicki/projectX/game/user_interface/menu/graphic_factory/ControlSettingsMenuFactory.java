package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.textures.MenuTextures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.BG_ANIMATION;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_BLUE;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_YELLOW;

public class ControlSettingsMenuFactory {

    public static ControlSettingsMenuFactory getInstance() {
        return ControlSettingsMenuFactory.HELPER.INSTANCE;
    }

    private ControlSettingsMenuFactory() {

    }

    private static class HELPER {
        private final static ControlSettingsMenuFactory INSTANCE = new ControlSettingsMenuFactory();
    }

    public ArrayList<Symbol> get_list_with_icons() {
        return new ArrayList<>(Arrays.asList(BG_ANIMATION, MOUSE, UP, DOWN, LEFT, RIGHT, TEXT_DOWN, TEXT_LEFT, TEXT_RIGHT,
                TEXT_UP, BASIC_ATTACK, ULTIMATE_ATTACK));
    }

    public static final MenuSymbol MOUSE = new MenuSymbol.Builder("mouse")
            .withTexture(MenuTextures.MOUSE)
            .withHeight(6f)
            .withWidth(3f)
            .withPositionX(1.0f)
            .withPositionY(-2.0f)
            .build();
    public static final MenuSymbol UP = new MenuSymbol.Builder("up_arrow")
            .withTexture(MenuTextures.UP_ARROW)
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol DOWN = new MenuSymbol.Builder("down_arrow")
            .withTexture(MenuTextures.DOWN_ARROW)
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol RIGHT = new MenuSymbol.Builder("right_arrow")
            .withTexture(MenuTextures.RIGHT_ARROW)
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol LEFT = new MenuSymbol.Builder("left_arrow")
            .withTexture(MenuTextures.LEFT_ARROW)
            .withHeight(1f)
            .withWidth(1f)
            .withPositionX(-8f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol TEXT_UP = new MenuSymbol.Builder("TEXT_up_arrow")
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE UP", Color.WHITE))
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(2.0f)
            .build();
    public static final MenuSymbol TEXT_DOWN = new MenuSymbol.Builder("TEXT_down_arrow")
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE DOWN", Color.WHITE))
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(0.8f)
            .build();
    public static final MenuSymbol TEXT_RIGHT = new MenuSymbol.Builder("TEXT_right_arrow")
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE RIGHT", Color.WHITE))
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-0.4f)
            .build();
    public static final MenuSymbol TEXT_LEFT = new MenuSymbol.Builder("TEXT_left_arrow")
            .withTexture(TextFactory.textInControlSettingsToImage("MOVE LEFT", Color.WHITE))
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-6.5f)
            .withPositionY(-1.6f)
            .build();
    public static final MenuSymbol BASIC_ATTACK = new MenuSymbol.Builder("TEXT_right_arrow")
            .withTexture(TextFactory.textInControlSettingsToImage("BASIC ATTACK", BRIGHT_YELLOW))
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.5f)
            .withPositionY(2.1f)
            .build();
    public static final MenuSymbol ULTIMATE_ATTACK = new MenuSymbol.Builder("TEXT_left_arrow")
            .withTexture(TextFactory.textInControlSettingsToImage("ULTIMATE ATTACK", BRIGHT_BLUE))
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(3.5f)
            .withPositionY(2.1f)
            .build();

}
