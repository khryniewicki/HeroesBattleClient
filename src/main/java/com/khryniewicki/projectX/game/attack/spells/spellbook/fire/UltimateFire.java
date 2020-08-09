package com.khryniewicki.projectX.game.attack.spells.spellbook.fire;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class UltimateFire extends Spell {

    public UltimateFire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("FireBall");
        setBasic(false);
        setCastingSpeed(0.2f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.FIREORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellUtil.FIREBALL, 1.7f));
        setIcon(SpellUtil.FIREICON);
        setFadedIcon(SpellUtil.FIREICONFADED);

    }

}

