package com.khryniewicki.projectX.game.attack.spell.spellbook.fire;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class Fire extends Spell {

    public Fire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Fire");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(16);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.FIREORB, 0.3f));
        setExecutedSpell(new SpellTexture(SpellTextures.FIRE, 1.2f));
        setIcon(SpellTextures.ULTIMATEFIREICON);
        setFadedIcon(SpellTextures.ULTIMATEFIREICONFADED);

    }

}

