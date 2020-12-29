package com.khryniewicki.projectX.game.attack.spells.spellbook.fire;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class FireBomb extends Spell {

    public FireBomb() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Fire Bomb");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(6000L);
        setPowerAttack(24);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.FIREORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellTextures.FIREBALL, 1.7f));
        setIcon(SpellTextures.FIREICON);
        setFadedIcon(SpellTextures.FIREICONFADED);

    }

}

