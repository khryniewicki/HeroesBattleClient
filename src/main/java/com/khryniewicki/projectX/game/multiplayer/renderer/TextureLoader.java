package com.khryniewicki.projectX.game.multiplayer.renderer;

import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;

import java.util.Map;

@Data
public class TextureLoader {
    private String path;
    private Texture texture;
    private VertexArray mesh;
    private Matrix4f ml_matrix;
    private final float SIZE = 8.0f;

    private final float positionX = -6f;
    private float positionY;

    public TextureLoader(String path) {
        texture = new Texture(path);
        float ratio = getRatio();

        float[] vertices = new float[]{
                positionX + -SIZE / 2.0f, calculateTextY() + -SIZE / (ratio * 2.0f), 1.0f,
                positionX + -SIZE / 2.0f, calculateTextY() + SIZE / (ratio * 2.0f), 1.0f,
                positionX + SIZE / 2.0f, calculateTextY() + SIZE / (ratio * 2.0f), 1.0f,
                positionX + SIZE / 2.0f, calculateTextY() + -SIZE / (ratio * 2.0f), 1.0f
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
        Vector position = new Vector();
        ml_matrix = Matrix4f.translate(position);
    }

    public float calculateTextY() {
        positionY = 5.0f - RenderFactory.mapWithTextures.size() * 0.5f;
        return positionY;
    }

    public void render() {
        Map<String, TextureLoader> mapWithTextures = RenderFactory.mapWithTextures;

        mapWithTextures.values().forEach(t -> {
            t.texture.bind();
            Shader.TEXT.enable();
            t.mesh.bind();
            Shader.TEXT.setUniformMat4f("ml_matrix", ml_matrix);
            t.mesh.draw();
            t.mesh.render();
            Shader.TEXT.disable();
            t.texture.unbind();
        });
    }

    public float getRatio() {
        float height = texture.getHeight();
        float weight = texture.getWidth();
        return weight / height;
    }
}
