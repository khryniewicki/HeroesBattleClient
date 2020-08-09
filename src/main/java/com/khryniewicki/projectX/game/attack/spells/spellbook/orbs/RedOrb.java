package com.khryniewicki.projectX.game.attack.spells.spellbook.orbs;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class RedOrb extends Spell {

    public RedOrb() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("RedOrbBall");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.BLOODORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellUtil.REDORB, 1.0f));
        setIcon(SpellUtil.REDORBICON);
        setFadedIcon(SpellUtil.REDORBICONFADED);
    }

}

