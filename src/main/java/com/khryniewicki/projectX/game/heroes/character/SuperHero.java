package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.collision.Collision;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroSendingService;
import com.khryniewicki.projectX.utils.HeroMove;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.lwjgl.glfw.GLFW.*;

@Data
@Slf4j
@Service
public class SuperHero implements UltraHero {
    private boolean isMovingLeft;
    private VertexArray mesh;
    private Texture texture, heroUp, heroDown, heroLeft, heroRight, heroIdle;
    private Vector position;
    private Spell spell;
    private String name;
    private Integer life, mana;
    public static float hero_positionX0;
    public static float hero_positionY0;
    private HeroSendingService heroSendingService;
    private float hero_standard_offset;
    private float hero_top_offset;
    public float SIZE = 0.9f;
    private float velocity = 0.2f;
    private HeroMove heroMove;
    public SuperHero() {
        heroSendingService = new HeroSendingService();
        heroMove=HeroMove.getInstance();
    }

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
        glfwSetKeyCallback(Game.window, (window, key, scancode, action, mods) -> {

                    SIZE = 1f;
                    float tmpX = getX();
                    float tmpY = getY();
                    if (key == GLFW_KEY_UP && action != GLFW_RELEASE && !Collision.collisions[2]) {
                        position.y += velocity;
                        texture = heroUp;
                    } else if (key == GLFW_KEY_DOWN && action != GLFW_RELEASE && !Collision.collisions[3]) {
                        position.y -= velocity;
                        texture = heroDown;
                    } else if (key == GLFW_KEY_LEFT && action != GLFW_RELEASE && !Collision.collisions[1]) {
                        position.x -= velocity;
                        isMovingLeft = true;
                        texture = heroLeft;
                    } else if (key == GLFW_KEY_RIGHT && action != GLFW_RELEASE && !Collision.collisions[0]) {
                        isMovingLeft = false;
                        position.x += velocity;
                        texture = heroRight;
                    } else {
                        setSIZE(0.9f);
                        texture = heroIdle;
                    }

                    if (tmpX != position.x || tmpY != position.y) {
                        heroMove.setHeroMoving(true);
                        setMesh(createHero());
                    }else
                        heroMove.setHeroMoving(false);

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

    public synchronized Float getX() {
        return position.x;
    }

    public synchronized Float getY() {
        return position.y;
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
