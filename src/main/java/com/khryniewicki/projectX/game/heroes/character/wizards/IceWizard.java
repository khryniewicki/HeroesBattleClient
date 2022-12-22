package com.khryniewicki.projectX.game.heroes.character.wizards;

import com.khryniewicki.projectX.game.attack.spell.instance.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spell.instance.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spell.spellbook.ice.IceBerg;
import com.khryniewicki.projectX.game.attack.spell.spellbook.ice.IceBolt;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class IceWizard extends SuperHero {

    public IceWizard() {
        setPosition();
        setTexture();
        setSpellBasis();
        setMesh();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroTextures.ICE_WIZARD_IDLE);
        setHeroIdle(HeroTextures.ICE_WIZARD_IDLE);
        setHeroRight(HeroTextures.ICE_WIZARD_RUN);
        setHeroLeft(HeroTextures.ICE_WIZARD_RUN);
        setHeroUp(HeroTextures.ICE_WIZARD_RUN);
        setHeroDown(HeroTextures.ICE_WIZARD_RUN);
        setHeroAttack(HeroTextures.ICE_WIZARD_ATTACK);
    }

    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new IceBolt()));
        setUltimateSpellInstance(new UltimateSpellInstance(new IceBerg()));
    }

    @Override
    public void setProperties() {
        setName("Ice Wizard");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(130f);
        setLife(70);
        setManaRegeneration(1f);
    }
}
