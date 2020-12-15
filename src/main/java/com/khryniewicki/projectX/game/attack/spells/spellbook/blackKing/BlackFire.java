package com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.graphics.textures.SpellTextures;


public class BlackFire extends Spell {

    public BlackFire() {
        setProperties();
        setTexture();
    }

    @Override
    public void setProperties() {
        setName("BlackFireBall");
        setBasic(true);
        setCastingSpeed(0.3f);
        setSpellDuration(3000L);
        setPowerAttack(10);
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

