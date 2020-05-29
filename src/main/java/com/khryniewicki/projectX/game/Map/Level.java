package com.khryniewicki.projectX.game.Map;

import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.SpellMock;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.HeroMock;
import com.khryniewicki.projectX.game.heroes.character.Pointer;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.heroes.wizards.FireWizard;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;

import java.util.List;

@Data
public class Level {

    private VertexArray background;
    private Texture bgTexture;
    private Vector position = new Vector();
    private List<MapObstacles> obstacles;
    private List<MapObstacles> terrains;

    public static SuperHero hero;
    public static UltraHero heroMock;
    private Spell spell;
    private UltraSpell spellMock;
    private Collision myCollision;
    public static Float getHero_x() {
        return hero == null ? GameUtill.heroStartingPositionX : hero.getX();
    }
    public static Float getHero_y() {
        return hero == null ? GameUtill.heroStartingPositionY : hero.getY();
    }




    private Pointer pointer;
    private boolean pointerON = false;

    public Level() {
        initVertexArray();
        initBackground();
        pointer = new Pointer();
        myCollision=new Collision();

        hero = new FireWizard();
        heroMock = new HeroMock(new FireWizard());
        spell = hero.castingSpell();
        spellMock = new SpellMock(heroMock.getSpell());
    }

    private void initVertexArray() {
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

    private void initBackground() {
        bgTexture = new Texture("res/desertforum.png");
        obstacles = ObstacleStorage.getObstacle();
        terrains = ObstacleStorage.getTerrainList();
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

        hero.render();
        heroMock.render();
        spell.render();
        spellMock.render();
        myCollision.collisionTest(hero);
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

}

