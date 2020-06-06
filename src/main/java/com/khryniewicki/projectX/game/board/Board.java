package com.khryniewicki.projectX.game.board;

import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.game.attack.attackSuccess.ActivatedAttack;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.SpellMock;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.Pointer;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;

import java.util.List;

@Data

public class Board {

    private VertexArray background;
    private Texture bgTexture;
    private Vector position = new Vector();

    public static SuperHero hero;
    public static UltraHero heroMock;
    private Spell spell;
    private UltraSpell spellMock;
    private ActivatedAttack activatedAttack;

    private Pointer pointer;
    public static Collision myCollision;
    private List<BoardObjects> obstacles;
    private List<BoardObjects> terrains;

    private boolean pointerON = false;

    public Board() {
        initVertex();
        initBackgroundTextures();

        myCollision = new Collision();
        pointer = new Pointer();
        HeroesInstances instance = HeroesInstances.getInstance();
        hero = instance.getHero();
        heroMock = new HeroMock(instance.getMock());
        spell = hero.castingSpell();
        spellMock = new SpellMock(heroMock.getSpell());


    }

    private void initBackgroundTextures() {
        bgTexture = new Texture("res/desertforum.png");
        obstacles = ObstacleStorage.getObstacle();
        terrains = ObstacleStorage.getTerrainList();
    }

    private void initVertex() {
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
    }

    public void update() {
        hero.update();
        heroMock.update();
        spell.update();
        spellMock.update();
        if (pointerON)
            pointer.update();
    }

    public void render() {
        renderBackground();

        myCollision.collisionTest(hero);

        hero.render();
        heroMock.render();
        spell.render();
        spellMock.render();

        if (pointerON)
            pointer.render();

    }

    private void renderBackground() {
        bgTexture.bind();
        Shader.BG.enable();
        background.bind();

        Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector(0f, 0.0f, 0.0f)));
        background.draw();


        background.render();
        Shader.BG.disable();
        bgTexture.unbind();

        renderObstacles();
        renderTerrains();
    }

    public void renderTerrains() {
        Shader.TERRAIN.enable();
        Terrain.getTexture().bind();

        for (BoardObjects terrain : terrains) {
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
        for (BoardObjects obstacle : obstacles) {
            obstacle.getMesh().bind();
            Shader.OBSTACLE.setUniformMat4f("ml_matrix", obstacle.getModelMatrix());
            obstacle.getMesh().draw();
            obstacle.getMesh().unbind();
        }

        Obstacle.getTexture().unbind();
        Shader.OBSTACLE.disable();
    }
}

