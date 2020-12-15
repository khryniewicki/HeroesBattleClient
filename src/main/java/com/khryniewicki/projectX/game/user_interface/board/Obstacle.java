package com.khryniewicki.projectX.game.user_interface.board;


import com.khryniewicki.projectX.game.control_settings.mouse_settings.MousePosition;
import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

@Data
public class Obstacle implements BoardObjects {

    private Vector position = new Vector();
    private Matrix4f ml_matrix;

    private static Texture texture;
    private VertexArray mesh;
    private String name;
    private float width;
    private float height;
    private float obstacle_positionX0;
    private float obstacle_positionY0;
    private float obstacle_positionX1;
    private float obstacle_positionY1;

    private float visible=1;

    public Obstacle(String name,float width, float height, float obstacle_positionX0, float obstacle_positionY0) {
        setWidth(width);
        setHeight(height);
        setObstacle_positionX0(obstacle_positionX0);
        setObstacle_positionY0(obstacle_positionY0);
        setObstacle_positionX1(obstacle_positionX0 + width);
        setObstacle_positionY1(obstacle_positionY0 + height);
        setName(name);
        float[] vertices = new float[]{
                0 + 0.0f, 0 + 0.0f,    visible*0.1f,
                0 + 0.0f, 0 + height,  visible*0.1f,
                0 + width, 0 + height, visible*0.1f,
                0 + width, 0 + 0.0f,   visible*0.1f
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
        texture = ObstacleStorage.pipe;
        position.x = obstacle_positionX0;
        position.y = obstacle_positionY0;
        ml_matrix = Matrix4f.translate(position);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }



    @Override
    public float getTangens() {
        return 0;
    }

    public Matrix4f getModelMatrix() {
        return ml_matrix;
    }

    public VertexArray getMesh() {
        return mesh;
    }

    public static Texture getTexture() {
        return texture;
    }


}
