package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.BlackFire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


public class FallenMonk extends SuperHero {

    public FallenMonk() {
        setPosition();
        setSpellBasis();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new BlackFire()));
        setUltimateSpellInstance(new UltimateSpellInstance(new Ice()));
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.FALLENIDLE2);
        setHeroIdle(HeroUtil.FALLENIDLE2);
        setHeroRight(HeroUtil.FALLENRUN2);
        setHeroLeft(HeroUtil.FALLENRUN2);
        setHeroUp(HeroUtil.FALLENRUN2);
        setHeroDown(HeroUtil.FALLENRUN2);
        setHeroAttack(HeroUtil.FALLENATTACK2);
    }


    @Override
    public void setProperties() {
        setName("Fallen2");
        setHero_left_offset(0.4f);
        setHero_top_offset(0.5f);
        setHero_right_offset(0.2f);
        setHero_bottom_offset(0.2f);
        setMana(100);
        setLife(100);
        setSIZE(1.1F);
    }


}
