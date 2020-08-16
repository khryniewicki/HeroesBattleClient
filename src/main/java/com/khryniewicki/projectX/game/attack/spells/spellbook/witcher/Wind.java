package com.khryniewicki.projectX.game.attack.spells.spellbook.witcher;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Wind extends Spell {

    public Wind() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("WindBall");
        setBasic(false);
        setCastingSpeed(0.20f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.WINDORB2, 0.7f));
        setExecutedSpell(new SpellTexture(SpellUtil.WINDATTACK, 1.6f));
        setIcon(SpellUtil.WINDICON);
        setFadedIcon(SpellUtil.WINDICONFADED);
    }

}

