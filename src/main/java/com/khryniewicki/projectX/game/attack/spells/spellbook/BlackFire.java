package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.utils.SpellUtil;


public class BlackFire extends Spell {

    public BlackFire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("BlackFireBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.RED);
        setConsumedSpellTexture(SpellUtil.BLUE);
        setSIZE(2.0f);
        setIcon(SpellUtil.FIREICON);
        setFadedIcon(SpellUtil.FIREICONFADED);
    }

}

