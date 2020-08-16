package com.khryniewicki.projectX.game.attack.spells.spellbook.ice;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class IceBerg extends Spell {

    public IceBerg() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("IceBerg");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(6000L);
        setPowerAttack(20);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.ICEBALL, 1.2f));
        setExecutedSpell(new SpellTexture(SpellUtil.ICEBERG, 1.2f));
        setIcon(SpellUtil.ICEBERGICON);
        setFadedIcon(SpellUtil.ICEBERGFADED);
    }

}
