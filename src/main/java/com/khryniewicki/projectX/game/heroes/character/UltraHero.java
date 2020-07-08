package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;

public interface UltraHero extends Ultra {
    Float getX();
    Float getY();
    SpellInstance getSpellInstance();
    UltimateSpell getUltimateSpell();
    void setMovingLeft(boolean movingLeft);

    LifeBar getLifeBar();
    void setLifeBar(LifeBar lifeBar);

    Integer getLife();
    default void setLife(Integer mana){};

    ManaBar getManaBar();
    void setManaBar(ManaBar manaBar);

    Integer getMana();
    default void setMana(Integer mana){};

    UltraSpell getUltraSpell();
    void setUltraSpell(UltraSpell ultraSpell);

    StartingPosition getStartingPosition();
    void setStartingPosition(StartingPosition startingPosition);

    BasicSpell getBasicSpell();
    default void setSpellBasis(){};


    default void setHeroIdle(){};
    default void setHeroRun(){};
    default void updateManaBar(){};

}
