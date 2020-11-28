package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;

import java.util.*;


public class TextureMenuFactory {
    public final static Texture BAR_FULL = new Texture("bar_full.png");
    public final static Texture BAR_EMPTY = new Texture("bar_empty.png");
    public final static Texture BAR_HALF = new Texture("bar_half.png");
    public final static Texture BAR_SERVER_OFFLINE = new Texture("bar_server_offline.png");
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
            listWithCharacterMenuMessages = new ArrayList<>(Arrays.asList(HERO_NAME, BG_ANIMATION,TABLE));
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
        return new ArrayList<>(Arrays.asList(TEXT_FALLENKING, TEXT_FALLENMONK, TEXT_FALLENWITCHER, TEXT_FIREWIZARD, TEXT_ICEWIZARD, TEXT_THUNDERWIZARD,TEXT_SERVER_OFFLINE));
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
            .withTexture(new Texture("wizard2.png"))
            .withName("menu_image")
            .withDisabled(false)
            .withVisibility(0f)
            .withHeight(9.5f)
            .withWidth(9.5f)
            .withPositionX(-7f)
            .withPositionY(-4.5f)
            .build();

    public static final MenuSymbol TABLE = new MenuSymbol.Builder()
            .withTexture(new Texture("blankTableWindow.png"))
            .withDisabled(true)
            .withName("table")
            .withVisibility(1f)
            .withHeight(3.75f)
            .withWidth(13.5f)
            .withPositionX(-8.5f)
            .withPositionY(-2.5f)
            .build();

    public static final MenuSymbol PLAYERS_DESCRIPTION_LABEL = new Button.Builder()
            .withTexture(new Texture("blankTextWindow.png"))
            .withDisabled(false)
            .withName("label")
            .withHeight(1f)
            .withWidth(5f)
            .withPositionX(5.55f)
            .withPositionY(3.2f)
            .build();
    public static final MenuSymbol PLAYERS_BAR_LABEL = new Button.Builder()
            .withTexture(new Texture("blankTextWindow.png"))
            .withDisabled(false)
            .withName("label")
            .withHeight(2.5f)
            .withWidth(3f)
            .withPositionX(5.4f)
            .withPositionY(3.0f)
            .build();
    public static final MenuSymbol LOADING = new Button.Builder()
            .withTexture(new Texture("blankTextWindow.png"))
            .withDisabled(false)
            .withName("loading")
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-1.0f)
            .withPositionY(0.0f)
            .build();

    public static final MenuSymbol ANIMATION_HERO = new Button.Builder()
            .withTexture(new Texture("blankTextWindow.png"))
            .isSizeKnown(true)
            .withDisabled(false)
            .withName("animation")
            .withPositionX(-1.0f)
            .withPositionY(2.8f)
            .build();
    public static final MenuSymbol BG_ANIMATION = new Button.Builder()
            .withTexture(new Texture("bgAnimation.png"))
            .withDisabled(false)
            .withName("bg_animation")
            .withHeight(11f)
            .withWidth(20f)
            .withVisibility(-0.5f)
            .withPositionX(-10f)
            .withPositionY(-5.5f)
            .build();
    public static final MenuSymbol ANIMATION_DUMMY = new Button.Builder()
            .withTexture(new Texture("training_dummy1.png"))
            .isSizeKnown(true)
            .withDisabled(false)
            .withSize(2f)
            .withName("dummy")
            .withPositionX(4f)
            .withPositionY(2.8f)
            .withVisibility(0f)
            .build();
    public static final MenuSymbol HERO_NAME = new Button.Builder()
            .withPath("blankTextWindowWithLine.png")
            .withName("HeroName")
            .withDisabled(true)
            .withHeight(1f)
            .withWidth(4f)
            .withPositionX(-5f)
            .withPositionY(-5f)
            .build();
}
