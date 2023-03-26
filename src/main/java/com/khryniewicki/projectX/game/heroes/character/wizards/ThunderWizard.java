package com.khryniewicki.projectX.game.heroes.character.wizards;


import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class ThunderWizard<T extends Spell, R extends Spell> extends SuperHero<T, R> {

    public ThunderWizard(T basicSpell, R ultimateSpell) {
        super(basicSpell, ultimateSpell);
        setPosition();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroTextures.THUNDER_WIZARD_IDLE);
        setHeroIdle(HeroTextures.THUNDER_WIZARD_IDLE);
        setHeroRight(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroLeft(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroUp(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroDown(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroAttack(HeroTextures.THUNDER_WIZARD_ATTACK);
    }

    @Override
    public void setProperties() {
        setName("Thunder Wizard");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(135f);
        setLife(65);
        setManaRegeneration(1f);
    }
}
