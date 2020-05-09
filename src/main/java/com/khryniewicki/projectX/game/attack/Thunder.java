package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.SpellUtil;

public class Thunder extends Spell {

    public Thunder() {
        setPosition();
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
    public void setPosition() {
        setPosition(new Vector());
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
        setPositionZ(0.1f);
    }
}
