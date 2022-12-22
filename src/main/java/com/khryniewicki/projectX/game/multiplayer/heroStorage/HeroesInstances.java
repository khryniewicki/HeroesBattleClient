package com.khryniewicki.projectX.game.multiplayer.heroStorage;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellMock;
import com.khryniewicki.projectX.game.control.settings.ControlSettings;
import com.khryniewicki.projectX.game.heroes.character.properties.HeroAttributes;
import com.khryniewicki.projectX.game.heroes.character.properties.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketController;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.services.dto.MessageDto;
import com.khryniewicki.projectX.services.receive.ReceiveServiceSingleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service
@Getter
@Setter
@Slf4j
public class HeroesInstances {

    private SuperHero hero;
    private SuperHero mock;
    private String heroName;
    private String heroType;
    private String mockName;
    private final HeroFactory heroFactory;
    private final Channels channels;

    private HeroesInstances() {
        heroFactory = HeroFactory.getInstance();
        channels = Channels.getINSTANCE();
    }

    public void setHero() {
        setBasicProperties(hero);
        setHeroMoveSetting();
    }

    public void setBasicProperties(SuperHero superHero) {
        if (superHero.equals(hero)) {
            superHero.setStartingPosition(HeroStartingPosition.getInstance());
            superHero.setBasicSpell(new Spell(superHero.getBasicSpellInstance()));
            superHero.setUltimateSpell(new Spell(superHero.getUltimateSpellInstance()));
            superHero.setHeroAttributes(new HeroAttributes(superHero, hero_name()));
        } else {
            superHero.setStartingPosition(MockStartingPosition.getInstance());
            superHero.setBasicSpell(new SpellMock(superHero.getBasicSpellInstance()));
            superHero.setUltimateSpell(new SpellMock(superHero.getUltimateSpellInstance()));
            superHero.setHeroAttributes(new HeroAttributes(superHero, mock_name()));
        }
        superHero.setMesh();
    }

    public void setHeroMoveSetting() {
        hero.setControlSettings(new ControlSettings());
    }

    private String mock_name() {
        return (Objects.nonNull(mockName)) ? mockName : "Player " + channels.getTopic();
    }

    private String hero_name() {
        return (Objects.nonNull(heroName)) ? heroName : "Player " + channels.getApp();
    }


    public void setMock() {
        WebsocketController websocketInstance = WebsocketController.getWebsocketInstance();
        String sessionId = websocketInstance.get_session_id();
        HeroesRegistry heroesRegistry = HeroesRegistry.getINSTANCE();
        Map<String, MessageDto> heroes = heroesRegistry.getHeroesRegistryBook();

        for (Map.Entry<String, MessageDto> hero : heroes.entrySet()) {
            String key = hero.getKey();
            if (!key.equals(sessionId)) {
                MessageDto messageDto = hero.getValue();
                String heroType = messageDto.getContent();
                setMock(heroType);
                setMockName(messageDto.getPlayerName());
                setBasicProperties(mock);
            }
        }
    }

    public void set_hero_type(String heroType) {
        this.heroType = heroType;
        setHero(heroType);
    }

    public void setHero(String heroType) {
        hero = heroFactory.create(heroType);
    }

    public void setMock(String mockType) {
        mock = new HeroMock(heroFactory.create(mockType));
    }

    public void reset() {
        mock = null;
        hero = null;
        ReceiveServiceSingleton receiveServiceSingleton=ReceiveServiceSingleton.getInstance();
        receiveServiceSingleton.reset();
    }

    public static HeroesInstances getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        public static final HeroesInstances INSTANCE = new HeroesInstances();
    }
}
