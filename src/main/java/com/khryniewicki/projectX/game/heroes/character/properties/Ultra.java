package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.math.Vector;

public interface Ultra extends Symbol {
    void setPositionX(Float positionX);
    void setPositionY(Float positionY);

    Vector getPosition();
    default void setTexture(){};
    default void setPosition(){};
    default void setMesh(){};
    default void setProperties(){};
}
