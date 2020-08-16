package com.khryniewicki.projectX.game.attack.spells.spellbook.fire;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Fire extends Spell {

    public Fire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Fire");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.FIREORB, 0.3f));
        setExecutedSpell(new SpellTexture(SpellUtil.FIRE, 1.2f));
        setIcon(SpellUtil.ULTIMATEFIREICON);
        setFadedIcon(SpellUtil.ULTIMATEFIREICONFADED);

    }

}

