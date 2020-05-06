package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Ice extends Spell {
    public Ice() {
        setThrowingSpellTexture(SpellUtil.ICEBALL);
        setConsumedSpellTexture(SpellUtil.ICE);
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
        setSIZE(0.8f);
        setMesh(createSpell());
    }
}
