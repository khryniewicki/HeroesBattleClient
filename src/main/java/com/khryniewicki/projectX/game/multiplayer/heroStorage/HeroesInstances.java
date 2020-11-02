package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellMock;
import com.khryniewicki.projectX.game.heroes.character.properties.*;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.control_settings.keyboard_settings.MoveSettings;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Message;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Data
public class HeroesInstances {

    private SuperHero hero;
    private UltraHero mock;
    private final HeroFactory heroFactory;
    private String heroType;

    private HeroesInstances() {
        heroFactory = HeroFactory.getInstance();
    }

    public void setHeroBasicProperties() {
        setBasicProperties(hero);
        setHeroMoveSetting();
    }

    public void setBasicProperties(UltraHero ultraHero) {
        ultraHero.setLifeBar(new LifeBar(ultraHero));
        ultraHero.setManaBar(new ManaBar(ultraHero));

        if (ultraHero.equals(hero)) {
            ultraHero.setStartingPosition(HeroStartingPosition.getInstance());
            ultraHero.setBasicSpell(new Spell(ultraHero.getBasicSpellInstance()));
            ultraHero.setUltimateSpell(new Spell(ultraHero.getUltimateSpellInstance()));
        } else {
            ultraHero.setStartingPosition(MockStartingPosition.getInstance());
            ultraHero.setBasicSpell(new SpellMock(ultraHero.getBasicSpellInstance()));
            ultraHero.setUltimateSpell(new SpellMock(ultraHero.getUltimateSpellInstance()));
        }
        ultraHero.setMesh();
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


    public void setHero() { hero = heroFactory.create(heroType); }

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
