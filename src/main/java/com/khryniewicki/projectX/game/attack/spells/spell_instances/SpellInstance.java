package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.graphics.Texture;

public interface SpellInstance  {

    Texture getThrowingSpellTexture();
    Texture getConsumedSpellTexture();
    Integer getPowerAttack();
    Integer getManaConsumed();
    Float getCastingSpeed();
    Spell getSpell();
    Long getSpellDuration();
    String getName();
    boolean isBasic();


}
