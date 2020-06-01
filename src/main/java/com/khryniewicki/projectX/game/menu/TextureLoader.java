package com.khryniewicki.projectX.game.menu;

import com.khryniewicki.projectX.HelloWorld;
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
    private VertexArray text;
    private Matrix4f ml_matrix;
    private final float SIZE = 8.0f;

    private final float textX = -6f;
    private float textY;

    public TextureLoader(String path) {
        texture = new Texture(path);
        float ratio = getRatio();

        float[] vertices = new float[]{
                textX + -SIZE / 2.0f, calculateTextY() + -SIZE / (ratio * 2.0f), 1.0f,
                textX + -SIZE / 2.0f, calculateTextY() + SIZE / (ratio * 2.0f), 1.0f,
                textX + SIZE / 2.0f, calculateTextY() + SIZE / (ratio * 2.0f), 1.0f,
                textX + SIZE / 2.0f, calculateTextY() + -SIZE / (ratio * 2.0f), 1.0f
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
        text = new VertexArray(vertices, indices, tcs);
        Vector position = new Vector();
        ml_matrix = Matrix4f.translate(position);
    }

    public float calculateTextY() {
        textY = 5.0f - HelloWorld.mapWithTextures.size() * 0.5f;
        return textY;
    }

    public void render() {
        Map<String, TextureLoader> mapWithTextures = HelloWorld.mapWithTextures;

        mapWithTextures.values().forEach(t -> {
            t.texture.bind();
            Shader.TEXT.enable();
            t.text.bind();
            Shader.TEXT.setUniformMat4f("ml_matrix", getModelMatrix());
            t.text.draw();
            t.text.render();
            Shader.TEXT.disable();
            t.texture.unbind();
        });
    }




    public Matrix4f getModelMatrix() {
        return ml_matrix;
    }

    public float getRatio() {
        float height = texture.getHeight();
        float weight = texture.getWidth();
        float ratio = weight / height;
        return ratio;
    }
}
