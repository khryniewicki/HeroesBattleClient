package com.khryniewicki.projectX.game.heroes.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


public class FireWizard extends SuperHero {

    public FireWizard() {
        setPosition();
        setSpellBasis();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpellBasis() {
        setBasicSpell(new BasicSpell(new Fire()));
        setUltimateSpell(new UltimateSpell(new Ice()));
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
    public void setProperties() {
        setName("FireWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);

    }

    @Override
    public void setHeroIdle() {
        setTexture(HeroUtil.FIRE_WIZARD_IDLE);
    }

    @Override
    public void setHeroRun() {
        setTexture(HeroUtil.FIRE_WIZARD_RUN);
    }
}
