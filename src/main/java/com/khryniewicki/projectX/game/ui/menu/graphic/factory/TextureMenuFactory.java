package com.khryniewicki.projectX.game.ui.menu.graphic.factory;

import com.khryniewicki.projectX.game.ui.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import com.khryniewicki.projectX.utils.GameUtil;

import java.awt.*;
import java.util.List;
import java.util.*;

import static com.khryniewicki.projectX.graphics.textures.GameTextures.YOU_LOSE_TEXTURE;
import static com.khryniewicki.projectX.graphics.textures.GameTextures.YOU_WIN_TEXTURE;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;


public class TextureMenuFactory {


    public static Map<String, MenuSymbol> TEXT_FACTORY;
    public List<Symbol> listWithCharacterMenuMessages;

    public static TextureMenuFactory getInstance() {
        return TextureMenuFactory.HELPER.INSTANCE;
    }

    private TextureMenuFactory() {
        fill();
    }

    private static class HELPER {
        private final static TextureMenuFactory INSTANCE = new TextureMenuFactory();
    }

    public List<Symbol> getListWithCharacterMenuMessages() {
        if (Objects.isNull(listWithCharacterMenuMessages)) {
            listWithCharacterMenuMessages = Arrays.asList(HERO_NAME, BG_ANIMATION, LOGO, VERSION);
        }
        return listWithCharacterMenuMessages;
    }

    public List<Symbol> getListWithTextMainMenuSymbols() {
        return Arrays.asList(TEXT_NO_HERO, TEXT_FALLENKING, TEXT_FALLENMONK, TEXT_FALLENWITCHER, TEXT_FIREWIZARD,
                TEXT_ICEWIZARD, TEXT_THUNDERWIZARD, TEXT_SERVER_OFFLINE, TEXT_ROOM_IS_FULL, NEW_VERSION);
    }

    public List<Symbol> getListWithPermamentMainMenuSymbols() {
        return Arrays.asList(PLAYERS_BAR_LABEL, PLAYERS_DESCRIPTION_LABEL, BG_ANIMATION, MENU_IMAGE, LOGO, VERSION);
    }

    private void fill() {
        TEXT_FACTORY = new HashMap<>();
        TEXT_FACTORY.put("Fallen Monk", TEXT_FALLENMONK);
        TEXT_FACTORY.put("Fallen Witcher", TEXT_FALLENWITCHER);
        TEXT_FACTORY.put("Fallen King", TEXT_FALLENKING);
        TEXT_FACTORY.put("Thunder Wizard", TEXT_THUNDERWIZARD);
        TEXT_FACTORY.put("Ice Wizard", TEXT_ICEWIZARD);
        TEXT_FACTORY.put("Fire Wizard", TEXT_FIREWIZARD);
        TEXT_FACTORY.put("NoHero", TEXT_NO_HERO);
    }

    public MenuSymbol getText(String text) {
        return TEXT_FACTORY.get(text);
    }

    public static final MenuSymbol TEXT_NO_HERO = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Please choose your hero"))
            .withName("NoHero")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_SERVER_OFFLINE = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Please try when server is on"))
            .withName("ServerOffLine")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol NEW_VERSION = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Please download latest version"))
            .withName("NewVersion")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_ROOM_IS_FULL = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Room is full, try later"))
            .withName("room_is_full")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FIREWIZARD = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Your hero is Fire Wizard"))
            .withName("CHOSE_FIREWIZARD")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_ICEWIZARD = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Your hero is Ice Wizard"))
            .withName("CHOSE_ICEWIZARD")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_THUNDERWIZARD = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Your hero is Thunder Wizard"))
            .withName("CHOSE_THUNDERWIZARD")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FALLENMONK = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Your hero is Fallen Monk"))
            .withName("CHOSE_FALLENMONK")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FALLENWITCHER = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Your hero is Fallen Witcher"))
            .withName("CHOSE_FALLENWITCHER")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FALLENKING = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Your hero is Fallen King"))
            .withName("CHOSE_FALLENKING")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol MENU_IMAGE = new MenuSymbol.Builder()
            .withTexture(MENU_IMAGE_TEXTURE)
            .withName("menu_image")
            .withVisibility(0f)
            .withHeight(9.5f)
            .withWidth(9.5f)
            .withPositionX(-7f)
            .withPositionY(-4.5f)
            .build();
    public static final MenuSymbol LOGO = new MenuSymbol.Builder()
            .withTexture(LOGO_TEXTURE)
            .withName("logo")
            .withVisibility(0f)
            .withHeight(0.9168f)
            .withWidth(2.4144f)
            .withPositionX(-9.6f)
            .withPositionY(-5.5f)
            .build();
    public static final MenuSymbol VERSION = new MenuSymbol.Builder()
            .withTexture(TextFactory.textInPlayerBar("v" + GameUtil.version, Color.WHITE))
            .withName("version")
            .withHeight(0.5f)
            .withWidth(1f)
            .withPositionX(8.9f)
            .withPositionY(-5.45f)
            .build();

