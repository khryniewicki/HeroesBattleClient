package com.khryniewicki.projectX.game.attack;

public interface UltraSpell {
    default void setProperties(){};
    default void setThrowingSpellTexture(){};
    default void setConsumedSpellTexture(){};
    default void setTexture(){};
    default void setMesh(){};

    void update();
    void render();
    void castingSpell();
    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setPositionZ(Float positionZ);

}
