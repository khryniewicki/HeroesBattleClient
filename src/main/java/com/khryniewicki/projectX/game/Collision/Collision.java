package com.khryniewicki.projectX.game.Collision;

import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.game.Map.Objects;
import com.khryniewicki.projectX.game.heroes.character.Hero;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Data
public class Collision {
    private Level level;
    private SuperHero hero;
    private static float SIZE;
    private static float hero_standard_offset = 0.2f;
    private static float hero_top_offset = 0.5f;
    private static float delta_x = 0.1f;
    private static float delta_y = 0.1f;

    private static float bx, by, bx0, bx1, by0, by1;
    private static float px0, px1, py0, py1;
    private static float[] heroCoordinates;

    public static boolean collision_left, collision_right, collision_up, collision_down = false;
    public static Boolean[] collisions = new Boolean[]{collision_right, collision_left, collision_up, collision_down};
    public static Boolean[] BoundaryCollisions = new Boolean[]{collision_right, collision_left, collision_up, collision_down};
    public static Boolean[] ObstacleCollisions = new Boolean[]{collision_right, collision_left, collision_up, collision_down};
    public static Boolean[] TerrainCollisions = new Boolean[]{collision_right, collision_left, collision_up, collision_down};
    boolean isCollision;
    HashMap<Objects, List<Boolean>> mapObstaclesListHashMap;

    private List<Objects> obstacleList_BL = ObstacleStorage.getObstacleList_BL();
    private List<Objects> obstacleList_BR = ObstacleStorage.getObstacleList_BR();
    private List<Objects> obstacleList_TL = ObstacleStorage.getObstacleList_TL();
    private List<Objects> obstacleList_TR = ObstacleStorage.getObstacleList_TR();
    private List<Objects> terrainList = ObstacleStorage.getTerrainList();


    public void collisionTest(SuperHero hero) {
        setHero(hero);
        bx = hero.getX();
        by = hero.getY();
        SIZE = hero.getSIZE();
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
        }
    }

    private boolean boundaryCollision() {
        Arrays.fill(BoundaryCollisions, false);
        if (bx >= 9.5f) {
            BoundaryCollisions[0] = true;
            return true;
        } else if (bx <= -9.5f) {
            BoundaryCollisions[1] = true;
            return true;
        } else if (by >= 5.0f) {
            BoundaryCollisions[2] = true;
            return true;
        } else if (by <= -5.2f) {
            BoundaryCollisions[3] = true;
            return true;
        } else
            return false;
    }

    private boolean obstacleCollision(MAP_QUARTERS map_quarter) {
        switch (map_quarter) {
            case BOTTOM_LEFT:
                return obstacleCollisionInQuarter(obstacleList_BL);
            case BOTTOM_RIGHT:
                return obstacleCollisionInQuarter(obstacleList_BR);
            case TOP_LEFT:
                return obstacleCollisionInQuarter(obstacleList_TL);
            case TOP_RIGHT:
                return obstacleCollisionInQuarter(obstacleList_TR);
            default:
                throw new IllegalArgumentException("There is no map quarter");
        }

    }


    public boolean obstacleCollisionInQuarter(List<Objects> mapObstacles) {
        return checkConditionForObstacleOrTerrain(mapObstacles, ObstacleCollisions);
    }

    public boolean terrainCollision(List<Objects> mapObstacles) {
        return checkConditionForObstacleOrTerrain(mapObstacles, TerrainCollisions);
    }

    private boolean checkConditionForObstacleOrTerrain(List<Objects> mapObstacles, Boolean[] obstacleOrTerrainCollisions) {
        Arrays.fill(obstacleOrTerrainCollisions, false);
        if (collision(mapObstacles)) {
            mapObstaclesListHashMap.values().forEach(e -> {
                for (int i = 0; i < e.size(); i++) {
                    Boolean isCollisionWithObstacle = e.get(i);
                    if (isCollisionWithObstacle)
                        obstacleOrTerrainCollisions[i] = true;
                }
            });
            return true;
        }
        return false;
    }

    public Boolean collision(List<Objects> obstacles) {

        heroObjectDimenions();
        isCollision = false;
        mapObstaclesListHashMap = new HashMap<>();
        float proximtyValue;

        for (Objects object : obstacles) {
            float tangens = object.getTangens();

            if (tangens == 0) {
                simpleRectangularObjectDimenions(object);
                proximtyValue = 0.21f;
            } else {
                inclinatedObjectDimensions(object, tangens);
                proximtyValue = 0.3f;
            }
            float[] obstacleCoordinates = {px0, px1, py0, py1};
            List<Boolean> ListWithDirectionCollision = Arrays.asList(collisions);
            mapObstaclesListHashMap.put(object, ListWithDirectionCollision);

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
                    mapObstaclesListHashMap.put(object, ListWithDirectionCollision);
                    isCollision = true;
                }
            }
        }
        return isCollision;
    }



    public static void heroObjectDimenions() {
        bx0 = bx - SIZE / 2.0f + hero_standard_offset;
        bx1 = bx + SIZE / 2.0f - hero_standard_offset;
        by0 = by - SIZE / 2.0f + hero_standard_offset;
        by1 = by + SIZE / 2.0f - hero_top_offset;
        heroCoordinates =new float[] {bx1, bx0, by1, by0};
    }

    public static void simpleRectangularObjectDimenions(Objects object) {
        px0 = (object.getObject_positionX0() - Hero.hero_positionX0) + delta_x;
        py0 = (object.getObject_positionY0() - Hero.hero_positionY0) + delta_y;
        px1 = px0 + object.getWidth();
        py1 = py0 + object.getHeight();
    }

    private void inclinatedObjectDimensions(Objects object, float tangens) {
        float px = (object.getObject_positionX0() - Hero.hero_positionX0);
        float py = (object.getObject_positionY0() - Hero.hero_positionY0);
        float pX = (object.getObject_positionX1() - Hero.hero_positionX0);
        float pY = (object.getObject_positionY1() - Hero.hero_positionY0);
        float X = 1.0f;
        if (by1 < py) X = 0;
        if (bx1 < px) X = 0;
        if (by0 > pY) X = 0;
        if (bx0 > pX) X = 0;

        px0 = px + X * (by0 - py) / tangens;
        py0 = py + X * (bx0 - px) * tangens;
        px1 = px + X * (by1 - py - 0.2f) / tangens + 0.2f;
        py1 = py + X * (bx1 - px - 0.2f) * tangens + 0.2f;
    }
}
