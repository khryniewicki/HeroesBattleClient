package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Fire extends Spell {

    public Fire() {
        setThrowingSpellTexture(SpellUtil.FIREBALL);
        setConsumedSpellTexture(SpellUtil.FIRE);
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
        setMesh(createSpell());
    }

}

