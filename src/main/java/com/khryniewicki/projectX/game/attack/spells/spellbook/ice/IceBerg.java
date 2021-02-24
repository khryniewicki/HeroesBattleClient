package com.khryniewicki.projectX.game.attack.spells.spellbook.ice;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class IceBerg extends Spell {

    public IceBerg() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Ice Berg");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(6000L);
        setPowerAttack(21);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.ICEBALL, 1.2f));
        setExecutedSpell(new SpellTexture(SpellTextures.ICEBERG, 1.2f));
        setIcon(SpellTextures.ICEBERGICON);
        setFadedIcon(SpellTextures.ICEBERGFADED);
    }

}
