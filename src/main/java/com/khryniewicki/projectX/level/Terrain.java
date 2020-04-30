package com.khryniewicki.projectX.level;

import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;

@Data
public class Terrain implements MapObstacles {

    private Vector position = new Vector();
    private Matrix4f ml_matrix;

    private static Texture texture;
    private VertexArray mesh;

    private float width;
    private float height;
    private float inclination;
    private float tangens;

    private float obstacle_positionX1;
    private float obstacle_positionY1;
    private float obstacle_positionX0;
    private float obstacle_positionY0;
    private float visible = 1;

    public Terrain(float obstacle_positionX0, float obstacle_positionY0, float obstacle_positionX1, float obstacle_positionY1) {
        width = obstacle_positionX1 - obstacle_positionX0;
        height = 0.2f;
        inclination = obstacle_positionY1 - obstacle_positionY0;
        tangens = (inclination) / (width);

        setObstacle_positionX1(obstacle_positionX1);
        setObstacle_positionY1(obstacle_positionY1);

        createNewTerrain(obstacle_positionX0, obstacle_positionY0 );
    }

    public Terrain(float obstacle_positionX0, float obstacle_positionY0, float height) {
        width = 0.2f;
        inclination=0;

        setTangens(0f);setHeight(height);setWidth(width);
        setObstacle_positionX1(obstacle_positionX0 + width);
        setObstacle_positionY1(obstacle_positionY0 + height);

        createNewTerrain(obstacle_positionX0, obstacle_positionY0);
    }
    public Terrain(float obstacle_positionX0, float obstacle_positionY0, double widthD) {
        height = 0.2f;
        inclination=0;
        setWidth((float) widthD);
        setTangens(0f);setHeight(height);setWidth(width);
        setObstacle_positionX1(obstacle_positionX0 + width);
        setObstacle_positionY1(obstacle_positionY0 + height);

        createNewTerrain(obstacle_positionX0, obstacle_positionY0);
    }

    private void createNewTerrain(float obstacle_positionX0, float obstacle_positionY0) {
        setObstacle_positionX0(obstacle_positionX0);
        setObstacle_positionY0(obstacle_positionY0);

        float[] vertices = new float[]{
                0 + 0.0f, 0.0f, visible * 0.1f,
                0 + 0.0f, height, visible * 0.1f,
                0 + width, height+inclination, visible * 0.1f,
                0 + width, inclination, visible * 0.1f
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
