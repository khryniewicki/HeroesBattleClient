package com.khryniewicki.projectX.game.attack.spell.spellbook.king;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class BlackFire extends Spell {

    public BlackFire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Black Fire");
        setBasic(true);
        setCastingSpeed(0.25f);
        setSpellDuration(4000L);
        setPowerAttack(12);
        setManaConsumed(10);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.BLACKORB, 0.5f));
        setExecutedSpell(new SpellTexture(SpellTextures.BLACKFIRE, 1.2f));
        setIcon(SpellTextures.BLACKFIREICON);
        setFadedIcon(SpellTextures.BLACKFIREICONFADED);
    }

}

