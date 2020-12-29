package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.graphics.GraphicLoader;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Getter
@Setter
@Slf4j
public class LifeBar extends GraphicLoader {

    private float width, height;
    private Vector position = new Vector();
    private VertexArray greenMesh, blackMesh;
    private Texture blackBarTexture, greenBarTexture;
    private UltraHero ultraHero;
    float offsetPositionY = 0.5f;
    float offsetPositionX = -0.3f;
    private Integer maxLife;

    public LifeBar(Builder builder) {
        super(builder);
        this.ultraHero = builder.ultraHero;
        this.blackBarTexture = builder.blackBarTexture;
        this.greenBarTexture = builder.greenBarTexture;
        this.maxLife = builder.maxLife;
        update();
    }

    @Override
    public void update() {
        updateLifeBar();
    }

    public void updateLifeBar() {
        setPositionX(ultraHero.getX() + offsetPositionX);
        setPositionY(ultraHero.getY() + offsetPositionY);
        this.greenMesh = getLifeBarMesh("green");
        this.blackMesh = getLifeBarMesh("black");

    }

    public VertexArray getLifeBarMesh(String color) {
        float factor = getLifeFactor(color);
        setWidth(factor);
        return updateMesh();
    }


    private float getLifeFactor(String textureType) {
        return textureType.equals("green") ? getLifeFactor() : 1f;
    }


    public Float getLifeFactor() {
        if (Objects.isNull(ultraHero.getLife())) {
            return 1f;
        } else {
            float life = ultraHero.getLife();
            return life < 0 ? 0 : life / maxLife;
        }
    }

    public void setWidth(float factor) {
        width = 0.65f;
        super.setWidth(width * factor);
    }


    @Override
    public void render() {
        Shader.STRIP.enable();
        Shader.STRIP.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        greenBarTexture.bind();
        greenMesh.render();
        blackBarTexture.bind();
        blackMesh.render();
        Shader.STRIP.disable();
    }


    public static class Builder extends GraphicLoader.Builder<Builder> {
        private UltraHero ultraHero;
        private Texture blackBarTexture;
        private Texture greenBarTexture;
        private Integer maxLife;

        public LifeBar.Builder withHero(UltraHero ultraHero) {
            this.ultraHero = ultraHero;
            return this;
        }

        public LifeBar.Builder withBlackBarTexture(Texture texture) {
            this.blackBarTexture = texture;
            return this;
        }

        public LifeBar.Builder withGreenBarTexture(Texture texture) {
            this.greenBarTexture = texture;
            return this;
        }

        public LifeBar.Builder withMaxLife(Integer maxLife) {
            this.maxLife = maxLife;
            return this;
        }

        public Builder() {
        }

        public LifeBar build() {
            return new LifeBar(this);
        }
    }
}



