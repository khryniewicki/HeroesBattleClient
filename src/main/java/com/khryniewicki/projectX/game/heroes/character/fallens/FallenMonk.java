package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spell.spellbook.monk.BloodyWhip;
import com.khryniewicki.projectX.game.attack.spell.spellbook.monk.FrostFury;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class FallenMonk<T extends FrostFury, R extends BloodyWhip> extends SuperHero<T, R> {

    public FallenMonk(T basicSpell, R ultimateSpell) {
        super(basicSpell, ultimateSpell);
        setPosition();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setTexture() {
        setTexture(HeroTextures.FALLENIDLE2);
        setHeroIdle(HeroTextures.FALLENIDLE2);
        setHeroRight(HeroTextures.FALLENRUN2);
        setHeroLeft(HeroTextures.FALLENRUN2);
        setHeroUp(HeroTextures.FALLENRUN2);
        setHeroDown(HeroTextures.FALLENRUN2);
        setHeroAttack(HeroTextures.FALLENATTACK2);
    }


    @Override
    public void setProperties() {
        setName("Fallen Monk");
        setHero_left_offset(0.2f);
        setHero_top_offset(0.5f);
        setHero_right_offset(0.2f);
        setMana(120f);
        setLife(120);
        setSIZE(1.1F);
        setManaRegeneration(0.75f);

    }


}
