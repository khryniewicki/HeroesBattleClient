package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Fire extends Spell {

    public Fire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("FireBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.FIREBALL);
        setConsumedSpellTexture(SpellUtil.FIRE);
    }

}

