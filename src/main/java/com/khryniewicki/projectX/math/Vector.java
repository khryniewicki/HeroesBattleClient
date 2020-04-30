package com.khryniewicki.projectX.math;

import lombok.Data;

@Data
public class Vector {

    public Float x, y, z;

    public Vector(Float x, Float y, Float z) {
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
