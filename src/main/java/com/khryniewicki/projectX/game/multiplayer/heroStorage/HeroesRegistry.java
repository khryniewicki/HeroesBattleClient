package com.khryniewicki.projectX.game.multiplayer.heroStorage;


import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Message;
import java.util.Map;

public class HeroesRegistry {
    private Map<String, Message> heroesRegistryBook;

    private HeroesRegistry() {
    }

    public Map<String, Message> getHeroesRegistryBook() {
        return heroesRegistryBook;
    }

    public void setHeroesRegistryBook(Map<String, Message> heroesRegistryBook) {
        this.heroesRegistryBook = heroesRegistryBook;
    }


    public static HeroesRegistry getINSTANCE() {
        return Helper.INSTANCE;
    }

    private static class Helper {
        private final static HeroesRegistry INSTANCE = new HeroesRegistry();
    }
}
