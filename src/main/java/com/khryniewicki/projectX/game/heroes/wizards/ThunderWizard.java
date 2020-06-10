package com.khryniewicki.projectX.game.heroes.wizards;


import com.khryniewicki.projectX.game.attack.spells.spell_instances.Thunder;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;

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
        setPosition(new Vector());
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        setPositionX(heroStartingPosition.getX());
        setPositionY(heroStartingPosition.getY());
        setMana(100f);
        setLife(100f);
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
