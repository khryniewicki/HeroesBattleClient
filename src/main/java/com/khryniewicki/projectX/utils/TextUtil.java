package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;

public class TextUtil {

    public final static String ALL_PLAYERS = "all_players_connected.png";
    public final static String ASK_FOR_CHAR = "ask2.png";
    public final static String CONNECTION = "connection2.png";
    public final static String CONNECTION_ESTABLISHED = "connection_established.png";
    public final static String CHOSE_FIREWIZARD = "choose_firewizard.png";
    public final static String CHOSE_THUNDERWIZARD = "choose_thunderwizard.png";
    public final static String CHOSE_ICEWIZARD = "choose_icewizard.png";
    public final static String CHOSE_WITCHER = "choose_witcher.png";
    public final static String CHOSE_MONK = "choose_monk.png";
    public final static String CHOSE_FALLEN_KING = "choose_fallen_king.png";
    public final static String ERROR = "error.png";
    public final static String GET_READY = "get_ready.png";
    public final static String OTHER_PLAYER = "other_player.png";
    public final static String WELCOME = "welcome.png";
    public final static String TRY_LATER = "try_later.png";

    public static final MenuSymbol TEXT_NO_HERO = new MenuSymbol.Builder()
            .withPath("no_hero.png")
            .withName("NoHero")
            .withHeight(1f)
            .withWidth(10f)
            .withPositionX(-5f)
            .withPositionY(-5f)
            .build();
}
