package com.khryniewicki.projectX.game.heroes.character.wizards;

import com.khryniewicki.projectX.game.attack.spell.spellbook.fire.Fire;
import com.khryniewicki.projectX.game.attack.spell.spellbook.fire.FireBomb;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class FireWizard<T extends Fire, R extends FireBomb> extends SuperHero<T, R> {

    public FireWizard(T basicSpell, R ultimateSpell) {
        super(basicSpell, ultimateSpell);
        setPosition();
        setMesh();
        setTexture();
        setProperties();
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
