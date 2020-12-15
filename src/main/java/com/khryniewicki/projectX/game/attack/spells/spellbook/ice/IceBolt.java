package com.khryniewicki.projectX.game.attack.spells.spellbook.ice;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class IceBolt extends Spell {

    public  IceBolt() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("IceBolt");
        setBasic(true);
        setCastingSpeed(0.25f);
        setSpellDuration(3000L);
        setPowerAttack(10);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.ICEBALL, 0.7f));
        setExecutedSpell(new SpellTexture(SpellTextures.ICE, 0.8f));
        setIcon(SpellTextures.ICEICON);
        setFadedIcon(SpellTextures.ICEICONFADED);
    }

}
