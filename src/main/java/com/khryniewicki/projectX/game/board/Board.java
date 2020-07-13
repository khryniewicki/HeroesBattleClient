package com.khryniewicki.projectX.game.board;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.collision.Collision;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.Ultra;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Slf4j
public class Board {

    private VertexArray background;
    private Texture bgTexture;
    private Vector position = new Vector();
    public static Collision collision;

    public  SuperHero hero;
    public  UltraHero mock;

    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;
    private UltraSpell basicSpellMock;
    private UltraSpell ultimateSpellMock;

    private List<UltraSpell> spells;
    private List<UltraHero> heroes;


    private Board() {
        initBackgroundTexture();
        createCollsion();
        createHeroes();
        createSpells();
    }
    private void initBackgroundTexture() {
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
        bgTexture = new Texture("desertBackground.png");
        background = new VertexArray(vertices, indices, tcs);
    }

    private void createCollsion() {
        collision = new Collision();
    }

    private void createHeroes() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        hero = heroesInstances.getHero();
        mock = heroesInstances.getMock();

        UltraHero[] ultraHeroes = {hero, mock};
        heroes=new ArrayList<>();
        heroes.addAll(Arrays.asList(ultraHeroes));

    }

    private void createSpells() {
        basicSpell = hero.getBasicSpell();
        ultimateSpell = hero.getUltimateSpell();
        basicSpellMock = mock.getBasicSpell();
        ultimateSpellMock = mock.getUltimateSpell();

        UltraSpell[] ultraSpells = {basicSpell, basicSpellMock, ultimateSpell, ultimateSpellMock};
        spells=new ArrayList<>();
        spells.addAll(Arrays.asList(ultraSpells));
    }


    public void update() {
        heroes.forEach(Ultra::update);
        spells.forEach(Ultra::update);
    }

    public void render() {
        renderBackground();
        collision.test();

        heroes.forEach(Ultra::render);

        spells.forEach(spell -> {
            if (spell.isSpellActivated()) {
                spell.render();
            }
        });

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

    public static Board getInstance() {
        return HELPER.INSTANCE;
    }

    private static final class HELPER {
        private final static Board INSTANCE = new Board();
    }
}

