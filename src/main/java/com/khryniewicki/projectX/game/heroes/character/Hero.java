package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.Fire;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;

@Data
public class Hero extends SuperHero {

    public Hero() {
        setPosition();
        setTexture();
        setSpell(new Fire());
        setMesh(createHero());
    }

    public void setTexture() {
        setHeroIdle(HeroUtil.SILVER_KNIGHT_IDLE);
        setHeroRight(HeroUtil.SILVER_KNIGHT_RUN_0);
        setHeroLeft(HeroUtil.SILVER_KNIGHT_RUN_0);
        setHeroUp(HeroUtil.SILVER_KNIGHT_RUN_0);
        setHeroDown(HeroUtil.SILVER_KNIGHT_RUN_0);
    }

    public void setPosition() {
        setPosition(new Vector());
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
    }
}

