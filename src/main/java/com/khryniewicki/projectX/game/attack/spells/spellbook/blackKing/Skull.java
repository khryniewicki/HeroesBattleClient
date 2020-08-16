package com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.utils.SpellUtil;


public class Skull extends Spell {

    public Skull() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Skull");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellUtil.SKULL4, 0.7f));
        setExecutedSpell(new SpellTexture(SpellUtil.SKULL3, 1.2f));
        setIcon(SpellUtil.SKULLICON);
        setFadedIcon(SpellUtil.SKULLICONFADED);
    }

}

