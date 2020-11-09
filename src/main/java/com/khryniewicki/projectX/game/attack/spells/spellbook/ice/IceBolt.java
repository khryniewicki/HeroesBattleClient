package com.khryniewicki.projectX.game.attack.spells.spellbook.ice;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class IceBolt extends Spell {

    public  IceBolt() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("IceBolt");
        setBasic(true);
        setCastingSpeed(0.05f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.ICEBALL, 0.7f));
        setExecutedSpell(new SpellTexture(SpellUtil.ICE, 0.8f));
        setIcon(SpellUtil.ICEICON);
        setFadedIcon(SpellUtil.ICEICONFADED);
    }

}
