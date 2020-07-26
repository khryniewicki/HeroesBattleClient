package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.*;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


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
        setBasicSpellInstance(new BasicSpellInstance(new Wind()));
        setUltimateSpellInstance(new UltimateSpellInstance(new RedOrb()));
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.FALLENIDLE1);
        setHeroIdle(HeroUtil.FALLENIDLE1);
        setHeroRight(HeroUtil.FALLENRUN1);
        setHeroLeft(HeroUtil.FALLENRUN1);
        setHeroUp(HeroUtil.FALLENRUN1);
        setHeroDown(HeroUtil.FALLENRUN1);
        setHeroAttack(HeroUtil.FALLENATTACK1);
    }


    @Override
    public void setProperties() {
        setName("Fallen1");
        setHero_left_offset(0.4f);
        setHero_right_offset(0.2f);
        setHero_bottom_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);
        setSIZE(1.1F);
    }


}
