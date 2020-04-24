package com.khryniewicki.projectX.level;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import flappy.input.Input;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;


public class Bird {

    private float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;

    private Vector position = new Vector();
    private float rot;
    private float delta = 0.0f;

    public Bird() {
        float[] vertices = new float[]{
                -SIZE / 2.0f, -SIZE / 2.0f, 1.0f,
                -SIZE / 2.0f, SIZE / 2.0f, 1.0f,
                SIZE / 2.0f, SIZE / 2.0f, 1.0f,
                SIZE / 2.0f, -SIZE / 2.0f, 1.0f
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("res/bird.png");
    }

    public void update() {
        glfwSetKeyCallback(HelloWorld.window, (window, key, scancode, action, mods) -> {
                    if (key== GLFW.GLFW_KEY_UP && action != GLFW_RELEASE)
                        position.y += 0.2f;
                    if (key== GLFW.GLFW_KEY_DOWN && action != GLFW_RELEASE)
                        position.y -= 0.2f;
                    if (key== GLFW.GLFW_KEY_LEFT && action != GLFW_RELEASE)
                        position.x -= 0.2f;
                    if (key== GLFW.GLFW_KEY_RIGHT && action != GLFW_RELEASE)
                        position.x += 0.2f;
                }
        );
    }

    public void render() {

        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));

        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }


}

