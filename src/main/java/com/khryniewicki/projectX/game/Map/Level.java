package com.khryniewicki.projectX.game.Map;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.game.attack.Spell;
import com.khryniewicki.projectX.game.heroes.character.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.Pointer;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.heroes.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.wizards.ThunderWizard;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.util.List;

@Data

public class Level {

    private VertexArray background;
    private Texture bgTexture;

    private int xScroll = 0;
    private int map = 0;
    private Vector position = new Vector();

    public static SuperHero hero;
    public static UltraHero newheroMock;

    public static Float getHero_x() {
        return hero == null ? GameUtill.heroStartingPositionX : hero.getX();
    }
    public static Float getHero_y() {
        return hero == null ? GameUtill.heroStartingPositionY : hero.getY();
    }

    private Pointer pointer;
    private Collision MyCollision;
    private List<MapObstacles> obstacles;
    private List<MapObstacles> terrains;
    private Spell spell;
    private boolean pointerON = false;
    public static boolean collision_left, collision_right, collision_up, collision_down = false;
    public static boolean[] collisions = new boolean[]{collision_right, collision_left, collision_up, collision_down};
    public static StompSessionHandler application;

    public Level() {
        float[] vertices = new float[]{
                -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
                -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                10.0f, -10.0f * 9.0f / 16.0f, 0.0f
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

        background = new VertexArray(vertices, indices, tcs);
        bgTexture = new Texture("res/desertforum.png");


        hero = new IceWizard();
        newheroMock = new HeroMock(new ThunderWizard());
        pointer = new Pointer();
        obstacles = ObstacleStorage.getObstacle();
        terrains = ObstacleStorage.getTerrainList();
        MyCollision = new Collision();
        spell = hero.castingSpell();
        application = Application.sessionHandler;
    }

    public void renderTerrains() {
        Shader.TERRAIN.enable();
        Terrain.getTexture().bind();

        for (MapObstacles terrain : terrains) {
            terrain.getMesh().bind();
            Shader.TERRAIN.setUniformMat4f("ml_matrix", terrain.getModelMatrix());
            terrain.getMesh().draw();
            terrain.getMesh().unbind();
        }
        Terrain.getTexture().unbind();
        Shader.TERRAIN.disable();
    }

    public void renderObstacles() {
        Shader.OBSTACLE.enable();
        Obstacle.getTexture().bind();
        for (MapObstacles obstacle : obstacles) {
            obstacle.getMesh().bind();
            Shader.OBSTACLE.setUniformMat4f("ml_matrix", obstacle.getModelMatrix());
            obstacle.getMesh().draw();
            obstacle.getMesh().unbind();
        }

        Obstacle.getTexture().unbind();
        Shader.OBSTACLE.disable();
    }


    public void update() {
        hero.update();
        newheroMock.update();
        spell.update();
        if (pointerON)
            pointer.update();
    }

    public void render() {
        bgTexture.bind();
        Shader.BG.enable();
        background.bind();
        for (int i = 0; i < map + 3; i++) {
            Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector(i * 10 + xScroll * 0.03f, 0.0f, 0.0f)));
            background.draw();
        }

        background.render();
        Shader.BG.disable();
        bgTexture.unbind();

        renderObstacles();
        renderTerrains();


        MyCollision.collisionTest(hero);

        hero.render();
        newheroMock.render();
        spell.render();
        if (pointerON)
            pointer.render();

    }


}

