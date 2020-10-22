package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;

import java.util.HashMap;
import java.util.Map;

public class ConnectionTextFactory {
    public static Map<String, MenuSymbol> TEXT_FACTORY;

    private ConnectionTextFactory() {
        fill();
    }

    public static ConnectionTextFactory getInstance() {
        return ConnectionTextFactory.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static ConnectionTextFactory INSTANCE = new ConnectionTextFactory();
    }


    private void fill() {
        TEXT_FACTORY = new HashMap<>();
    }

    public final static String ALL_PLAYERS = "all_players_connected.png";
    public final static String ASK_FOR_CHAR = "ask2.png";
    public final static String CONNECTION = "connection2.png";
    public final static String CONNECTION_ESTABLISHED = "connection_established.png";
    public final static String ERROR = "error.png";
    public final static String GET_READY = "get_ready.png";
    public final static String OTHER_PLAYER = "other_player.png";
    public final static String WELCOME = "welcome.png";
    public final static String TRY_LATER = "try_later.png";

}
