package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class FallenWitcher<T extends Spell, R extends Spell> extends SuperHero<T, R>{

    public FallenWitcher(T basicSpell, R ultimateSpell) {
        super(basicSpell, ultimateSpell);
        setPosition();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroTextures.FALLENIDLE1);
        setHeroIdle(HeroTextures.FALLENIDLE1);
        setHeroRight(HeroTextures.FALLENRUN1);
        setHeroLeft(HeroTextures.FALLENRUN1);
        setHeroUp(HeroTextures.FALLENRUN1);
        setHeroDown(HeroTextures.FALLENRUN1);
        setHeroAttack(HeroTextures.FALLENATTACK1);
    }


    @Override
    public void setProperties() {
        setName("Fallen Witcher");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setHero_right_offset(0.2f);
        setMana(80f);
        setLife(160);
        setSIZE(1.1F);
        setManaRegeneration(0.5f);
    }


}
