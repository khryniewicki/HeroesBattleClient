package com.khryniewicki.projectX.game.heroes.character.fallens;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Ice;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.HeroUtil;


public class Fallen3 extends SuperHero {

    public Fallen3() {
        setPosition();
        setSpellBasis();
        setMesh();
        setTexture();
        setProperties();
    }

    @Override
    public void setSpellBasis() {
        setBasicSpellInstance(new BasicSpellInstance(new Fire()));
        setUltimateSpellInstance(new UltimateSpellInstance(new Ice()));
    }

    @Override
    public void setTexture() {
        setTexture(HeroUtil.FALLENIDLE3);
        setHeroIdle(HeroUtil.FALLENIDLE3);
        setHeroRight(HeroUtil.FALLENRUN3);
        setHeroLeft(HeroUtil.FALLENRUN3);
        setHeroUp(HeroUtil.FALLENRUN3);
        setHeroDown(HeroUtil.FALLENRUN3);
    }


    @Override
    public void setProperties() {
        setName("Fallen3");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);
        setSIZE(1.2F);
    }


}
