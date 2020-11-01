package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.utils.CreateText;

import java.util.*;
import java.util.List;

public class TextMenuFactory {
    public static Map<String, MenuSymbol> TEXT_FACTORY;

    public static TextMenuFactory getInstance() {
        return TextMenuFactory.HELPER.INSTANCE;
    }

    private TextMenuFactory() {
        fill();
    }

    private static class HELPER {
        private final static TextMenuFactory INSTANCE = new TextMenuFactory();
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
    public List<MenuSymbol> getListWithTextMenuSymbols(){
        return new ArrayList<>(Arrays.asList(TEXT_FALLENKING,TEXT_FALLENMONK,TEXT_FALLENWITCHER,TEXT_FIREWIZARD,TEXT_ICEWIZARD,TEXT_THUNDERWIZARD));
    }
    public static final MenuSymbol TEXT_NO_HERO = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Please choose your hero"))
            .withName("NoHero")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FIREWIZARD = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Your hero is Fire Wizard"))
            .withName("CHOSE_FIREWIZARD")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_ICEWIZARD = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Your hero is Ice Wizard"))
            .withName("CHOSE_ICEWIZARD")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_THUNDERWIZARD = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Your hero is Thunder Wizard"))
            .withName("CHOSE_THUNDERWIZARD")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FALLENMONK = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Your hero is Fallen Monk"))
            .withName("CHOSE_FALLENMONK")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FALLENWITCHER = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Your hero is Fallen Witcher"))
            .withName("CHOSE_FALLENWITCHER")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();
    public static final MenuSymbol TEXT_FALLENKING = new MenuSymbol.Builder()
            .withTexture(CreateText.textToImageMenu("Your hero is Fallen King"))
            .withName("CHOSE_FALLENKING")
            .withDisabled(true)
            .withHeight(0.7f)
            .withWidth(4f)
            .withPositionX(-3.5f)
            .withPositionY(-5.4f)
            .build();

    public static final MenuSymbol MENU_IMAGE = new MenuSymbol.Builder()
            .withTexture(new Texture("wizard2.png"))
            .withName("wizard")
            .withDisabled(false)
            .withVisibility(0f)
            .withHeight(9.5f)
            .withWidth(9.5f)
            .withPositionX(-7f)
            .withPositionY(-4.5f)
            .build();
}
