package com.khryniewicki.projectX.level;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import flappy.input.Input;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;


public class Wizard {

    private float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;

    private Vector position = new Vector();
    private float rot;
    private float delta = 0.0f;

    private Texture walk=new Texture("res/knights/PNG/bronze_knight/2_WALK_000.png");
    private Texture walk1=new Texture("res/knights/PNG/bronze_knight/2_WALK_001.png");
    private Texture walk2=new Texture("res/knights/PNG/bronze_knight/2_WALK_002.png");
    private Texture walk3=new Texture("res/knights/PNG/bronze_knight/2_WALK_003.png");

    private Texture run=new Texture("res/knights/PNG/bronze_knight/3_RUN_000.png");
    private Texture run1=new Texture("res/knights/PNG/bronze_knight/3_RUN_001.png");


    private Texture idle=new Texture("res/knights/PNG/bronze_knight/1_IDLE_000.png");

    public Wizard() {
        float[] vertices = new float[]{
                -6.8f+-SIZE / 2.0f, -4+-SIZE / 2.0f, 1.0f,
                -6.8f+-SIZE / 2.0f, -4+SIZE / 2.0f, 1.0f,
                -6.8f+ SIZE / 2.0f, -4+SIZE / 2.0f, 1.0f,
                -6.8f+ SIZE / 2.0f, -4+-SIZE / 2.0f, 1.0f
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
        texture = idle;
    }

    public void update() {
        glfwSetKeyCallback(HelloWorld.window, (window, key, scancode, action, mods) -> {
                    if (key== GLFW.GLFW_KEY_UP && action != GLFW_RELEASE){
                        position.y += 0.2f;
                        texture =walk ;
                        System.out.println(texture.toString());
                    }
                    else if (key== GLFW.GLFW_KEY_DOWN && action != GLFW_RELEASE){
                        position.y -= 0.2f;
                        texture = walk;
                        System.out.println(texture.toString());

                    }
                    else if (key== GLFW.GLFW_KEY_LEFT && action != GLFW_RELEASE){
                        position.x -= 0.2f;
                        texture = walk;
                        System.out.println(texture.toString());

                    }
                    else if (key== GLFW.GLFW_KEY_RIGHT && action != GLFW_RELEASE){
                        position.x += 0.2f;
                        texture = walk;
                        System.out.println(texture.toString());

                    }
                    else{
                        texture = idle;

                    }
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

