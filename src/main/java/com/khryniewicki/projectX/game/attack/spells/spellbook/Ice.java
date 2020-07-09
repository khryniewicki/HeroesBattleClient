package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Ice extends Spell {

    public Ice() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("IceBolt");
        setBasic(false);
        setCastingSpeed(0.3f);
        setSpellDuration(6000L);
        setPowerAttack(20);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.ICEBALL);
        setConsumedSpellTexture(SpellUtil.ICE);
    }

}
