package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;

@Data
public class IceWizard extends SuperHero {

    public IceWizard() {
        setPosition();
        setTexture();
        setSpell(new Ice());
        setMesh();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.ICE_WIZARD_IDLE);
        setHeroIdle(HeroUtil.ICE_WIZARD_IDLE);
        setHeroRight(HeroUtil.ICE_WIZARD_RUN);
        setHeroLeft(HeroUtil.ICE_WIZARD_RUN);
        setHeroUp(HeroUtil.ICE_WIZARD_RUN);
        setHeroDown(HeroUtil.ICE_WIZARD_RUN);
    }


    @Override
    public void setPosition() {
        setPosition(new Vector());
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        setPositionX(heroStartingPosition.getX());
        setPositionY(heroStartingPosition.getY());
    }

    @Override
    public void setProperties() {
        setName("IceWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }


    @Override
    public void setTextureRun() {
        setTexture(HeroUtil.ICE_WIZARD_RUN);
    }
}
