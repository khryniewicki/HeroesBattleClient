package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.Fire;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;

@Data
public class FireWizard extends SuperHero {

    public FireWizard() {
        setPosition();
        setSpell(new Fire());
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.FIRE_WIZARD_IDLE);
        setHeroIdle(HeroUtil.FIRE_WIZARD_IDLE);
        setHeroRight(HeroUtil.FIRE_WIZARD_RUN);
        setHeroLeft(HeroUtil.FIRE_WIZARD_RUN);
        setHeroUp(HeroUtil.FIRE_WIZARD_RUN);
        setHeroDown(HeroUtil.FIRE_WIZARD_RUN);
    }

    @Override
    public void setPosition() {
        setPosition(new Vector());
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
    }

    @Override
    public void setProperties() {
        setName("FireWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
        setSIZE(0.9f);
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }

    @Override
    public void setTextureRun() {
        setTexture(HeroUtil.FIRE_WIZARD_RUN);
    }
}
