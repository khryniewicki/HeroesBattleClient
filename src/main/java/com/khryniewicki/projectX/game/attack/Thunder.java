package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.utils.SpellUtil;

public class Thunder extends Spell {

    public Thunder() {
        setProperties();
        setTexture();
        setMesh();
    }

    @Override
    public void setProperties() {
        setName("ThunderBolt");
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
        setSIZE(1.0f);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.THUNDERBALL);
        setConsumedSpellTexture(SpellUtil.THUNDER);
    }

    @Override
    public void setMesh() {
        setMesh(createSpell());
    }

    @Override
    public void setThrowingSpellTexture() {
        setTexture(SpellUtil.THUNDERBALL);
    }

    @Override
    public void setConsumedSpellTexture() {
        setTexture(SpellUtil.THUNDER);
    }
}
