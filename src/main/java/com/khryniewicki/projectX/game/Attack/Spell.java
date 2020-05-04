package com.khryniewicki.projectX.game.Attack;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.game.Character.Hero;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.KnightIMG;
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
    private Float relativeX,relativeY;
    private Float distanceX,distanceY;
    private Float castingSpeed;
    private Long startSpell=null;
    public float SIZE = 1.0f;

    public VertexArray createSpell() {

        float[] vertices = new float[]{
                0f + -SIZE / 2.0f, 0f + -SIZE / 2.0f, -0.1f,
                0f + -SIZE / 2.0f, 0f + SIZE / 2.0f, -0.1f,
                0f + SIZE / 2.0f, 0f + SIZE / 2.0f, -0.1f,
                0f + SIZE / 2.0f, 0f + -SIZE / 2.0f, -0.1f
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

    public void update() {
        getMousePosition();

        if (relativeX != null && relativeY != null) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                position.x += Math.signum(distanceX) * 0.2f;
                position.y += (distanceY) / Math.abs(distanceX) * 0.2f;
            } else {
                position.x += (distanceX) / Math.abs(distanceY) * 0.2f;
                position.y += Math.signum(distanceY) * 0.2f;
            }

            if (Math.abs(position.x - relativeX) < 0.1 && Math.abs(position.y - relativeY) < 0.1) {
                startSpell = System.currentTimeMillis();
                position.x = relativeX;
                position.y = relativeY;
                relativeY = null;
                relativeX = null;
                setTexture(KnightIMG.FIRE);
            }
        }
        if (startSpell != null && System.currentTimeMillis() - startSpell > 4000) {
            setPositionZ(-1f);
            startSpell = null;
        }
    }

    public void getMousePosition() {
        glfwSetMouseButtonCallback(HelloWorld.window, (window, key, action, mods) -> {
            DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
            glfwGetCursorPos(HelloWorld.window, xBuffer, yBuffer);
            double x = xBuffer.get(0);
            double y = yBuffer.get(0);
            if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE) {
                setTexture(KnightIMG.FIREBALL);
                setPositionX(Level.getHero_x());
                setPositionY(Level.getHero_y());
                setRelativeX((float) (x - HelloWorld.width / 2) / (HelloWorld.width / 20));
                setRelativeY((float) (HelloWorld.height / 2 - y) / (HelloWorld.height / 10));
                setPositionZ(1f);
                distanceX = relativeX - Level.getHero_x();
                distanceY = relativeY - Level.getHero_y();
            }
        });
    }


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
