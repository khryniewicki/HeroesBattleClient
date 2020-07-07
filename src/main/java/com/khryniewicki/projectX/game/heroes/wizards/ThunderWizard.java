package com.khryniewicki.projectX.game.heroes.wizards;


import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Ice;
import com.khryniewicki.projectX.game.attack.spells.spellbook.Thunder;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.HeroUtil;
import lombok.Data;

@Data
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
        setTexture(HeroUtil.THUNDER_WIZARD_IDLE);
        setHeroIdle(HeroUtil.THUNDER_WIZARD_IDLE);
        setHeroRight(HeroUtil.THUNDER_WIZARD_RUN);
        setHeroLeft(HeroUtil.THUNDER_WIZARD_RUN);
        setHeroUp(HeroUtil.THUNDER_WIZARD_RUN);
        setHeroDown(HeroUtil.THUNDER_WIZARD_RUN);
    }

    @Override
    public void setPosition() {
        setPosition(new Vector());

    }

    @Override
    public void setSpellBasis() {
        setUltraSpell(new Spell());
        setBasicSpell(new BasicSpell(new Thunder()));
        setUltimateSpell(new UltimateSpell(new Ice()));
    }

    @Override
    public void setProperties() {
        setName("ThunderWizard");
        setHero_standard_offset(0.2f);
        setHero_top_offset(0.5f);
        setMana(100);
        setLife(100);
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }


}
