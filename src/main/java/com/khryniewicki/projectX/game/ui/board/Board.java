package com.khryniewicki.projectX.game.ui.board;

import com.khryniewicki.projectX.game.attack.spell.settings.UltraSpell;
import com.khryniewicki.projectX.game.ui.board.collision.Collision;
import com.khryniewicki.projectX.game.control.settings.mouse.MousePosition;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.properties.Ultra;
import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.ui.playerBar.PlayerBar;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khryniewicki.projectX.game.ui.board.GameFactory.BACKGROUND;
import static com.khryniewicki.projectX.game.ui.board.GameFactory.BAR;

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
    private MousePosition mousePosition;
    private List<BoardObjects> obstacles;
    private List<BoardObjects> terrains;
    private List<Symbol> symbols;
    private List<UltraSpell> spells;
    private List<UltraHero> heroes;
    private boolean notify;

    public Board() {
        createBackground();
        createHeroes();
        createSpells();
        obstacles = ObstacleStorage.getObstacle();
        terrains = ObstacleStorage.getTerrainList();
//        collision = new Collision();

//        mousePosition = new MousePosition();
//        addEventClick();
    }

    private void createBackground() {
        symbols = new ArrayList<>(Arrays.asList(BACKGROUND, BAR, new PlayerBar()));
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
        heroes.forEach(Ultra::render);
        spells.forEach(spell -> {
            if (spell.isSpellActivated()) {
                spell.render();
            }
        });
//        if (collision.isTest_square()) {
//            collision.getSquares().values().forEach(MenuSymbol::render);
//        }
//        renderTerrains();
//        renderObstacles();
    }

    public boolean player_dead() {
        return heroes.stream().anyMatch(ultraHero -> ultraHero.getLife() == 0);
    }

//    public void addEventClick() {
//        glfwSetMouseButtonCallback(GameUtil.window, (window, key, action, mods) -> {
//
//            if (key == 0 && action != GLFW_RELEASE) {
//
//                Position cursorPosition = mousePosition.getCursorPosition();
//                log.info("KURSOR: [{},  {}]", mousePosition.getWindowPositionX(), mousePosition.getWindowPositionY());
//
//                obstacles.stream()
//                        .filter(obstacles -> mousePosition.getWindowPositionX() > obstacles.getObstacle_positionX0() && mousePosition.getWindowPositionX() < obstacles.getObstacle_positionX1())
//                        .filter(obstacles -> mousePosition.getWindowPositionY() > obstacles.getObstacle_positionY0() && mousePosition.getWindowPositionY() < obstacles.getObstacle_positionY1())
//                        .findFirst()
//                        .ifPresent(obstacles -> System.out.println("OB " + obstacles.getName()));
//
//                terrains.stream()
//                        .filter(obstacles -> mousePosition.getWindowPositionX() > obstacles.getObstacle_positionX0() && mousePosition.getWindowPositionX() < obstacles.getObstacle_positionX1())
//                        .filter(obstacles -> mousePosition.getWindowPositionY() > obstacles.getObstacle_positionY0() && mousePosition.getWindowPositionY() < obstacles.getObstacle_positionY1())
//                        .findFirst()
//                        .ifPresent(obstacles -> System.out.println("TR " + obstacles.getName()));
//            }
//        });
//    }
//
//    public void renderTerrains() {
//        Shader.TERRAIN.enable();
//        Terrain.getTexture().bind();
//
//        for (BoardObjects terrain : terrains) {
//            terrain.getMesh().bind();
//            Shader.TERRAIN.setUniformMat4f("ml_matrix", terrain.getModelMatrix());
//            terrain.getMesh().draw();
//            terrain.getMesh().unbind();
//        }
//        Terrain.getTexture().unbind();
//        Shader.TERRAIN.disable();
//    }
//
//    public void renderObstacles() {
//        Shader.OBSTACLE.enable();
//        Obstacle.getTexture().bind();
//
//        for (BoardObjects obstacle : obstacles) {
//            obstacle.getMesh().bind();
//            Shader.OBSTACLE.setUniformMat4f("ml_matrix", obstacle.getModelMatrix());
//            obstacle.getMesh().draw();
//            obstacle.getMesh().unbind();
//        }
//
//        Obstacle.getTexture().unbind();
//        Shader.OBSTACLE.disable();
//    }

}

