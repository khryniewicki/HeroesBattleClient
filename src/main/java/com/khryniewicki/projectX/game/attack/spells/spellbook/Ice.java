package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;
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
        setPowerAttack(10);
        setManaConsumed(10);
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
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        setPositionX(heroStartingPosition.getX());
        setPositionY(heroStartingPosition.getY());
        setPositionZ(-0.1f);
    }
}
