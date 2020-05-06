package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.math.Vector;

public interface UltraHero {
    void update();
    void render();
    void setTexture();
    void setPosition();
    void setPositionX(Float positionX);
    void setPositionY(Float positionY);
    void setProperties();
    Vector getPosition();
    void setMesh();
    void setMovingLeft(boolean movingLeft);
    void setTextureRun();

}
