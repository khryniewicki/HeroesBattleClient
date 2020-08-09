package com.khryniewicki.projectX.game.attack.spells.spellbook.wind;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Storm extends Spell {

    public Storm() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Storm");
        setBasic(true);
        setCastingSpeed(0.20f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.WINDORB2, 0.5f));
        setExecutedSpell(new SpellTexture(SpellUtil.STORM, 2.0f));
        setIcon(SpellUtil.WINDICON);
        setFadedIcon(SpellUtil.WINDICONFADED);
    }

}

