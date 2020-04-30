package com.khryniewicki.projectX.game.Collision;

import com.khryniewicki.projectX.game.Character.Hero;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.game.Map.MapObstacles;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;

import java.util.Arrays;
import java.util.List;


@Data
public class Collision {
    private Level level;
    private Hero hero;
    private float hero_standard_offset = 0.2f;
    private float hero_top_offset = 0.5f;
    private float delta_x = 0.1f;
    private float delta_y = 0.1f;

    private float bx, by, bx0, bx1, by0, by1;
    private float px0, px1, py0, py1;

    public static boolean collision_left, collision_right, collision_up, collision_down = false;
    public static Boolean[] collisions = new Boolean[]{collision_right, collision_left, collision_up, collision_down};

    private List<MapObstacles> obstacleList_BL = ObstacleStorage.getObstacleList_BL();
    private List<MapObstacles> obstacleList_BR = ObstacleStorage.getObstacleList_BR();
    private List<MapObstacles> obstacleList_TL = ObstacleStorage.getObstacleList_TL();
    private List<MapObstacles> obstacleList_TR = ObstacleStorage.getObstacleList_TR();
    private List<MapObstacles> terrainList = ObstacleStorage.getTerrainList();


    public boolean collisionTest(Hero hero) {
        setHero(hero);
        bx = hero.getX();
        by = hero.getY();
        MAP_QUARTERS map_quarter = checkInWhichQuerterIsHero();
        boolean isCollisionWithObstacle = ObstacleCollisionInHeroQuarter(map_quarter);
        ;
        boolean isCollisionWithTerrain = ObstacleCollision(terrainList);

        if (!isCollisionWithObstacle && !isCollisionWithTerrain) {
            Arrays.fill(collisions, false);
        }
        return true;
    }

    private boolean ObstacleCollisionInHeroQuarter(MAP_QUARTERS map_quarter) {
        switch (map_quarter) {
            case BOTTOM_LEFT:
                return ObstacleCollision(obstacleList_BL);
            case BOTTOM_RIGHT:
                return ObstacleCollision(obstacleList_BR);
            case TOP_LEFT:
                return ObstacleCollision(obstacleList_TL);
            case TOP_RIGHT:
                return ObstacleCollision(obstacleList_TR);
            default:
                throw new IllegalArgumentException("There is no map quarter");
        }

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

    public boolean ObstacleCollision(List<MapObstacles> obstacles) {

        bx0 = bx - hero.SIZE / 2.0f + hero_standard_offset;
        bx1 = bx + hero.SIZE / 2.0f - hero_standard_offset;
        by0 = by - hero.SIZE / 2.0f + hero_standard_offset;
        by1 = by + hero.SIZE / 2.0f - hero_top_offset;
        float proximtyValue;
        boolean isCollision = false;
        for (MapObstacles obstacle : obstacles) {
            float tangens = obstacle.getTangens();

            if (tangens == 0) {
                px0 = (obstacle.getObstacle_positionX0() - Hero.hero_positionX0) + delta_x;
                py0 = (obstacle.getObstacle_positionY0() - Hero.hero_positionY0) + delta_y;
                px1 = px0 + obstacle.getWidth();
                py1 = py0 + obstacle.getHeight();
                proximtyValue = 0.21f;
            } else {
                float px = (obstacle.getObstacle_positionX0() - Hero.hero_positionX0);
                float py = (obstacle.getObstacle_positionY0() - Hero.hero_positionY0);
                float pX = (obstacle.getObstacle_positionX1() - Hero.hero_positionX0);
                float pY = (obstacle.getObstacle_positionY1() - Hero.hero_positionY0);
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

            if (bx1 > px0 && bx0 < px1) {
                if (by1 > py0 && by0 < py1) {
                    for (int i = 0; i < obstacleCoordinates.length; i++) {
                        float proximity = Math.abs(obstacleCoordinates[i] - heroCoordinates[i]);


                        if (proximity <= proximtyValue) {
                            collisions[i] = true;
                        } else if (proximity > proximtyValue && collisions[i] == true)
                            continue;
                        else
                            collisions[i] = false;
                    }
//                   System.out.println(String.format("C! RIGHT[(bx1) %f > %f (px)] LEFT[(bx0) %f < %f (px1)] UP[(by1) %f > %f (py0)] DOWN[(by0) %f < %f (py1)] ", bx1,px0,bx0, px1,by1,py0,by0,py1 ));

//                    System.out.println(String.format("Object:[%f,%f,%f,%f] Hero:[%f,%f,%f,%f]", px0, px1, py0, py1, bx0, bx1, by0, by1));
//                    System.out.println(Arrays.toString(collisions));
                    isCollision = true;
                }
            }
//            System.out.println(String.format("RIGHT[(bx1) %f > %f (px)] LEFT[(bx0) %f < %f (px1)] UP[(by1) %f > %f (py0)] DOWN[(by0) %f < %f (py1)] ", bx1,px0,bx0, px1,by1,py0,by0,py1 ));

//            System.out.println(Arrays.toString(collisions));


        }
        if (isCollision) {
            List<Boolean> booleans = Arrays.asList(collisions);
            if (booleans.stream().allMatch(e -> e.equals(true)))
                return false;
            return booleans.stream().anyMatch(e -> e.equals(true));
        }
        return false;
    }

}
