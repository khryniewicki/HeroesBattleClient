package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
public class GameSymbol implements Symbol {
    private VertexArray mesh;
    private final Vector position;
    private final Float x, y;
    private Texture texture;
    private Float width, height, visibility;

    private GameSymbol(Builder builder) {
        this.position = builder.position;
        this.x = builder.x;
        this.y = builder.y;
        this.texture = builder.texture;
        this.width = builder.width;
        this.height = builder.height;
        this.visibility = builder.visibility;
        this.mesh = createVertexArray();
    }

    public VertexArray createVertexArray() {
        float[] vertices = new float[]{
                x, y, visibility,
                x, y + height, visibility,
                x + width, y + height, visibility,
                x + width, y, visibility
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
        return new VertexArray(vertices, indices, tcs);
    }

    public void render() {
        Shader.SYMBOL.enable();
        Shader.SYMBOL.setUniformMat4f("ml_matrix", Matrix4f.translate(position));

        texture.bind();
        mesh.render();
        Shader.SYMBOL.disable();
    }

    public void update_mesh() {
        this.mesh = createVertexArray();
    }

    public static class Builder {
        private final Texture texture;
        private final Vector position;
        private final Float x, y;
        private Float width, height, visibility;

        public Builder(Texture texture, Float x, Float y) {
            this.texture = texture;
            this.position = new Vector();
            this.x = x;
            this.y = y;
            this.width = 0.3f;
            this.height = 0.3f;
            this.visibility = 1.0f;
        }

        public Builder withWidth(Float width) {
            this.width = width;
            return this;
        }

        public Builder withHeight(Float height) {
            this.height = height;
            return this;
        }

        public Builder withVisibility(Float visibility) {
            this.visibility = visibility;
            return this;
        }

        public GameSymbol build() {
            return new GameSymbol(this);
        }
    }
}
