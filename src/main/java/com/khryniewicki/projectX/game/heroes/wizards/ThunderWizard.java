package com.khryniewicki.projectX.game.heroes.wizards;


import com.khryniewicki.projectX.game.attack.spells.spell_instances.Thunder;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
public class ThunderWizard extends SuperHero {


    public ThunderWizard() {
        setPosition();
        setSpell(new Thunder());
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.THUNDER_WIZARD_IDLE);
        setHeroIdle(HeroUtil.THUNDER_WIZARD_IDLE);
        setHeroRight(HeroUtil.THUNDER_WIZARD_RUN);
        setHeroLeft(HeroUtil.THUNDER_WIZARD_RUN);
        setHeroUp(HeroUtil.THUNDER_WIZARD_RUN);
        setHeroDown(HeroUtil.THUNDER_WIZARD_RUN);
    }

    @Override
    public void setPosition() {
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        setPosition(new Vector());
        setPositionX(heroStartingPosition.getX());
        setPositionY(heroStartingPosition.getY());
    }

    @Override
    public void setProperties() {
        setName("ThunderWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }

    @Override
    public void setTextureRun() {
        setTexture(HeroUtil.THUNDER_WIZARD_RUN);
    }
}
