package com.khryniewicki.projectX.game.attack.spell_group.spells;

import com.khryniewicki.projectX.game.attack.spell_group.Spell;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
public class Fire extends Spell {

    public Fire() {
        setPosition();
        setProperties();
        setTexture();
        setMesh();
    }

    @Override
    public void setProperties() {
        setName("FireBall");
        setCastingSpeed(0.2f);
        setSpellDuration(4000L);
        setThrowingSpellSize(0.8f);
        setConsumedSpellSize(1.5f);
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
    public void setPosition() {
        setPosition(new Vector());
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
        setPositionZ(0.1f);
        setSIZE(1.5f);
    }
}

