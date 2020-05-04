package com.khryniewicki.projectX.game.Attack;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.game.Character.Hero;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.util.Optional;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

@Data
public abstract class Spell {
    private VertexArray mesh;
    private Texture texture;

    private Vector position;

    public float SIZE = 1.0f;



    public VertexArray createSpell() {

        float[] vertices = new float[]{
                0f + -SIZE / 2.0f, 0f + -SIZE / 2.0f, -0.1f,
                0f + -SIZE / 2.0f, 0f + SIZE / 2.0f, -0.1f,
                0f + SIZE / 2.0f, 0f + SIZE / 2.0f, -0.1f,
                0f + SIZE / 2.0f, 0f + -SIZE / 2.0f,-0.1f
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

    public abstract void update();



    public void render() {
        Shader.SPELL.enable();
        Shader.SPELL.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.SPELL.disable();
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
    public void setPositionZ(Float positionZ) {
        this.position.z = positionZ;
    }
}
