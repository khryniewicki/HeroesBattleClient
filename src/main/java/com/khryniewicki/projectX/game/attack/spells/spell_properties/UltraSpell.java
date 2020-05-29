package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;

public interface UltraSpell {
    void update();
    void render();

    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setPositionZ(Float positionZ);
    void getMousePosition();

    void setRelativeX(Float relativeX);
    void setRelativeY(Float relativeY);
    void setDistanceX(Float distanceX);
    void setDistanceY(Float distanceY);
    void setSpell(Float indexHeight, Float indexWidth, Texture texture);
    void spellCasting();

    Float getHeroPositionX();
    Float getHeroPositionY();
    Float getDistanceX();
    Float getDistanceY();
    Float getPositionX();
    Float getPositionY();
    Float getPositionZ();
    Float getSize();
    Texture getThrowingSpellTexture();

    default void setPosition(){ };
    default void setMesh(){};
    default void setProperties(){};
    default void setTexture(){};



}
