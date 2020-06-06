package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.config.messageHandler.Message;
import com.khryniewicki.projectX.game.heroes.Factory.WizardFactory;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.WebsocketInitializer;

import java.util.Map;

import static com.khryniewicki.projectX.game.multiplayer.MultiplayerInitializer.getWizardType;


public class HeroesInstances {
    private SuperHero hero;
    private SuperHero mock;

    private HeroesInstances() {
    }
    public static HeroesInstances getInstance() {
        return HELPER.INSTANCE;
    }


    public SuperHero getHero() {
        return hero;
    }
    public SuperHero getMock() {
        return mock;
    }

    public void setHero() {
        this.hero = getWizardType();
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

        public static final HeroesInstances INSTANCE = new HeroesInstances();

    }
}
