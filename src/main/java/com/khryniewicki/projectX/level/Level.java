package com.khryniewicki.projectX.level;

import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;

import java.util.Arrays;

public class Level {

    private VertexArray background;
    private Texture bgTexture;

    private int xScroll = 0;
    private int map = 0;
    private Vector position = new Vector();

    private Hero hero;
    private Obstacle obstacle;
    private float delta_x=0.1f;
    private float delta_y=0.1f;

    public static boolean collision_left,collision_right,collision_up,collision_down=false;
    public static boolean[] collisions=new boolean[]{collision_right,collision_left,collision_up, collision_down};
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

        hero = new Hero();
        obstacle = new Obstacle();
    }

    public void update() {

        hero.update();
        obstacle.update();
    }

    private boolean Collision() {
        float bx = hero.getX();
        float by = hero.getY();
        float px = (Obstacle.obstacle_positionX0-Hero.hero_positionX0 )+delta_x;
        float py = (Obstacle.obstacle_positionY0-Hero.hero_positionY0 )+ delta_y;

        float bx0 = bx - hero.SIZE / 2.0f;
        float bx1 = bx + hero.SIZE / 2.0f;
        float by0 = by - hero.SIZE/ 2.0f;
        float by1 = by + hero.SIZE / 2.0f-0.7f;

        float px0 = px;
        float px1 = px + obstacle.getWidth();
        float py0 = py;
        float py1 = py + obstacle.getHeight();

//        System.out.println(String.format("Object:[%f,%f,%f,%f] Hero:[%f,%f,%f,%f]",px0,px1,py0,py1,bx0,bx1,by0,by1));
        float[] obstacleCoordinates = {px0, px1, py0, py1};
        float[] heroCoordinates =     {bx1,bx0,by1,by0};

        if (bx1 > px0 && bx0 < px1) {
            if (by1 > py0 && by0 < py1) {
                for (int i = 0; i < obstacleCoordinates.length; i++) {
                    if (Math.abs(obstacleCoordinates[i]-heroCoordinates[i])<=0.21f) {
                        collisions[i] = true;
                    }
                    else
                        collisions[i] = false;
                }
                System.out.println(String.format("Object:[%f,%f,%f,%f] Hero:[%f,%f,%f,%f]",px0,px1,py0,py1,bx0,bx1,by0,by1));

                System.out.println(Arrays.toString(collisions));

                return true;
            }
        }

        System.out.println(Arrays.toString(collisions));
        collisions=new boolean[]{collision_right,collision_left,collision_up, collision_down};
        return false;
    }

    public void render() {
        bgTexture.bind();
        Shader.BG.enable();
        background.bind();
        for (int i = 0; i < map + 3; i++) {
            Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector(i * 10 + xScroll * 0.03f, 0.0f, 0.0f)));
            background.draw();
        }
        Collision();
        background.render();
        Shader.BG.disable();
        bgTexture.unbind();

        hero.render();
        obstacle.render();
    }
}


