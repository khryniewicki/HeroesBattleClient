package com.khryniewicki.projectX.level;


import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;


public class Obstacle {

    private Vector position = new Vector();
    private Matrix4f ml_matrix;

    private static Texture texture;
    private static VertexArray mesh;

    private static float width = 0.11f;
    private static float height = 1.0f;
    public static float obstacle_positionX0 = -5.2f;
    public static float obstacle_positionY0 = -3.2f;

    public Obstacle() {
        float[] vertices = new float[]{
                obstacle_positionX0 + 0.0f,  obstacle_positionY0 + 0.0f,  -0.1f,
                obstacle_positionX0 + 0.0f,  obstacle_positionY0 + height,-0.1f,
                obstacle_positionX0 + width, obstacle_positionY0 + height,-0.1f,
                obstacle_positionX0 + width, obstacle_positionY0 + 0.0f,  -0.1f
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
        texture = new Texture("res/pipe.png");
    }

    public Obstacle(float x, float y) {
        position.x = x;
        position.y = y;
        ml_matrix = Matrix4f.translate(position);
    }

    public void update() {
    }


    public void render() {
        Shader.OBSTACLE.enable();

        Shader.OBSTACLE.setUniformMat4f("ml_matrix", com.khryniewicki.projectX.math.Matrix4f.translate(position));

        texture.bind();

        mesh.render();

        Shader.OBSTACLE.disable();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Matrix4f getModelMatrix() {
        return ml_matrix;
    }

    public static VertexArray getMesh() {
        return mesh;
    }

    public static Texture getTexture() {
        return texture;
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }
}
