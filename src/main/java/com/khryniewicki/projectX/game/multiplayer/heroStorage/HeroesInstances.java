package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.game.websocket.messages.Message;
import com.khryniewicki.projectX.game.heroes.character.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.game.heroes.character.properties.Move;
import com.khryniewicki.projectX.game.heroes.factory.CharacterFactory;
import com.khryniewicki.projectX.game.heroes.factory.WizardFactory;
import com.khryniewicki.projectX.game.multiplayer.MultiplayerController;
import com.khryniewicki.projectX.game.websocket.WebsocketInitializer;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class HeroesInstances {
    private SuperHero hero;
    private UltraHero mock;
    private CharacterFactory characterFactory;

    private HeroesInstances() {
        characterFactory = new WizardFactory();
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
        this.hero = characterFactory.create(MultiplayerController.inputText);
    }

    public void setHeroLifeManaMove() {
        hero.setLifeBar(new LifeBar(hero));
        hero.setManaBar(new ManaBar(hero));
        hero.setMove(Move.getInstance());
    }


    public void setMock() {
        WebsocketInitializer websocketInstance = WebsocketInitializer.getWebsocketInstance();
        HeroesRegistry heroesRegistry1 = HeroesRegistry.getINSTANCE();

        String sessionId = websocketInstance.getSessionId();

        Map<String, Message> heroes = heroesRegistry1.getMapWithHeroes();

        for (Map.Entry<String, Message> hero : heroes.entrySet()) {
            String key = hero.getKey();
            if (!key.equals(sessionId)) {
                String heroType = hero.getValue().getContent();
                SuperHero superHero = characterFactory.create(heroType);
                this.mock = new HeroMock(superHero);
                mock.setLifeBar(new LifeBar(mock));
                mock.setManaBar(new ManaBar(mock));

            }
        }
    }


    private static class HELPER {
        public static final HeroesInstances INSTANCE = new HeroesInstances();
    }
}
