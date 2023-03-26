package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.spellbook.fire.Fire;
import com.khryniewicki.projectX.game.attack.spell.spellbook.fire.FireBomb;
import com.khryniewicki.projectX.game.attack.spell.spellbook.ice.IceBerg;
import com.khryniewicki.projectX.game.attack.spell.spellbook.ice.IceBolt;
import com.khryniewicki.projectX.game.attack.spell.spellbook.king.BlackFire;
import com.khryniewicki.projectX.game.attack.spell.spellbook.king.SkullCurse;
import com.khryniewicki.projectX.game.attack.spell.spellbook.monk.BloodyWhip;
import com.khryniewicki.projectX.game.attack.spell.spellbook.monk.FrostFury;
import com.khryniewicki.projectX.game.attack.spell.spellbook.thunder.Lightning;
import com.khryniewicki.projectX.game.attack.spell.spellbook.thunder.Thunderbolt;
import com.khryniewicki.projectX.game.attack.spell.spellbook.witcher.ElectricBomb;
import com.khryniewicki.projectX.game.attack.spell.spellbook.witcher.ElectricShock;
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
        new SuperHero<>();
        heroesFactory.put("Fire Wizard", () -> new FireWizard<>(new Fire(), new FireBomb()));
        heroesFactory.put("Ice Wizard", () -> new IceWizard<>(new IceBolt(), new IceBerg()));
        heroesFactory.put("Thunder Wizard", () -> new ThunderWizard<>(new Thunderbolt(), new Lightning()));
        heroesFactory.put("Fallen Witcher", () -> new FallenWitcher<>(new ElectricShock(), new ElectricBomb()));
        heroesFactory.put("Fallen Monk", () -> new FallenMonk<>(new FrostFury(), new BloodyWhip()));
        heroesFactory.put("Fallen King", () -> new FallenKing<>(new BlackFire(), new SkullCurse()));
    }

    public SuperHero<? extends Spell, ? extends Spell> create(String character) {
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
