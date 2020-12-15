package com.khryniewicki.projectX.game.attack.spells.spellbook.monk;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class BlueOrb extends Spell {

    public BlueOrb() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("BlueOrbBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
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

