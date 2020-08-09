package com.khryniewicki.projectX.game.attack.spells.spellbook.thunder;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Thunder extends Spell {

    public Thunder() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("ThunderBolt");
        setBasic(false);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setSIZE(1.0f);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.THUNDERBALL2, 1.5f));
        setExecutedSpell(new SpellTexture(SpellUtil.THUNDER, 1.7f));
        setIcon(SpellUtil.THUNDERICON);
        setFadedIcon(SpellUtil.THUNDERICONFADED);
    }

}
