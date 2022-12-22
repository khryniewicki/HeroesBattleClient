package com.khryniewicki.projectX.game.attack.spell.spellbook.ice;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class IceBolt extends Spell {

    public  IceBolt() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Ice Bolt");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(14);
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
