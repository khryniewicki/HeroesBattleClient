package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;

public interface CharacterFactory {
    SuperHero<Spell, Spell> create();
}
