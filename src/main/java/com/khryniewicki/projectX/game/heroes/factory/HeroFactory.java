package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.heroes.character.fallens.FallenKing;
import com.khryniewicki.projectX.game.heroes.character.fallens.FallenMonk;
import com.khryniewicki.projectX.game.heroes.character.fallens.FallenWitcher;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.wizards.FireWizard;
import com.khryniewicki.projectX.game.heroes.character.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.character.wizards.ThunderWizard;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Data
public class HeroFactory {

    private static Map<String, CharacterFactory> heroesFactory;

    private HeroFactory() {
        heroesFactory = new HashMap<>();
        initMap();
    }

    public void initMap() {
        new SuperHero();
        heroesFactory.put("FireWizard", FireWizard::new);
        heroesFactory.put("IceWizard", IceWizard::new);
        heroesFactory.put("ThunderWizard", ThunderWizard::new);
        heroesFactory.put("FallenWitcher", FallenWitcher::new);
        heroesFactory.put("FallenMonk", FallenMonk::new);
        heroesFactory.put("FallenKing", FallenKing::new);
    }

    public SuperHero create(String character) {

        if (heroesFactory.containsKey(character)) {
            return heroesFactory.get(character).create();
        }
        throw new UnsupportedOperationException("Postać nie została znaleziona");
    }

    public static HeroFactory getInstance() {
        return HeroFactory.HELPER.INSTANCE;
    }

    private static class HELPER {
        public static final HeroFactory INSTANCE = new HeroFactory();
    }
}
