package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellMock;
import com.khryniewicki.projectX.game.control_settings.keyboard_settings.MoveSettings;
import com.khryniewicki.projectX.game.heroes.character.properties.*;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Message;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service
@Data
public class HeroesInstances {

    private SuperHero hero;
    private SuperHero mock;
    private String heroName="Konrad";
    private String mockName="Malanix";
    private final HeroFactory heroFactory;


    private HeroesInstances() {
        heroFactory = HeroFactory.getInstance();
    }

    public void setHeroBasicProperties() {
        setBasicProperties(hero);
        setHeroMoveSetting();
    }

    public void setBasicProperties(SuperHero superHero) {
        if (superHero.equals(hero)) {
            superHero.setStartingPosition(HeroStartingPosition.getInstance());
            superHero.setBasicSpell(new Spell(superHero.getBasicSpellInstance()));
            superHero.setUltimateSpell(new Spell(superHero.getUltimateSpellInstance()));
            superHero.setHeroAttributes(new HeroAttributes(superHero, heroName));
        } else {
            superHero.setStartingPosition(MockStartingPosition.getInstance());
            superHero.setBasicSpell(new SpellMock(superHero.getBasicSpellInstance()));
            superHero.setUltimateSpell(new SpellMock(superHero.getUltimateSpellInstance()));
            superHero.setHeroAttributes(new HeroAttributes(superHero, mockName));
        }
        superHero.setMesh();
    }

    public void setHeroMoveSetting() {
        hero.setMoveSettings(MoveSettings.getInstance());
    }

    public void setMock() {
        WebsocketInitializer websocketInstance = WebsocketInitializer.getWebsocketInstance();
        String sessionId = websocketInstance.getSessionId();

        HeroesRegistry heroesRegistry = HeroesRegistry.getINSTANCE();
        Map<String, Message> heroes = heroesRegistry.getHeroesRegistryBook();

        for (Map.Entry<String, Message> hero : heroes.entrySet()) {
            String key = hero.getKey();
            if (!key.equals(sessionId)) {
                String heroType = hero.getValue().getContent();
                setMock(heroType);
                setBasicProperties(mock);
            }
        }
    }


    public void setHero(String heroType) {
        hero = heroFactory.create(heroType);
    }

    public void setMock(String mockName) {
        mock = new HeroMock(heroFactory.create(mockName));
    }

    public static HeroesInstances getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        public static final HeroesInstances INSTANCE = new HeroesInstances();
    }
}
