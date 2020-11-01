package com.khryniewicki.projectX.graphics;

import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;

import java.util.Objects;


@Data
public class GraphicLoader implements Symbol {
    private String path;
    private Texture texture;
    private VertexArray mesh;
    private Matrix4f ml_matrix;
    private float SIZE;
    float[] vertices;
    float[] tcs;
    private float positionX;
    private float positionY;
    private float visibility;
    private Vector position;
    private float width;
    private float height;
    boolean isSizeKnown;

    public GraphicLoader(Builder<?> builder) {
        this.path = builder.path;
        this.texture = builder.texture;
        this.vertices = builder.vertices;
        this.tcs = builder.tcs;
        this.positionX = builder.positionX;
        this.positionY = builder.positionY;
        this.visibility = builder.visibility;
        this.SIZE = builder.SIZE;
        this.width = builder.width;
        this.height = builder.height;
        this.isSizeKnown = builder.isSizeKnown;
        this.position = new Vector();
        init(path);
    }

    public void init(String path) {
        if (Objects.nonNull(path)) {
            this.texture = new Texture(path);
        }
        float ratio = getRatio();

        if (isSizeKnown) {
            vertices = new float[]{
                    positionX + -SIZE / 2.0f, positionY + -SIZE / (2.0f), visibility,
                    positionX + -SIZE / 2.0f, positionY + SIZE / (2.0f), visibility,
                    positionX + SIZE / 2.0f, positionY + SIZE / (2.0f), visibility,
                    positionX + SIZE / 2.0f, positionY + -SIZE / (2.0f), visibility
            };
        } else {
            vertices = new float[]{
                    positionX, positionY, visibility,
                    positionX, positionY + height, visibility,
                    positionX + width, positionY + height, visibility,
                    positionX + width, positionY, visibility
            };
        }
        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        tcs = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };
        mesh = new VertexArray(vertices, indices, tcs);
        Vector position = new Vector();
        ml_matrix = Matrix4f.translate(position);
    }

    public void render() {
        texture.bind();
        Shader.TEXT.enable();
        mesh.bind();
        Shader.TEXT.setUniformMat4f("ml_matrix", ml_matrix);
        mesh.draw();
        mesh.render();
        Shader.TEXT.disable();
        texture.unbind();
    }

    public float getRatio() {
        float height = texture.getHeight();
        float weight = texture.getWidth();
        return weight / height;
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T extends Builder<T>> {
        private String path;
        private Texture texture;
        private float SIZE = 1f;
        private float[] vertices;
        private float[] tcs;
        private float positionX;
        private float positionY;
        private float width;
        private float height;
        boolean isSizeKnown;
        private float visibility = 1f;

        public Builder() {
        }

        public T withSize(boolean isSizeKnown) {
            this.isSizeKnown = isSizeKnown;
            return (T) this;
        }

        public T withPath(String path) {
            this.path = path;
            return (T) this;
        }

        public T withTexture(Texture texture) {
            this.texture = texture;
            return (T) this;
        }

        public T withPositionX(Float positionX) {
            this.positionX = positionX;
            return (T) this;
        }

        public T withSize(Float SIZE) {
            this.SIZE = SIZE;
            return (T) this;
        }

        public T withPositionY(Float positionY) {
            this.positionY = positionY;
            return (T) this;
        }

        public T withVisibility(Float visibility) {
            this.visibility = visibility;
            return (T) this;
        }

        public T withWidth(Float width) {
            this.width = width;
            return (T) this;
        }

        public T withHeight(Float height) {
            this.height = height;
            return (T) this;
        }


        public GraphicLoader build() {
            return new GraphicLoader(this);
        }
    }
}
