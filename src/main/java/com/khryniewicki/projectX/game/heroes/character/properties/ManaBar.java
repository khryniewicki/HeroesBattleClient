package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ManaBar {

    private Vector position = new Vector();
    private static Texture blueTexture, blackTexture;
    private VertexArray blueMesh, blackMesh;
    private float width, height;

    private UltraHero hero;
    private StartingPosition startingPosition;
    private Long start;
    private StackEvent stackEvent;

    public ManaBar(UltraHero ultraHero) {
        height = 0.07f;
        width = 0.65f;
        hero = ultraHero;
        stackEvent=StackEvent.getInstance();
        updateManaBar();

    }


    public float getManaFactor() {
        float ManaFactor;
        if (hero.getMana() == null) {
            ManaFactor = 1f;
        } else {
            ManaFactor = hero.getMana() / 100f;
            if (ManaFactor < 0) {
                ManaFactor = 0;
            }
        }

        return ManaFactor;
    }

    public void updateManaBar() {

        setBlueMesh(createVertexArray("blue"));
        setBlackMesh(createVertexArray("black"));
        blackTexture = GameUtill.empty;
        blueTexture = GameUtill.mana;
        stackEvent.setHasAction(true);
    }

    public VertexArray createVertexArray(String textureType) {
        float manaFactor = getManaFactor(textureType);
        float offsetPositionY = 0.6f;
        float offsetPositionX = -0.3f;
        float heroPositionX = hero.getPosition().x;
        float heroPositionY = hero.getPosition().y;

        float[] vertices = new float[]{
                offsetPositionX + heroPositionX , offsetPositionY + heroPositionY , 0.8f,
                offsetPositionX + heroPositionX , offsetPositionY + heroPositionY + height,0.8f,
                offsetPositionX + heroPositionX + manaFactor * width, offsetPositionY + heroPositionY + height, 0.8f,
                offsetPositionX + heroPositionX + manaFactor * width, offsetPositionY + heroPositionY + 0.0f, 0.8f
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

    private float getManaFactor(String textureType) {
        float manaFactor;
        if (textureType.equals("blue")) {
            manaFactor = getManaFactor();
        } else {
            manaFactor = 1f;
        }
        return manaFactor;
    }

    public void renegerateMana() {

        if (start == null) {
            start = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - start > 3000) {
            addMana();
            updateManaBar();
            start = null;
        }
    }

    private void addMana() {
        Integer mana = hero.getMana();

        if (mana <= 98) {
            hero.setMana(mana + 2);
        }else if (mana==99){
            hero.setMana(mana + 1);
        }
    }

    public void render() {
        Shader.STRIP.enable();
        Shader.STRIP.setUniformMat4f("ml_matrix", Matrix4f.translate(position));

        blueTexture.bind();
        blueMesh.render();

        blackTexture.bind();
        blackMesh.render();

        Shader.STRIP.disable();

    }


}



