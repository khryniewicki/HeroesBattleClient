package com.khryniewicki.projectX.game.menu.heroStorage;

import com.khryniewicki.projectX.config.Message;

import java.util.Map;

public class MapWithHeroes {
    private Map<String, Message> mapWithHeroes;

    public Map<String, Message> getMapWithHeroes() {
        return mapWithHeroes;
    }

    public void setMapWithHeroes(Map<String, Message> mapWithHeroes) {
        this.mapWithHeroes = mapWithHeroes;
    }

    private MapWithHeroes() {
    }

    public static MapWithHeroes getINSTANCE() {
        return Helper.INSTANCE;
    }

    private static class Helper {


        private final static MapWithHeroes INSTANCE = new MapWithHeroes();
    }
}
