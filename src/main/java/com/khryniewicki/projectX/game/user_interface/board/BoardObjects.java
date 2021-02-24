package com.khryniewicki.projectX.game.user_interface.board;

import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;

public interface BoardObjects {
    float getX();
    float getY();
    float getObstacle_positionX0();
    float getObstacle_positionY0();
    float getObstacle_positionX1();
    float getObstacle_positionY1();
    float getWidth();
    float getHeight();
    float getTangens();
    Matrix4f getModelMatrix();
    VertexArray getMesh();
    String getName();
}
