package com.khryniewicki.projectX;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class Input extends GLFWKeyCallback {

    public static int[] keys=new int [65536];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key]=  GLFW.GLFW_PRESS;
    }
}

//    private void moveWithKey(int pressedKey) {
//        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
//            if (key == pressedKey && action != GLFW_RELEASE)
//                System.out.println("Jump");
//        });
//    }