package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
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
        setMissleSpell(new SpellTexture(SpellUtil.ICEBALL, 1.0f));
        setExecutedSpell(new SpellTexture(SpellUtil.ICE, 1.0f));
        setIcon(SpellUtil.ICEICON);
        setFadedIcon(SpellUtil.ICEICONFADED);
    }

}
