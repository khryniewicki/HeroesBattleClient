package com.khryniewicki.projectX.game.multiplayer.heroStorage;


import com.khryniewicki.projectX.services.dto.MessageDto;
import java.util.Map;

public class HeroesRegistry {
    private Map<String, MessageDto> heroesRegistryBook;

    private HeroesRegistry() {
    }

    public Map<String, MessageDto> getHeroesRegistryBook() {
        return heroesRegistryBook;
    }

    public void setHeroesRegistryBook(Map<String, MessageDto> heroesRegistryBook) {
        this.heroesRegistryBook = heroesRegistryBook;
    }


    public static HeroesRegistry getINSTANCE() {
        return Helper.INSTANCE;
    }

    private static class Helper {
        private final static HeroesRegistry INSTANCE = new HeroesRegistry();
    }
}
