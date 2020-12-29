package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.witcher.ElectricShock;
import com.khryniewicki.projectX.game.attack.spells.spellbook.witcher.ElectricBomb;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.graphics.textures.HeroTextures;


public class FallenWitcher extends SuperHero {

    public FallenWitcher() {
        setPosition();
       setSpellBasis();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new ElectricShock()));
        setUltimateSpellInstance(new UltimateSpellInstance(new ElectricBomb()));
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
        setName("FallenWitcher");
        setHero_left_offset(0.4f);
        setHero_right_offset(0.2f);
        setHero_bottom_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(80f);
        setLife(160);
        setSIZE(1.1F);
        setManaRegeneration(0.5f);
    }


}
