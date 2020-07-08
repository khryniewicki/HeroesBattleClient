package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Thunder extends Spell {

    public Thunder() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("ThunderBolt");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setSIZE(1.0f);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.THUNDERBALL);
        setConsumedSpellTexture(SpellUtil.THUNDER);
    }

}
