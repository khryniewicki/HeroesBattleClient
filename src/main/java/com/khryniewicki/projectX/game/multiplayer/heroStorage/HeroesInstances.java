package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellMock;
import com.khryniewicki.projectX.game.heroes.character.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.game.heroes.character.properties.MoveSettings;
import com.khryniewicki.projectX.game.heroes.factory.CharacterFactory;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.websocket.messages.Message;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Data
public class HeroesInstances {

    private SuperHero hero;
    private UltraHero mock;
    private final HeroFactory heroFactory;

    private HeroesInstances() {
        heroFactory = new HeroFactory();
    }


    public void setHero() {
        this.hero = heroFactory.create(MultiplayerController.inputText);
    }

    public void setHeroBasicProperties() {
        setBasicProperties(hero);
        setHeroMoveSetting();
    }

    public void setBasicProperties(UltraHero ultraHero) {
        ultraHero.setLifeBar(new LifeBar(ultraHero));
        ultraHero.setManaBar(new ManaBar(ultraHero));

        if (ultraHero.equals(hero)){
            ultraHero.setStartingPosition(HeroStartingPosition.getInstance());
            ultraHero.setBasicSpell(new Spell(ultraHero.getBasicSpellInstance()));
            ultraHero.setUltimateSpell(new Spell(ultraHero.getUltimateSpellInstance()));
        } else{
            ultraHero.setStartingPosition(MockStartingPosition.getInstance());
            ultraHero.setBasicSpell(new SpellMock(ultraHero.getBasicSpellInstance()));
            ultraHero.setUltimateSpell(new SpellMock(ultraHero.getUltimateSpellInstance()));
        }
            ultraHero.setMesh();
    }

    public void setHeroMoveSetting(){
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
                SuperHero superHero = heroFactory.create(heroType);
                this.mock = new HeroMock(superHero);
                setBasicProperties(mock);
            }
        }
    }

    public static HeroesInstances getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        public static final HeroesInstances INSTANCE = new HeroesInstances();
    }
}
