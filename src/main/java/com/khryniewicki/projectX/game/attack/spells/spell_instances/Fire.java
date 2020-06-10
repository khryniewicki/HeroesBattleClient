package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.math.Vector;
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
        setPowerAttack(10);
        setManaConsumed(10);
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
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        setPositionX(heroStartingPosition.getX());
        setPositionY(heroStartingPosition.getY());
        setPositionZ(0.1f);
    }
}

