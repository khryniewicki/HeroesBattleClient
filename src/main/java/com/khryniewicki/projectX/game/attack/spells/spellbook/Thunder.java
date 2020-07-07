package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.utils.SpellUtil;
import lombok.Data;

@Data
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
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setSIZE(1.0f);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setThrowingSpellTexture(SpellUtil.THUNDERBALL);
        setConsumedSpellTexture(SpellUtil.THUNDER);
    }

    @Override
    public void setMesh() {
        setMesh(createMesh());
    }


    @Override
    public void setPosition() {
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        setPositionX(heroStartingPosition.getX());
        setPositionY(heroStartingPosition.getY());
        setPositionZ(-0.1f);
    }
}
