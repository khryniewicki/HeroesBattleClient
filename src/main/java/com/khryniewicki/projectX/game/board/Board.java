package com.khryniewicki.projectX.game.board;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackExecution;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.collision.Collision;
import com.khryniewicki.projectX.game.heroes.character.Pointer;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class Board {

    private VertexArray background;
    private Texture bgTexture;
    private Vector position = new Vector();

    public static SuperHero hero;
    public static UltraHero mock;
    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;

    private UltraSpell basicSpellMock;
    private UltraSpell ultimateSpellMock;
    private AttackExecution attackExecution;
    private StackEvent stackEvent;
    private Pointer pointer;
    public static Collision myCollision;
    private List<BoardObjects> obstacles;
    private List<BoardObjects> terrains;

    private boolean renderBG = true;

    private Board() {
        initVertex();
        initBackgroundTextures();

        myCollision = new Collision();
        stackEvent = StackEvent.getInstance();
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        hero = heroesInstances.getHero();
        mock = heroesInstances.getMock();
        basicSpell = hero.getBasicSpell();
        ultimateSpell = hero.getUltimateSpell();

        basicSpellMock = mock.getBasicSpell();
        ultimateSpellMock = mock.getUltimateSpell();
    }

    public static Board getInstance() {
        return HELPER.INSTANCE;
    }

    private void initBackgroundTextures() {
        bgTexture = new Texture("desertBackground.png");
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
        mock.update();
        basicSpell.update();
        ultimateSpell.update();

        basicSpellMock.update();
        ultimateSpellMock.update();
    }

    public void render() {

        renderBackground();
        myCollision.collisionTest();

        hero.render();
        if (!stackEvent.isBasicSpellActivated())
            basicSpell.render();

        if (!stackEvent.isUltimateSpellActivated())
            ultimateSpell.render();

        mock.render();
        basicSpellMock.render();
        ultimateSpellMock.render();
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
    }


    private static final class HELPER {
        private final static Board INSTANCE = new Board();
    }
}

