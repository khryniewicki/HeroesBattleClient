package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Ice extends Spell {
    public Ice() {
        setPosition();
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
    public void setPosition() {
        setPosition(new Vector());
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
        setPositionZ(0.1f);
    }
}
