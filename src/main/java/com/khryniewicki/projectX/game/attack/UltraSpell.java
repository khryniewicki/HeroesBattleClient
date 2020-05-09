package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;

public interface UltraSpell {
    default void setProperties(){};
    default void setThrowingSpellTexture(){};
    default void setConsumedSpellTexture(){};
    default void setTexture(){};
    default void setMesh(){};
    default void setPosition(){ };
    Vector getPosition();
    void setSpellParametrs();
    void update();
    void render();
    void castingSpell();
    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setPositionZ(Float positionZ);
    Float getHeroPositionX();
    Float getHeroPositionY();
    void setRelativeX(Float relativeX);
    void setRelativeY(Float relativeY);
    void getMousePosition();
    void spellCasting();
    void setDistanceX(Float distanceX);
    void setDistanceY(Float distanceY);
    Float getDistanceX();
    Float getDistanceY();
    Texture getThrowingSpellTexture();
    void setSpell(Float indexHeight, Float indexWidth, Texture texture);
}
