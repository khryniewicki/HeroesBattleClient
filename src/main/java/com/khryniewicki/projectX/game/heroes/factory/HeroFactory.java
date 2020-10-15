package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.heroes.character.fallens.FallenKing;
import com.khryniewicki.projectX.game.heroes.character.fallens.FallenMonk;
import com.khryniewicki.projectX.game.heroes.character.fallens.FallenWitcher;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.wizards.FireWizard;
import com.khryniewicki.projectX.game.heroes.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.wizards.ThunderWizard;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HeroFactory {

    private Map<String, CharacterFactory> heroesFactory;

    public HeroFactory() {
        initMap();
    }

    public void initMap() {
        this.heroesFactory = new HashMap<>();
        this.heroesFactory.put("FireWizard", FireWizard::new);
        this.heroesFactory.put("IceWizard", IceWizard::new);
        this.heroesFactory.put("ThunderWizard", ThunderWizard::new);
        this.heroesFactory.put("FallenWitcher", FallenWitcher::new);
        this.heroesFactory.put("FallenMonk", FallenMonk::new);
        this.heroesFactory.put("FallenKing", FallenKing::new);
    }

    public SuperHero create(String character) {
        if (heroesFactory.containsKey(character)) {
            return heroesFactory.get(character).create();
        }
        throw new UnsupportedOperationException("Postać nie została znaleziona");
    }
}
