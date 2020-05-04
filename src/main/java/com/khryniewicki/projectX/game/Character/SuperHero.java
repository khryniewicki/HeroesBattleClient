package com.khryniewicki.projectX.game.Character;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.Attack.Spell;
import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

@Data
public abstract class   SuperHero {

    public float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;
    private Texture heroUp,heroDown,heroLeft,heroRight,heroIdle;
    private Vector position;
    public static boolean isMovingLeft;

    private Spell spell;
    public static float hero_positionX0;
    public static float hero_positionY0;

    private Application.MyStompSessionHandler application = new Application.MyStompSessionHandler();

    public VertexArray createHero() {
        int i=isMovingLeft?-1:1;
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
        texture=heroIdle;
        return new VertexArray(vertices, indices, tcs);
    }


    public void update(){
    glfwSetKeyCallback(HelloWorld.window, (window, key, scancode, action, mods) -> {
        isMovingLeft=false;
        if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_UP && action != GLFW_RELEASE && !Collision.collisions[2]) {
            position.y += 0.2f;
            texture = heroUp;
            setMesh(createHero());

        } else if (key == GLFW.GLFW_KEY_DOWN && action != GLFW_RELEASE && !Collision.collisions[3]) {
            position.y -= 0.2f;
            texture = heroDown;
            setMesh(createHero());


        } else if (key == GLFW.GLFW_KEY_LEFT && action != GLFW_RELEASE && !Collision.collisions[1]) {
            position.x -= 0.2f;
            texture = heroLeft;
            isMovingLeft=true;
            setMesh(createHero());


        } else if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT && action != GLFW_RELEASE && !Collision.collisions[0]) {
            position.x += 0.2f;
            texture = heroRight;
            setMesh(createHero());
        } else {
            texture = heroIdle;
        }
        application.sendHeroCoordinatesFromWebsocket();
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
