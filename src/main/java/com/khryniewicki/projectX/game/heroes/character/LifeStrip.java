package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class LifeStrip {

    private Vector position = new Vector();

    private static Texture greenTexture;
    private static Texture blackTexture;
    private VertexArray greenMesh;
    private VertexArray blackMesh;

    private float width;
    private float height;

    private SuperHero hero;
    private StartingPosition startingPosition;

    public LifeStrip() {
    }

    public LifeStrip(StartingPosition startingPosition2) {
        height = 0.07f;
        width = 0.65f;
        hero = HeroesInstances.getInstance().getHero();
        startingPosition = startingPosition2;
        createLifeStrip();
    }


    public float getLifeFactor() {
        float LifeFactor;
        if (hero.getLife() == null) {
            LifeFactor = 1f;
        } else {
            LifeFactor = hero.getLife() / 100f;
            if (LifeFactor < 0) {
                LifeFactor = 0;
            }
        }

        return LifeFactor;
    }

    public void createLifeStrip() {

        setGreenMesh(createVertexArray("green"));
        setBlackMesh(createVertexArray("black"));
        blackTexture = GameUtill.empty;
        greenTexture = GameUtill.life;
    }

    public VertexArray createVertexArray(String textureType) {
        float lifeFactor = getLifeFactor(textureType);

        float offsetPositionY = 0.5f;
        float offsetPositionX = -0.3f;
        float heroPositionX = startingPosition.getX();
        float heroPositionY = startingPosition.getY();

        float[] vertices = new float[]{
                offsetPositionX + heroPositionX + 0.0f, offsetPositionY + heroPositionY + 0.0f, 1f,
                offsetPositionX + heroPositionX + 0.0f, offsetPositionY + heroPositionY + height, 1f,
                offsetPositionX + heroPositionX + lifeFactor * width, offsetPositionY + heroPositionY + height, 1f,
                offsetPositionX + heroPositionX + lifeFactor * width, offsetPositionY + heroPositionY + 0.0f, 1f
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

    private float getLifeFactor(String textureType) {
        float lifeFactor;
        if (textureType.equals("green")) {
            lifeFactor = getLifeFactor();
        } else {
            lifeFactor = 1f;
        }
        return lifeFactor;
    }


    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void render() {
        Shader.STRIP.enable();
        Shader.STRIP.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        greenTexture.bind();
        greenMesh.render();

        blackTexture.bind();
        blackMesh.render();

        Shader.STRIP.disable();
    }


}



