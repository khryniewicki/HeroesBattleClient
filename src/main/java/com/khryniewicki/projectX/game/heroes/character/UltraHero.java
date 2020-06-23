package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.math.Vector;

public interface UltraHero extends Ultra {

    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setMovingLeft(boolean movingLeft);
    void setLifeBar(LifeBar lifeBar);
    void setManaBar(ManaBar manaBar);

    Spell getSpell();
    LifeBar getLifeBar();
    ManaBar getManaBar();
    Integer getLife();
    Integer getMana();


    default void setSpell(){};
    default void setHeroIdle(){};
    default void  setHeroRun(){};
    default void updateManaBar(){};

}
