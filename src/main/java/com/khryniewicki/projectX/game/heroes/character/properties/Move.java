package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.collision.Collision;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.StackEvent;

import static org.lwjgl.glfw.GLFW.*;

public class Move {

    private Vector position;
    private final SuperHero hero;
    private final StackEvent stackEvent;
    private final LifeBar lifeBar;
    private final ManaBar manaBar;
    private final MouseSetting mouseSetting;

    private Move() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        hero = heroesInstances.getHero();
        stackEvent = StackEvent.getInstance();
        lifeBar = hero.getLifeBar();
        manaBar = hero.getManaBar();
        mouseSetting=MouseSetting.getInstance();
        mouseSetting.setMouseCallBack();
    }

    public void move() {

        glfwSetKeyCallback(Game.window, (window, key, scancode, action, mods) -> {

            float tmpX = hero.getX();
            float tmpY = hero.getY();
            position = hero.getPosition();

            float velocity = 0.2f;
            if (key == GLFW_KEY_UP && action != GLFW_RELEASE && !Collision.collisions[2]) {
                hero.setPositionY(position.y + velocity);
                hero.setTexture(hero.getHeroUp());
            } else if (key == GLFW_KEY_DOWN && action != GLFW_RELEASE && !Collision.collisions[3]) {
                hero.setPositionY(position.y - velocity);
                hero.setTexture(hero.getHeroDown());
            } else if (key == GLFW_KEY_LEFT && action != GLFW_RELEASE && !Collision.collisions[1]) {
                hero.setPositionX(position.x - velocity);
                hero.setMovingLeft(true);
                hero.setTexture(hero.getHeroLeft());
            } else if (key == GLFW_KEY_RIGHT && action != GLFW_RELEASE && !Collision.collisions[0]) {
                hero.setPositionX(position.x + velocity);
                hero.setMovingLeft(false);
                hero.setTexture(hero.getHeroRight());
            } else {
                hero.setTexture(hero.getHeroIdle());
            }
            sendMsg(tmpX,tmpY);
        });

    }

    private void sendMsg(float tmpX, float tmpY) {

        if (tmpX != hero.getPosition().x || tmpY != hero.getPosition().y) {
            stackEvent.setHasAction(true);
            hero.setMesh(hero.createHero());
            lifeBar.updateLifeBar();
            manaBar.updateManaBar();
        } else
            stackEvent.setHasAction(false);
    }


    public static Move getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static Move INSTANCE = new Move();
    }
}
