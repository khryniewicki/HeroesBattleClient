package com.khryniewicki.projectX.game.control_settings.collision;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.board.BoardObjects;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Data
@Slf4j
public class Collision {

    private float hero_left_offset, hero_top_offset;
    private float delta_x = 0.1f, delta_y = 0.1f;
    private float bx, by, bx0, bx1, by0, by1, px0, px1, py0, py1;
    private boolean isCollision;
    //RIGHT | LEFT | UP | DOWN
    public static Boolean[] collisions = new Boolean[4];
    private static Boolean[] BoundaryCollisions = new Boolean[4];
    private static Boolean[] ObstacleCollisions = new Boolean[4];
    private static Boolean[] TerrainCollisions = new Boolean[4];
    private HashMap<BoardObjects, List<Boolean>> mapObstaclesListHashMap;
    private SuperHero hero;

    private List<BoardObjects> obstacleList_BL = ObstacleStorage.getObstacleList_BL();
    private List<BoardObjects> obstacleList_BR = ObstacleStorage.getObstacleList_BR();
    private List<BoardObjects> obstacleList_TL = ObstacleStorage.getObstacleList_TL();
    private List<BoardObjects> obstacleList_TR = ObstacleStorage.getObstacleList_TR();
    private List<BoardObjects> terrainList = ObstacleStorage.getTerrainList();

    private boolean test_square = true;
    private Map<Position, MenuSymbol> squares = new HashMap<>();

    public MenuSymbol createSquare(Position position) {
        System.out.println("SQUARES: " + squares.size());

        return new MenuSymbol.Builder()
                .withTexture(new Texture("red_square.png"))
                .withName("text")
                .withHeight(0.2f)
                .withWidth(0.2f)
                .withPositionX(position.getX())
                .withPositionY(position.getY())
                .withVisibility(1.5f)
                .build();
    }

    public void test() {
        if (hero == null) {
            HeroesInstances heroesInstances = HeroesInstances.getInstance();
            setHero(heroesInstances.getHero());
            hero_top_offset = hero.getHero_top_offset();
            hero_left_offset = hero.getHero_left_offset();
        }
        bx = hero.getX();
        by = hero.getY();

        obstacleCollision(checkInWhichQuerterIsHero());
        terrainCollision(terrainList);
        boundaryCollision();
        checkCollisionForSpecificDirection();
    }

    private MAP_QUARTERS checkInWhichQuerterIsHero() {
        MAP_QUARTERS quarter;
        if (bx > 0 && by > 0) {
            quarter = MAP_QUARTERS.TOP_RIGHT;
        } else if (bx > 0 && by < 0) {
            quarter = MAP_QUARTERS.BOTTOM_RIGHT;
        } else if (bx < 0 && by > 0) {
            quarter = MAP_QUARTERS.TOP_LEFT;
        } else {
            quarter = MAP_QUARTERS.BOTTOM_LEFT;
        }
        return quarter;
    }

    private void checkCollisionForSpecificDirection() {
        Arrays.fill(collisions, false);
        for (int i = 0; i < 4; i++) {
            if (BoundaryCollisions[i] || ObstacleCollisions[i] || TerrainCollisions[i])
                collisions[i] = true;
//            log.info(Arrays.toString(collisions));
        }

        if (test_square) {
            boolean b = Arrays.stream(collisions).allMatch(c -> c.equals(true));
            if (b) {
                Position position = new Position(hero.getX(), hero.getY());
                squares.put(position, createSquare(position));
            }
        }
    }

    private void boundaryCollision() {
        Arrays.fill(BoundaryCollisions, false);
        if (bx >= 9.5f) {
            BoundaryCollisions[0] = true;
        } else if (bx <= -9.5f) {
            BoundaryCollisions[1] = true;
        } else if (by >= 4.6f) {
            BoundaryCollisions[2] = true;
        } else if (by <= -5.0f) {
            BoundaryCollisions[3] = true;
        }
    }

