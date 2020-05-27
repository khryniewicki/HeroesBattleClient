package com.khryniewicki.projectX.game.Map;

import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;

public interface Objects {

    float getObject_positionX0();
    float getObject_positionY0();
    float getObject_positionX1();
    float getObject_positionY1();
    float getWidth();
    float getHeight();
    float getTangens();
    Matrix4f getModelMatrix();
    VertexArray getMesh();
}
