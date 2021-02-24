package com.khryniewicki.projectX.game.attack.spells.spellbook.thunder;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;

public class Lightning extends Spell {

    public Lightning() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Lightning");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(6000L);
        setSIZE(1.0f);
        setPowerAttack(22);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.THUNDERBALL2, 1.5f));
        setExecutedSpell(new SpellTexture(SpellTextures.THUNDER, 1.3f));
        setIcon(SpellTextures.THUNDERICON);
        setFadedIcon(SpellTextures.THUNDERICONFADED);
    }

}
