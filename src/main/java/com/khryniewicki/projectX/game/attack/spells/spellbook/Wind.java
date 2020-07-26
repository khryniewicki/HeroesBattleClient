package com.khryniewicki.projectX.game.attack.spells.spellbook;

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
        setBasic(true);
        setCastingSpeed(0.25f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.WINDORB2, 0.5f));
        setExecutedSpell(new SpellTexture(SpellUtil.WINDATTACK, 1f));
        setIcon(SpellUtil.WINDICON);
        setFadedIcon(SpellUtil.WINDICONFADED);
    }

}

