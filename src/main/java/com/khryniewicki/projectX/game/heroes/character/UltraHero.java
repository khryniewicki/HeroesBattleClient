package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.math.Vector;

public interface UltraHero {
    void update();
    void render();
    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setMovingLeft(boolean movingLeft);
    Vector getPosition();
    Spell getSpell();
   default void setSpell(){};

    default void setTextureRun(){};
    default void setTexture(){};
    default void setPosition(){};
    default void setProperties(){};
    default void setMesh(){};
    default void setHeroIdl(){};
    default void  setHeroRun(){};


}
