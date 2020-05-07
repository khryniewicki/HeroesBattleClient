package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Fire extends Spell {

    public Fire() {
        setProperties();
        setTexture();
        setMesh();
    }

    @Override
    public void setProperties() {
        setName("FireBall");
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.FIREBALL);
        setConsumedSpellTexture(SpellUtil.FIRE);
    }

    @Override
    public void setMesh() {
        setMesh(createSpell());
    }

    @Override
    public void setThrowingSpellTexture() {
        setTexture(SpellUtil.FIREBALL);
    }

    @Override
    public void setConsumedSpellTexture() {
        setTexture(SpellUtil.FIRE);
    }

}

