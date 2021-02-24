package com.khryniewicki.projectX.game.heroes.character.wizards;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.FireBomb;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


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
        setBasicSpellInstance(new BasicSpellInstance(new Fire()));
        setUltimateSpellInstance(new UltimateSpellInstance(new FireBomb()));
    }

    @Override
    public void setTexture() {
        setTexture(HeroTextures.FIRE_WIZARD_IDLE);
        setHeroIdle(HeroTextures.FIRE_WIZARD_IDLE);
        setHeroRight(HeroTextures.FIRE_WIZARD_RUN);
        setHeroLeft(HeroTextures.FIRE_WIZARD_RUN);
        setHeroUp(HeroTextures.FIRE_WIZARD_RUN);
        setHeroDown(HeroTextures.FIRE_WIZARD_RUN);
        setHeroAttack(HeroTextures.FIRE_WIZARD_ATTACK);
    }


    @Override
    public void setProperties() {
        setName("Fire Wizard");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(140f);
        setLife(60);
        setManaRegeneration(1f);
    }

}
