package com.khryniewicki.projectX.game.attack.spells.spellbook.thunder;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class VioletMissle extends Spell {

    public VioletMissle() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("VioletMissle");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setSIZE(1.0f);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.THUNDERORB, 0.8f));
        setExecutedSpell(new SpellTexture(SpellUtil.VIOLETMISSLE, 2.5f));
        setIcon(SpellUtil.THUNDERICON);
        setFadedIcon(SpellUtil.THUNDERICONFADED);
    }

}
