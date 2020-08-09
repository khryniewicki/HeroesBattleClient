package com.khryniewicki.projectX.game.attack.spells.spellbook.black;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class BlackFire extends Spell {

    public BlackFire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("BlackFireBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.BLACKORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellUtil.BLACKFIRE, 1.2f));
        setIcon(SpellUtil.BLACKFIREICON);
        setFadedIcon(SpellUtil.BLACKFIREICONFADED);
    }

}

