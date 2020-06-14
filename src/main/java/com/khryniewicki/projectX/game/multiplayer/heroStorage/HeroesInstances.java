package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.config.messageHandler.Message;
import com.khryniewicki.projectX.game.heroes.character.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.heroes.factory.CharacterFactory;
import com.khryniewicki.projectX.game.heroes.factory.WizardFactory;
import com.khryniewicki.projectX.game.multiplayer.MultiplayerInitializer;
import com.khryniewicki.projectX.game.multiplayer.WebsocketInitializer;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class HeroesInstances {
    private SuperHero hero;
    private UltraHero mock;
    private CharacterFactory characterFactory;
    private HeroStartingPosition heroStartingPosition;
    private HeroesInstances() {
        characterFactory=new WizardFactory();
        heroStartingPosition=HeroStartingPosition.getInstance();
    }
    public static HeroesInstances getInstance() {
        return HELPER.INSTANCE;
    }


    public SuperHero getHero() {
        return hero;
    }
    public UltraHero getMock() {
        return mock;
    }

    public void setHero() {
        this.hero = getWizardType();
    }

    public SuperHero getWizardType() {
        return characterFactory.create(MultiplayerInitializer.inputText);
    }

    public void setMock() {
        WebsocketInitializer websocketInstance = WebsocketInitializer.getWebsocketInstance();

        MapWithHeroes instance = MapWithHeroes.getINSTANCE();

        String sessionId = websocketInstance.getSessionId();
        Map<String, Message> mapWithHeroes = instance.getMapWithHeroes();

        for (Map.Entry<String, Message> hero : mapWithHeroes.entrySet()) {
            if (!hero.getKey().equals(sessionId)) {
                String heroType = hero.getValue().getContent();
                SuperHero superHero = characterFactory.create(heroType);
                this.mock = new HeroMock(superHero);
            }
        }
    }


    private static class HELPER {

        public static final HeroesInstances INSTANCE = new HeroesInstances();

    }
}
