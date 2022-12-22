package com.khryniewicki.projectX.game.attack.spell.spellbook.witcher;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class ElectricBomb extends Spell {

    public ElectricBomb() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Electric Bomb");
        setBasic(false);
        setCastingSpeed(0.20f);
        setSpellDuration(8000L);
        setPowerAttack(16);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.WINDORB2, 0.7f));
        setExecutedSpell(new SpellTexture(SpellTextures.WINDATTACK, 1.6f));
        setIcon(SpellTextures.WINDICON);
        setFadedIcon(SpellTextures.WINDICONFADED);
    }

}

