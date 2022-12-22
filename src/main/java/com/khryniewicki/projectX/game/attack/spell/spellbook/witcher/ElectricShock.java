package com.khryniewicki.projectX.game.attack.spell.spellbook.witcher;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class ElectricShock extends Spell {

    public ElectricShock() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Electric Shock");
        setBasic(true);
        setCastingSpeed(0.25f);
        setSpellDuration(4000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.WINDORB2, 0.5f));
        setExecutedSpell(new SpellTexture(SpellTextures.STORM, 1.5f));
        setIcon(SpellTextures.STORMICON);
        setFadedIcon(SpellTextures.STORMICONFADED);
    }

}

