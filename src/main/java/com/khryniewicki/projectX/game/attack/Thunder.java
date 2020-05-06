package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.utils.SpellUtil;

public class Thunder extends Spell {

    public Thunder() {
        setThrowingSpellTexture(SpellUtil.THUNDERBALL);
        setConsumedSpellTexture(SpellUtil.THUNDER);
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
        setSIZE(1f);
        setMesh(createSpell());
    }
}
