package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.Fire;
import com.khryniewicki.projectX.game.attack.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
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
        setMesh(createHero());
        setName("IceWizard");

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
        setPositionX(GameUtill.mockStartingPositionX);
        setPositionY(GameUtill.mockStartingPositionY);
    }
    @Override
    public void setProperties() {
        setName("FireWizard");
    }
}
