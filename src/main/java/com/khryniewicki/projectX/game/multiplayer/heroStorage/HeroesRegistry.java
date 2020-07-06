package com.khryniewicki.projectX.game.multiplayer.heroStorage;


import com.khryniewicki.projectX.game.websocket.messages.Message;
import java.util.Map;

public class HeroesRegistry {
    private Map<String, Message> mapWithHeroes;

    private HeroesRegistry() {
    }

    public Map<String, Message> getMapWithHeroes() {
        return mapWithHeroes;
    }

    public void setMapWithHeroes(Map<String, Message> mapWithHeroes) {
        this.mapWithHeroes = mapWithHeroes;
    }


    public static HeroesRegistry getINSTANCE() {
        return Helper.INSTANCE;
    }

    private static class Helper {
        private final static HeroesRegistry INSTANCE = new HeroesRegistry();
    }
}
