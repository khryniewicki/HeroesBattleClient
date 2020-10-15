package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.HeroUtil;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Pointer {

    public float SIZE = 0.1f;


    private VertexArray mesh;
    private Texture texture;

    private Vector position = new Vector();

    private float delta = 0.0f;
    public static boolean isMovingLeft = false;

    public static float hero_positionX0 = -0f;
    public static float hero_positionY0 = -0f;
    private float[] tcs;

    public Pointer() {
        setMesh(isMovingLeft(false));
        texture = HeroUtil.DOT;
    }

    public VertexArray isMovingLeft(boolean isMovingLeft) {
        int i = 1;
        if (isMovingLeft) {
            i = -1;
        }

        float[] vertices = new float[]{
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 1.0f,
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 1.0f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 1.0f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 1.0f
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        tcs = new float[]{
                0, 1,
                0, 0,
                i * 1, 0,
                i * 1, 1
        };
        return new VertexArray(vertices, indices, tcs);

    }

    public void update() {
        glfwSetMouseButtonCallback(Game.window, (window, key, action, mods) -> {
            if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE) {
                System.out.println(position);
            }
        });
        glfwSetKeyCallback(Game.window, (window, key, scancode, action, mods) -> {

                    if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_UP && action != GLFW_RELEASE ) {
                        System.out.println();
                        position.y += 0.05f;
                        setMesh(isMovingLeft(false));

                    } else if (key == GLFW.GLFW_KEY_DOWN && action != GLFW_RELEASE ) {
                        System.out.println();
                        position.y -= 0.05f;
                        setMesh(isMovingLeft(false));


                    } else if (key == GLFW.GLFW_KEY_LEFT && action != GLFW_RELEASE ) {
                        System.out.println();
                        position.x -= 0.05f;
                        setMesh(isMovingLeft(true));


                    } else if (key == GLFW.GLFW_KEY_RIGHT && action != GLFW_RELEASE ) {
                        System.out.println();
                        position.x += 0.05f;
                        setMesh(isMovingLeft(false));

                    } else {
                        isMovingLeft = false;

                    }
                }
        );

    }


    public void render() {

        Shader.HERO.enable();
        Shader.HERO.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.HERO.disable();

    }

    public VertexArray isMovingLeft() {
        return mesh;
    }


    public void setMesh(VertexArray mesh) {
        this.mesh = mesh;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

}
