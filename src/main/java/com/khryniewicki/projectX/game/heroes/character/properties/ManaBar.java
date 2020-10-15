package com.khryniewicki.projectX.game.heroes.character.properties;

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

import java.util.Objects;

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

    public Float getManaFactor() {
        if (Objects.isNull(hero.getMana())) {
            return 1f;
        } else {
            float mana = hero.getMana();
            return mana < 0 ? 0 : mana / 100f;
        }
    }

    private float getManaFactor(String textureType) {
        return textureType.equals("blue") ? getManaFactor() : 1f;
    }


    public void updateManaBar() {
        setBlueMesh(createVertexArray("blue"));
        setBlackMesh(createVertexArray("black"));
        blackTexture = GameUtill.EMPTY;
        blueTexture = GameUtill.MANA;
        stackEvent.setHasAction(true);
    }

    public VertexArray createVertexArray(String textureType) {
        float manaFactor = getManaFactor(textureType);
        float offsetPositionY = 0.6f;
        float offsetPositionX = -0.3f;
        float heroPositionX = hero.getPosition().x;
        float heroPositionY = hero.getPosition().y;
        float visibility= 0.8f;
        float[] vertices = new float[]{
                offsetPositionX + heroPositionX , offsetPositionY + heroPositionY , visibility,
                offsetPositionX + heroPositionX , offsetPositionY + heroPositionY + height,visibility,
                offsetPositionX + heroPositionX + manaFactor * width, offsetPositionY + heroPositionY + height, visibility,
                offsetPositionX + heroPositionX + manaFactor * width, offsetPositionY + heroPositionY, visibility
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


    public void renegerateMana() {

        if (Objects.isNull(start)) {
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



