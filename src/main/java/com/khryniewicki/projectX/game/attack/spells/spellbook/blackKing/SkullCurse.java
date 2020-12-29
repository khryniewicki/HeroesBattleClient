package com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class SkullCurse extends Spell {

    public SkullCurse() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("Skull Curse");
        setBasic(false);
        setCastingSpeed(0.25f);
        setSpellDuration(8000L);
        setPowerAttack(18);
        setManaConsumed(15);
    }

    @Override
    public void setTexture() {
        setMissleSpell(new SpellTexture(SpellTextures.SKULL4, 0.7f));
        setExecutedSpell(new SpellTexture(SpellTextures.SKULL3, 1.2f));
        setIcon(SpellTextures.SKULLICON);
        setFadedIcon(SpellTextures.SKULLICONFADED);
    }

}

