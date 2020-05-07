package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Ice extends Spell {
    public Ice() {
        setProperties();
        setTexture();
        setMesh();
    }

    @Override
    public void setProperties() {
        setName("IceBolt");
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
//        setSIZE(0.8f);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.ICEBALL);
        setConsumedSpellTexture(SpellUtil.ICE);
    }

    @Override
    public void setMesh() {
        setMesh(createSpell());
    }

    @Override
    public void setThrowingSpellTexture() {
        setTexture(SpellUtil.ICEBALL);
    }

    @Override
    public void setConsumedSpellTexture() {
        setTexture(SpellUtil.ICE);
    }

}
