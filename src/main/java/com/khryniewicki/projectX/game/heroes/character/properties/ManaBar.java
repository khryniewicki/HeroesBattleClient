package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.graphics.GraphicLoader;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.dto.HeroDto;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Getter
@Setter
@Slf4j
public class ManaBar extends GraphicLoader {
    private float width, height;
    private float offsetPositionY = 0.6f;
    private float offsetPositionX = -0.3f;
    private Vector position = new Vector();
    private Texture blueBarTexture, blackBarTexture;
    private VertexArray blueMesh, blackMesh;

    private SuperHero superHero;
    private Long start;
    private StackEvent stackEvent;


    public ManaBar(Builder builder) {
        super(builder);
        this.superHero = builder.superHero;
        this.blackBarTexture = builder.blackBarTexture;
        this.blueBarTexture = builder.blueBarTexture;
        stackEvent = StackEvent.getInstance();
        update();
    }

    @Override
    public void update() {
        updateManaBar();
    }

    public void updateManaBar() {
        setPositionX(superHero.getX() + offsetPositionX);
        setPositionY(superHero.getY() + offsetPositionY);
        this.blueMesh = getManaBarMesh("blue");
        this.blackMesh = getManaBarMesh("black");
        stackEvent.addHeroDto();
    }


    private VertexArray getManaBarMesh(String color) {
        float factor = getManaFactor(color);
        setWidth(factor);
        return updateMesh();
    }

    public void setWidth(float factor) {
        width = 0.65f;
        super.setWidth(width * factor);
    }

    private float getManaFactor(String textureType) {
        return textureType.equals("blue") ? getManaFactor() : 1f;
    }

    private Float getManaFactor() {
        if (Objects.isNull(superHero.getMana())) {
            return 1f;
        } else {
            float mana = superHero.getMana();
            return mana < 0 ? 0 : mana / 100f;
        }
    }

    public void renegerateMana() {
        if (Objects.isNull(start)) {
            start = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - start > superHero.getManaRenegeration()) {
            addMana();
            updateManaBar();
            start = null;
        }
    }

    private void addMana() {
        Integer mana = superHero.getMana();
        if (mana <= 98) {
            superHero.setMana(mana + 2);
        } else if (mana == 99) {
            superHero.setMana(mana + 1);
        }
    }

    @Override
    public void render() {
        Shader.STRIP.enable();
        Shader.STRIP.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        blueBarTexture.bind();
        blueMesh.render();
        blackBarTexture.bind();
        blackMesh.render();
        Shader.STRIP.disable();
    }

    public static class Builder extends GraphicLoader.Builder<Builder> {
        private SuperHero superHero;
        private Texture blackBarTexture;
        private Texture blueBarTexture;


        public ManaBar.Builder withHero(SuperHero superHero) {
            this.superHero = superHero;
            return this;
        }

        public ManaBar.Builder withBlackBarTexture(Texture texture) {
            this.blackBarTexture = texture;
            return this;
        }

        public ManaBar.Builder withBlueBarTexture(Texture texture) {
            this.blueBarTexture = texture;
            return this;
        }

        public Builder() {
        }

        public ManaBar build() {
            return new ManaBar(this);
        }
    }
}



