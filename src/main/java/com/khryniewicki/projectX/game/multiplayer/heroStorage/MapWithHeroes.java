package com.khryniewicki.projectX.game.multiplayer.heroStorage;


import com.khryniewicki.projectX.config.messageHandler.Message;
import java.util.Map;

public class MapWithHeroes {
    private Map<String, Message> mapWithHeroes;

    private MapWithHeroes() {
    }

    public Map<String, Message> getMapWithHeroes() {
        return mapWithHeroes;
    }

    public void setMapWithHeroes(Map<String, Message> mapWithHeroes) {
        this.mapWithHeroes = mapWithHeroes;
    }


    public static MapWithHeroes getINSTANCE() {
        return Helper.INSTANCE;
    }

    private static class Helper {
        private final static MapWithHeroes INSTANCE = new MapWithHeroes();
    }
}
