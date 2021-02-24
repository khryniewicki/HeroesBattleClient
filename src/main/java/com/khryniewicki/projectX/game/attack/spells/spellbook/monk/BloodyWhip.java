package com.khryniewicki.projectX.game.attack.spells.spellbook.monk;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class BloodyWhip extends Spell {

    public BloodyWhip() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Bloody Whip");
        setBasic(false);
        setCastingSpeed(0.2f);
        setSpellDuration(8000L);
        setPowerAttack(20);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.BLOODORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellTextures.REDORB, 1.0f));
        setIcon(SpellTextures.REDORBICON);
        setFadedIcon(SpellTextures.REDORBICONFADED);
    }

}

