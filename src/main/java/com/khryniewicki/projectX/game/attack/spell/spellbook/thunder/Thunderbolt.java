package com.khryniewicki.projectX.game.attack.spell.spellbook.thunder;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class Thunderbolt extends Spell {

    public Thunderbolt() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Thunderbolt");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setSIZE(1.0f);
        setPowerAttack(15);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.THUNDERORB, 0.8f));
        setExecutedSpell(new SpellTexture(SpellTextures.VIOLETMISSLE, 2.5f));
        setIcon(SpellTextures.VIOLETICON);
        setFadedIcon(SpellTextures.VIOLETICONFADED);
    }

}
