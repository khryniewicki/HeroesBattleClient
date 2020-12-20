package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;

import java.util.*;

import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;


public class TextureMenuFactory {


    public static Map<String, MenuSymbol> TEXT_FACTORY;
    public List<MenuSymbol> listWithCharacterMenuMessages;

    public static TextureMenuFactory getInstance() {
        return TextureMenuFactory.HELPER.INSTANCE;
    }

    private TextureMenuFactory() {
        fill();
    }

    private static class HELPER {
        private final static TextureMenuFactory INSTANCE = new TextureMenuFactory();
    }

    public List<MenuSymbol> getListWithCharacterMenuMessages() {
        if (Objects.isNull(listWithCharacterMenuMessages)) {
            listWithCharacterMenuMessages = new ArrayList<>(Arrays.asList(HERO_NAME, BG_ANIMATION, TABLE));
        }
        return listWithCharacterMenuMessages;
    }

    private void fill() {
        TEXT_FACTORY = new HashMap<>();
        TEXT_FACTORY.put("FallenMonk", TEXT_FALLENMONK);
        TEXT_FACTORY.put("FallenWitcher", TEXT_FALLENWITCHER);
        TEXT_FACTORY.put("FallenKing", TEXT_FALLENKING);
        TEXT_FACTORY.put("ThunderWizard", TEXT_THUNDERWIZARD);
        TEXT_FACTORY.put("IceWizard", TEXT_ICEWIZARD);
        TEXT_FACTORY.put("FireWizard", TEXT_FIREWIZARD);
        TEXT_FACTORY.put("NoHero", TEXT_NO_HERO);
    }

    public MenuSymbol getText(String text) {
        return TEXT_FACTORY.get(text);
    }

    public List<MenuSymbol> getListWithTextMainMenuSymbols() {
        return new ArrayList<>(Arrays.asList(TEXT_NO_HERO, TEXT_FALLENKING, TEXT_FALLENMONK, TEXT_FALLENWITCHER, TEXT_FIREWIZARD,
                TEXT_ICEWIZARD, TEXT_THUNDERWIZARD, TEXT_SERVER_OFFLINE, TEXT_ROOM_IS_FULL));
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
    public static final MenuSymbol TEXT_ROOM_IS_FULL = new MenuSymbol.Builder()
            .withTexture(TextFactory.textToImageMenu("Room is full, Try later"))
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
            .withDisabled(false)
            .withVisibility(0f)
            .withHeight(9.5f)
            .withWidth(9.5f)
            .withPositionX(-7f)
            .withPositionY(-4.5f)
            .build();

    public static final MenuSymbol TABLE = new MenuSymbol.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withDisabled(true)
            .withName("table")
            .withVisibility(1f)
            .withHeight(3.75f)
            .withWidth(13.5f)
            .withPositionX(-8.5f)
            .withPositionY(-2.5f)
            .build();

    public static final MenuSymbol PLAYERS_DESCRIPTION_LABEL = new Button.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withDisabled(false)
            .withName("label")
            .withHeight(1f)
            .withWidth(5f)
            .withPositionX(5.55f)
            .withPositionY(3.2f)
            .build();
    public static final MenuSymbol PLAYERS_BAR_LABEL = new Button.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withDisabled(false)
            .withName("label")
            .withHeight(2.5f)
            .withWidth(3f)
            .withPositionX(5.4f)
            .withPositionY(3.0f)
            .build();
    public static final MenuSymbol LOADING = new Button.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .withDisabled(false)
            .withName("loading")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.0f)
            .withPositionY(0.0f)
            .build();

    public static final MenuSymbol ANIMATION_HERO = new Button.Builder()
            .withTexture(BLANK_TEXT_WINDOW)
            .isSizeKnown(true)
            .withDisabled(false)
            .withName("animation")
            .withPositionX(-1.0f)
            .withPositionY(2.8f)
            .build();
    public static final MenuSymbol BG_ANIMATION = new Button.Builder()
            .withTexture(BG_ANIMATION_TEXTURE)
            .withDisabled(false)
            .withName("bg_animation")
            .withHeight(11f)
            .withWidth(20f)
            .withVisibility(-0.5f)
            .withPositionX(-10f)
            .withPositionY(-5.5f)
            .build();
    public static final MenuSymbol ANIMATION_DUMMY = new Button.Builder()
            .withTexture(TRAINING_DUMMY)
            .isSizeKnown(true)
            .withDisabled(false)
            .withSize(2f)
            .withName("dummy")
            .withPositionX(4f)
            .withPositionY(2.8f)
            .withVisibility(0f)
            .build();
    public static final MenuSymbol HERO_NAME = new Button.Builder()
            .withTexture(BLANK_TEXT_WINDOW_WITH_LINE)
            .withName("HeroName")
            .withDisabled(true)
            .withHeight(1.25f)
            .withWidth(5f)
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
}
