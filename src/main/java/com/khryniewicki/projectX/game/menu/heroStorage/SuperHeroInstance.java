package com.khryniewicki.projectX.game.menu.heroStorage;

import com.khryniewicki.projectX.config.Message;
import com.khryniewicki.projectX.game.heroes.Factory.WizardFactory;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.menu.WebsocketInitializer;

import java.util.Map;


public class SuperHeroInstance {
    private SuperHero hero;
    private SuperHero mock;

    private SuperHeroInstance() {
    }
    public static SuperHeroInstance getInstance() {
        return HELPER.INSTANCE;
    }


    public SuperHero getHero() {
        return hero;
    }
    public SuperHero getMock() {
        return mock;
    }

    public void setHero(SuperHero hero) {
        this.hero = hero;
    }

    public void setMock() {
        WebsocketInitializer websocketInstance = WebsocketInitializer.getWebsocketInstance();

        MapWithHeroes instance = MapWithHeroes.getINSTANCE();

        String sessionId = websocketInstance.getSessionId();
        Map<String, Message> mapWithHeroes = instance.getMapWithHeroes();

        for (Map.Entry<String, Message> hero : mapWithHeroes.entrySet()) {
            if (!hero.getKey().equals(sessionId)) {
                String heroType = hero.getValue().getContent();
                this.mock = new WizardFactory().createWizard(heroType);
            }
        }
    }


    private static class HELPER {

        public static final SuperHeroInstance INSTANCE = new SuperHeroInstance();

    }
}
