package com.khryniewicki.projectX.game.Character;

import com.khryniewicki.projectX.game.Attack.Fire;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.KnightIMG;
import lombok.Data;

@Data
public class Hero extends SuperHero {

    public Hero() {
        setPosition();
        setTexture();
        setSpell(new Fire());
        setMesh(createHero());
    }

    private void setTexture() {
        setHeroIdle(KnightIMG.SILVER_KNIGHT_IDLE);
        setHeroRight(KnightIMG.SILVER_KNIGHT_RUN_0);
        setHeroLeft(KnightIMG.SILVER_KNIGHT_RUN_0);
        setHeroUp(KnightIMG.SILVER_KNIGHT_RUN_0);
        setHeroDown(KnightIMG.SILVER_KNIGHT_RUN_0);
    }

    private void setPosition() {
        setPosition(new Vector());
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
    }
}

