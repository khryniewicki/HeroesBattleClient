package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.graphics.GraphicLoader;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.sending.StackEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static java.lang.System.currentTimeMillis;
import static java.util.Objects.isNull;

@Getter
@Setter
@Slf4j
public class ManaBar extends GraphicLoader {
    private static final String BLUE = "blue";
    private static final String BLACK = "black";
    private Vector position = new Vector();
    private Texture blueBarTexture, blackBarTexture;
    private VertexArray blueMesh, blackMesh;
    private Long madaRegeneration = 1000L;
    private UltraHero hero;
    private Long start;
    private StackEvent stackEvent;
    private Float maxMana;
    private float width, height;
    private float offsetPositionY = 0.6f;
    private float offsetPositionX = -0.3f;
    private int tmpMana = 0;
    private float tmpHeroX = 0F;
    private float tmpHeroY = 0F;

    public ManaBar(Builder builder) {
        super(builder);
        this.hero = builder.hero;
        this.blackBarTexture = builder.blackBarTexture;
        this.blueBarTexture = builder.blueBarTexture;
        this.maxMana = builder.maxMana;
        stackEvent = StackEvent.getInstance();
        updateManaBar();
    }

    @Override
    public void update() {
        renegerateMana();
        if (hero.getX() != tmpHeroX || hero.getY() != tmpHeroY || hero.getMana() != tmpMana) {
            updateManaBar();
        }

    }

    public void updateManaBar() {
        setPositionX(hero.getX() + offsetPositionX);
        setPositionY(hero.getY() + offsetPositionY);
        this.blueMesh = getManaBarMesh(BLUE);
        this.blackMesh = getManaBarMesh(BLACK);
        this.tmpMana = hero.getLife();
        this.tmpHeroX = hero.getX();
        this.tmpHeroY = hero.getY();
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
        return textureType.equals(BLUE) ? getManaFactor() : 1f;
    }

    private Float getManaFactor() {
        if (isNull(hero.getMana())) {
            return 1f;
        } else {
            float mana = hero.getMana();
            return mana < 0 ? 0 : mana / maxMana;
        }
    }

    public void renegerateMana() {
        if (isNull(start)) {
            start = currentTimeMillis();
        }
        if (currentTimeMillis() - start > madaRegeneration) {
            start = null;
            addMana();
            stackEvent.addHeroDto();
        }
    }

    private void addMana() {
        Float mana = hero.getMana();
        Float manaRegeneration = hero.getManaRegeneration();
        if (mana <= maxMana - manaRegeneration) {
            hero.setMana(mana + manaRegeneration);
        } else
            hero.setMana(maxMana);
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
        private UltraHero hero;
        private Texture blackBarTexture;
        private Texture blueBarTexture;
        private Float maxMana;

        public ManaBar.Builder withHero(UltraHero hero) {
            this.hero = hero;
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

        public ManaBar.Builder withMaxMana(Float maxMana) {
            this.maxMana = maxMana;
            return this;
        }

        public Builder() {
        }

        public ManaBar build() {
            return new ManaBar(this);
        }
    }
}



