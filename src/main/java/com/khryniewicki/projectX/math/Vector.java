package com.khryniewicki.projectX.math;

public class Vector {

    public float x, y, z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    @Override
    public String toString() {
        return (String.format("%s[x : %f] [y : %f] [z : %f]", this.getClass().getName(), x, y, z));
    }

}
