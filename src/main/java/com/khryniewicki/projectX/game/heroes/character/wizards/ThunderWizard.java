package com.khryniewicki.projectX.game.heroes.character.wizards;


import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Lightning;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Thunderbolt;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


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
        setTexture(HeroTextures.THUNDER_WIZARD_IDLE);
        setHeroIdle(HeroTextures.THUNDER_WIZARD_IDLE);
        setHeroRight(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroLeft(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroUp(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroDown(HeroTextures.THUNDER_WIZARD_RUN);
        setHeroAttack(HeroTextures.THUNDER_WIZARD_ATTACK);
    }


    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new Thunderbolt()));
        setUltimateSpellInstance(new UltimateSpellInstance(new Lightning()));
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
