package com.khryniewicki.projectX.game.user_interface.board;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.collision.Collision;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.properties.Ultra;
import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.playerBar.PlayerBar;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.game.user_interface.symbols.GameSymbol;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Slf4j
public class Board {

    public static Collision collision;
    private SuperHero hero;
    private UltraHero mock;
    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;
    private UltraSpell basicSpellMock;
    private UltraSpell ultimateSpellMock;

    private List<Symbol> symbols;
    private List<UltraSpell> spells;
    private List<UltraHero> heroes;
    private List<BoardObjects> obstacles;
    private List<BoardObjects> terrains;

    private Board() {
        createBackground();
        obstacles = ObstacleStorage.getObstacle();
        terrains = ObstacleStorage.getTerrainList();
        createCollision();
        createHeroes();
        createSpells();
    }

    private void createBackground() {
        Symbol background = new GameSymbol.Builder(GameUtill.BACKGROUND, -10f, -10.0f * 9.0f / 16.0f)
                .withVisibility(0.0f)
                .withWidth(20f)
                .withHeight(19 * 9.0f / 16.0f)
                .build();

        Symbol bar = new GameSymbol.Builder(GameUtill.BAR, -10f, 9.0f * 9.0f / 16.0f)
                .withVisibility(0.9f)
                .withWidth(20f)
                .withHeight(9.0f / 16.0f)
                .build();

        Symbol playerBar = new PlayerBar();
        symbols = new ArrayList<>(Arrays.asList(background, bar, playerBar));
    }

    private void createCollision() {
        collision = new Collision();
    }

    private void createHeroes() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        hero = heroesInstances.getHero();
        mock = heroesInstances.getMock();
        heroes = new ArrayList<>(Arrays.asList(hero, mock));

    }

    private void createSpells() {
        basicSpell = hero.getBasicSpell();
        ultimateSpell = hero.getUltimateSpell();
        basicSpellMock = mock.getBasicSpell();
        ultimateSpellMock = mock.getUltimateSpell();
        spells = new ArrayList<>(Arrays.asList(basicSpell, basicSpellMock, ultimateSpell, ultimateSpellMock));
    }

    public void update() {
        heroes.forEach(Ultra::update);
        spells.forEach(Ultra::update);
        symbols.forEach(Symbol::update);
    }

    public void render() {
        symbols.forEach(Symbol::render);
        collision.test();
        heroes.forEach(Ultra::render);
        spells.forEach(spell -> {
            if (spell.isSpellActivated()) {
                spell.render();
            }
        });

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

    public static Board getInstance() {
        return HELPER.INSTANCE;
    }

    private static final class HELPER {
        private final static Board INSTANCE = new Board();
    }
}

