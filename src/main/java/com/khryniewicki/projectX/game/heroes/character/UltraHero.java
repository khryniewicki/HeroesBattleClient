package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.math.Vector;

public interface UltraHero extends Ultra {

    void setMovingLeft(boolean movingLeft);
    void setLifeBar(LifeBar lifeBar);
    void setManaBar(ManaBar manaBar);

    SpellInstance getSpellInstance();
    LifeBar getLifeBar();
    ManaBar getManaBar();
    Integer getLife();
    Integer getMana();
    BasicSpell getBasicSpell();
    UltimateSpell getUltimateSpell();
    void setUltraSpell(UltraSpell ultraSpell);
    UltraSpell getUltraSpell();
    default void setMana(Integer mana){};
    default void setSpellBasis(){};
    default void setHeroIdle(){};
    default void  setHeroRun(){};
    default void updateManaBar(){};

}
