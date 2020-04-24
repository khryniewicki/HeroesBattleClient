package com.khryniewicki.projectX;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class Input extends GLFWKeyCallback {

    public static int[] keys=new int [65536];

    @Override
    public void invoke(long l, int i, int i1, int i2, int i3) {
        keys[i]= GLFW.GLFW_RELEASE;
    }
}
