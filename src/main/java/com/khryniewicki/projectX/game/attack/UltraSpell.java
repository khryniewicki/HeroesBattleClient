package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.math.Vector;

public interface UltraSpell {
    default void setProperties(){};
    default void setThrowingSpellTexture(){};
    default void setConsumedSpellTexture(){};
    default void setTexture(){};
    default void setMesh(){};
    default void setPosition(){ };
    Vector getPosition();

    void update();
    void render();
    void castingSpell();
    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setPositionZ(Float positionZ);
}
