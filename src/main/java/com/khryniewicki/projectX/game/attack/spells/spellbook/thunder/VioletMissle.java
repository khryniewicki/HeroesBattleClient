package com.khryniewicki.projectX.game.attack.spells.spellbook.thunder;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class VioletMissle extends Spell {

    public VioletMissle() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("VioletMissle");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setSIZE(1.0f);
        setPowerAttack(10);
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
