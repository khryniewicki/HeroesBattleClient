package com.khryniewicki.projectX.game.Character;

import com.khryniewicki.projectX.game.Attack.Spell;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;

import java.util.Optional;

@Data
public abstract class   SuperHero {

    public float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;

    private Vector position;
    private float rot;
    private float delta = 0.0f;
    public static boolean isMovingLeft;
    private Spell spell;

    public static float hero_positionX0;
    public static float hero_positionY0;
    private float[] tcs;

    public VertexArray isMovingLeft(boolean isMovingLeft) {
        int i = 1;
        if (isMovingLeft) {
            i = -1;
        }
        float[] vertices = new float[]{
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 0.8f,
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 0.8f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 0.8f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 0.8f
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        tcs = new float[]{
                0, 1,
                0, 0,
                i * 1, 0,
                i * 1, 1
        };
        return new VertexArray(vertices, indices, tcs);

    }

    public abstract void update();


    public void render() {
        Shader.HERO.enable();
        Shader.HERO.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.HERO.disable();

    }

    public VertexArray isMovingLeft() {
        return mesh;
    }


    public void setMesh(VertexArray mesh) {
        this.mesh = mesh;
    }

    public Float getX() {

        return Optional.ofNullable(position.x).orElse(0f);
    }

    public Float getY() {
        return Optional.ofNullable(position.y).orElse(0f);
    }

    public void setPositionX(Float positionX) {
        this.position.x = positionX;
    }

    public void setPositionY(Float positionY) {
        this.position.y = positionY;
    }

    public  Spell castingSpell(){
       return spell;
    };
}
