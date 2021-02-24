package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.Texture;

public interface SpellInstance  {

    SpellTexture getThrowingSpellTexture();
    SpellTexture getConsumedSpellTexture();
    Texture getIcon();
    Texture getFadedIcon();
    Integer getPowerAttack();
    Integer getManaConsumed();
    Float getCastingSpeed();
    Spell getSpell();
    Long getSpellDuration();
    String getName();
    boolean isBasic();

}
