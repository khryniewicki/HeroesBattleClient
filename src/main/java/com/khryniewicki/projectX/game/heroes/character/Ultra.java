package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.math.Vector;

public interface Ultra {
    void update();
    void render();
    Vector getPosition();

    default void setTexture(){};
    default void setPosition(){};
    default void setMesh(){};
    default void setProperties(){};
}
