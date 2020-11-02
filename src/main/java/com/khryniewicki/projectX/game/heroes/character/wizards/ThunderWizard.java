package com.khryniewicki.projectX.game.heroes.character.wizards;


import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Thunder;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.VioletMissle;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


public class ThunderWizard extends SuperHero {

    public ThunderWizard() {
        setPosition();
        setSpellBasis();
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
        setHeroAttack(HeroUtil.THUNDER_WIZARD_ATTACK);
    }


    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new VioletMissle()));
        setUltimateSpellInstance(new UltimateSpellInstance(new Thunder()));
    }

    @Override
    public void setProperties() {
        setName("ThunderWizard");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);
    }

}
