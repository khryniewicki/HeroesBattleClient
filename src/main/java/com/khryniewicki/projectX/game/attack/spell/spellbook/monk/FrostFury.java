package com.khryniewicki.projectX.game.attack.spell.spellbook.monk;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class FrostFury extends Spell {

    public FrostFury() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Frost Fury");
        setBasic(true);
        setCastingSpeed(0.25f);
        setSpellDuration(4000L);
        setPowerAttack(13);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.BLUEORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellTextures.BLUE, 2.5f));
        setIcon(SpellTextures.BLUEICON);
        setFadedIcon(SpellTextures.BLUEICONFADED);
    }

}

