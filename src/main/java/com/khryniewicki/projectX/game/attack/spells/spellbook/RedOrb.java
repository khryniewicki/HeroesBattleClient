package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.utils.SpellUtil;


public class RedOrb extends Spell {

    public RedOrb() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("RedOrbBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.FIRE);
        setConsumedSpellTexture(SpellUtil.FIREBALL);
        setIcon(SpellUtil.FIREICON);
        setFadedIcon(SpellUtil.FIREICONFADED);
    }

}