    private void obstacleCollision(MAP_QUARTERS map_quarter) {
        switch (map_quarter) {
            case BOTTOM_LEFT:
                obstacleCollisionInQuarter(obstacleList_BL);
                return;
            case BOTTOM_RIGHT:
                obstacleCollisionInQuarter(obstacleList_BR);
                return;
            case TOP_LEFT:
                obstacleCollisionInQuarter(obstacleList_TL);
                return;
            case TOP_RIGHT:
                obstacleCollisionInQuarter(obstacleList_TR);
                return;
            default:
                throw new IllegalArgumentException("There is no map quarter");
        }

    }


    public void obstacleCollisionInQuarter(List<BoardObjects> mapObstacles) {
        checkConditionForObstacleOrTerrain(mapObstacles, ObstacleCollisions);
    }

    public void terrainCollision(List<BoardObjects> mapObstacles) {
        checkConditionForObstacleOrTerrain(mapObstacles, TerrainCollisions);
    }

    private void checkConditionForObstacleOrTerrain(List<BoardObjects> mapObstacles, Boolean[] obstacleOrTerrainCollisions) {
        Arrays.fill(obstacleOrTerrainCollisions, false);
        if (collision(mapObstacles)) {
            mapObstaclesListHashMap.values().forEach(e -> {
                for (int i = 0; i < e.size(); i++) {
                    Boolean isCollisionWithObstacle = e.get(i);
                    if (isCollisionWithObstacle)
                        obstacleOrTerrainCollisions[i] = true;
                }
            });
        }
    }

    public Boolean collision(List<BoardObjects> obstacles) {

        bx0 = bx - hero.SIZE / 2.0f + hero_left_offset;
        bx1 = bx + hero.SIZE / 2.0f - hero_left_offset;
        by0 = by - hero.SIZE / 2.0f + hero_left_offset;
        by1 = by + hero.SIZE / 2.0f - hero_top_offset;
        isCollision = false;
        mapObstaclesListHashMap = new HashMap<>();
        float proximtyValue;

        for (BoardObjects obstacle : obstacles) {
            float tangens = obstacle.getTangens();

            if (tangens == 0) {
                px0 = (obstacle.getObstacle_positionX0() - SuperHero.hero_positionX0) + delta_x;
                py0 = (obstacle.getObstacle_positionY0() - SuperHero.hero_positionY0) + delta_y;
                px1 = px0 + obstacle.getWidth();
                py1 = py0 + obstacle.getHeight();
                proximtyValue = 0.21f;
            } else {
                float px = (obstacle.getObstacle_positionX0() - SuperHero.hero_positionX0);
                float py = (obstacle.getObstacle_positionY0() - SuperHero.hero_positionY0);
                float pX = (obstacle.getObstacle_positionX1() - SuperHero.hero_positionX0);
                float pY = (obstacle.getObstacle_positionY1() - SuperHero.hero_positionY0);
                float X = 1.0f;
                if (by1 < py) X = 0;
                if (bx1 < px) X = 0;
                if (by0 > pY) X = 0;
                if (bx0 > pX) X = 0;

                px0 = px + X * (by0 - py) / tangens;
                py0 = py + X * (bx0 - px) * tangens;
                px1 = px + X * (by1 - py - 0.2f) / tangens + 0.2f;
                py1 = py + X * (bx1 - px - 0.2f) * tangens + 0.2f;
                proximtyValue = 0.3f;

            }
            float[] obstacleCoordinates = {px0, px1, py0, py1};
            float[] heroCoordinates = {bx1, bx0, by1, by0};
            List<Boolean> ListWithDirectionCollision = Arrays.asList(collisions);
            mapObstaclesListHashMap.put(obstacle, ListWithDirectionCollision);
            if (bx1 > px0 && bx0 < px1) {
                if (by1 > py0 && by0 < py1) {
                    ListWithDirectionCollision = new ArrayList<>();
                    for (int i = 0; i < obstacleCoordinates.length; i++) {
                        float proximity = Math.abs(obstacleCoordinates[i] - heroCoordinates[i]);

                        if (proximity <= proximtyValue) {
                            ListWithDirectionCollision.add(true);
                        } else
                            ListWithDirectionCollision.add(false);
                    }
                    mapObstaclesListHashMap.put(obstacle, ListWithDirectionCollision);
                    isCollision = true;
                }
            }
        }
        return isCollision;
    }

    enum MAP_QUARTERS {
        BOTTOM_LEFT, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT
    }
}
