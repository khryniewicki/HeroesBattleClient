package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;

import java.util.Optional;

import static org.lwjgl.glfw.GLFW.*;

@Data
public class SuperHero implements UltraHero {
    private boolean isMovingLeft;
    private VertexArray mesh;
    private Texture texture,heroUp, heroDown, heroLeft, heroRight, heroIdle;
    private Vector position;
    private Spell spell;
    private String name;
    public static float hero_positionX0;
    public static float hero_positionY0;
    private Application.MyStompSessionHandler application = new Application.MyStompSessionHandler();
    private float hero_standard_offset;
    private float hero_top_offset;
    public float SIZE = 0.9f;

    public VertexArray createHero() {
        int i = isMovingLeft ? -1 : 1;
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

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                i * 1, 0,
                i * 1, 1
        };

        return new VertexArray(vertices, indices, tcs);
    }


    public void update() {
        glfwSetKeyCallback(HelloWorld.window, (window, key, scancode, action, mods) -> {
                    SIZE = 1f;
                    if (key == GLFW_KEY_UP && action != GLFW_RELEASE && !Collision.collisions[2]) {
                        position.y += 0.2f;
                        texture = heroUp;
                    } else if (key == GLFW_KEY_DOWN && action != GLFW_RELEASE && !Collision.collisions[3]) {
                        position.y -= 0.2f;
                        texture = heroDown;
                    } else if (key == GLFW_KEY_LEFT && action != GLFW_RELEASE && !Collision.collisions[1]) {
                        position.x -= 0.2f;
                        isMovingLeft = true;
                        texture = heroLeft;
                    } else if (key == GLFW_KEY_RIGHT && action != GLFW_RELEASE && !Collision.collisions[0]) {
                        isMovingLeft = false;
                        position.x += 0.2f;
                        texture = heroRight;
                    } else {
                        setSIZE(0.9f);
                        texture = heroIdle;
                    }
                    setMesh(createHero());
                    application.sendHeroToStompSocket();
                }
        );
    }


    public void render() {
        Shader.HERO.enable();
        Shader.HERO.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.HERO.disable();

    }


    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setMesh(VertexArray mesh) {
        this.mesh = mesh;
    }

    public Float getX() { return Optional.ofNullable(position.x).orElse(0f);}

    public Float getY() {
        return Optional.ofNullable(position.y).orElse(0f);
    }

    @Override
    public void setPositionX(Float positionX) {
        this.position.x = positionX;
    }

    @Override
    public void setPositionY(Float positionY) {
        this.position.y = positionY;
    }

    @Override
    public Spell getSpell() {
        return spell;
    }

    public Spell castingSpell() {
        return spell;
    }

    @Override
    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }


}
