package com.khryniewicki.projectX.graphics;

import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class GraphicLoader implements Symbol {
    protected String path;
    protected Texture texture;
    protected VertexArray mesh;
    protected float SIZE;
    protected float[] vertices;
    protected float[] tcs;
    protected float positionX;
    protected float positionY;
    protected float visibility;
    protected Vector position = new Vector();
    protected float width;
    protected float height;
    protected boolean isSizeKnown;
    protected boolean isTurningLeft;
    protected String name;

    public GraphicLoader(Builder<?> builder) {
        this.path = builder.path;
        this.texture = builder.texture;
        this.positionX = builder.positionX;
        this.positionY = builder.positionY;
        this.visibility = builder.visibility;
        this.SIZE = builder.SIZE;
        this.width = builder.width;
        this.height = builder.height;
        this.isSizeKnown = builder.isSizeKnown;
        this.name = builder.name;
        if (Objects.nonNull(path)) {
            this.texture = new Texture(path);
        }
        updateMesh();
    }

    public VertexArray updateMesh() {
        int t = isTurningLeft ? -1 : 1;

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
                t, 0,
                t, 1
        };
        mesh = new VertexArray(vertices, indices, tcs);
        return mesh;
    }

    public void render() {
        texture.bind();
        Shader.TEXT.enable();
        mesh.bind();
        Shader.TEXT.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        mesh.draw();
        mesh.render();
        Shader.TEXT.disable();
        texture.unbind();
    }

    @Override
    public void reload() {
        this.texture.reload();
        updateMesh();
    }


    @SuppressWarnings("unchecked")
    public static class Builder<T extends Builder<T>> {
        private String path;
        private Texture texture;
        private float SIZE = 1f;
        private float positionX;
        private float positionY;
        private float width;
        private float height;
        boolean isSizeKnown;
        private float visibility = 1f;
        private String name;

        public Builder(String name) {
            this.name = name;
        }

        public Builder() {
        }

        public T isSizeKnown(boolean isSizeKnown) {
            this.isSizeKnown = isSizeKnown;
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