    public static final MenuSymbol TABLE = new MenuSymbol.Builder()
            .withTexture(BLANK_TABLE_WINDOW)
            .withDisabled(true)
            .withName("table")
            .withVisibility(1f)
            .withHeight(3.75f)
            .withWidth(13.5f)
            .withPositionX(-8.5f)
            .withPositionY(-2.5f)
            .build();

    public static final MenuSymbol PLAYERS_DESCRIPTION_LABEL = new MenuSymbol.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withName("description_label")
            .withHeight(0.75f)
            .withWidth(6.5f)
            .withPositionX(4.7f)
            .withPositionY(3.2f)
            .build();
    public static final MenuSymbol PLAYERS_BAR_LABEL = new MenuSymbol.Builder()
            .withTexture(BLANK_BAR_LABEL_WINDOW)
            .withName("label")
            .withHeight(2.675f)
            .withWidth(4f)
            .withPositionX(4.4f)
            .withPositionY(2.9f)
            .build();
    public static final MenuSymbol LOADING = new MenuSymbol.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withName("loading")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.0f)
            .withPositionY(0.0f)
            .build();

    public static final MenuSymbol ANIMATION_HERO = new MenuSymbol.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .isSizeKnown(true)
            .withName("animation")
            .withPositionX(-1.0f)
            .withPositionY(2.8f)
            .build();
    public static final MenuSymbol BG_ANIMATION = new MenuSymbol.Builder()
            .withTexture(BG_ANIMATION_TEXTURE)
            .withName("bg_animation")
            .withHeight(11f)
            .withWidth(20f)
            .withVisibility(-0.5f)
            .withPositionX(-10f)
            .withPositionY(-5.5f)
            .build();
    public static final MenuSymbol ANIMATION_DUMMY = new MenuSymbol.Builder()
            .withTexture(TRAINING_DUMMY)
            .isSizeKnown(true)
            .withSize(2f)
            .withName("dummy")
            .withPositionX(4f)
            .withPositionY(2.8f)
            .withVisibility(0f)
            .build();
    public static final MenuSymbol HERO_NAME = new MenuSymbol.Builder()
            .withTexture(BLANK_TEXT_WINDOW_WITH_LINE)
            .withDisabled(true)
            .withHeight(1.25f)
            .withWidth(3.61f)
            .withPositionX(-5.5f)
            .withPositionY(-5f)
            .build();

    public static final MenuSymbol TIMER = new MenuSymbol.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withName("Timer")
            .withHeight(0.5f)
            .withWidth(4.34f)
            .withPositionX(6f)
            .withPositionY(4.5f)
            .withVisibility(1.0f)
            .build();

    public static final MenuSymbol YOU_WIN = new MenuSymbol.Builder()
            .withTexture(YOU_WIN_TEXTURE)
            .withName("victory")
            .withHeight(1f)
            .withWidth(6f)
            .withPositionX(-3f)
            .withPositionY(1f)
            .withVisibility(1f)
            .build();
    public static final MenuSymbol YOU_LOSE = new MenuSymbol.Builder()
            .withTexture(YOU_LOSE_TEXTURE)
            .withName("failure")
            .withHeight(1f)
            .withWidth(6f)
            .withPositionX(-3f)
            .withPositionY(1f)
            .withVisibility(1f)
            .build();
}
