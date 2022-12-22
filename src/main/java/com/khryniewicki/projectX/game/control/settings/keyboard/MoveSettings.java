package com.khryniewicki.projectX.game.control.settings.keyboard;

import com.khryniewicki.projectX.game.ui.board.collision.Collision;
import com.khryniewicki.projectX.game.heroes.character.properties.HeroAttributes;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.sending.StackEvent;
import com.khryniewicki.projectX.utils.GameUtil;
import lombok.extern.slf4j.Slf4j;

import static org.lwjgl.glfw.GLFW.*;

@Slf4j
public class MoveSettings {

    private Vector position;
    private final SuperHero hero;
    private final StackEvent stackEvent;
    private final HeroAttributes heroAttributes;
    private final Collision collision;

    public MoveSettings() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        hero = heroesInstances.getHero();
        stackEvent = StackEvent.getInstance();
        heroAttributes = hero.getHeroAttributes();
        collision = new Collision();
    }

    public void move() {

        glfwSetKeyCallback(GameUtil.window, (window, key, scancode, action, mods) -> {
            float tmpX = hero.getX();
            float tmpY = hero.getY();
            position = hero.getPosition();
            collision.test();

            float velocity = 0.2f;
            if (key == GLFW_KEY_UP && action != GLFW_RELEASE && !Collision.collisions[2]) {
                hero.setPositionY(position.y + velocity);
                hero.setTexture(hero.getHeroUp());
            } else if (key == GLFW_KEY_DOWN && action != GLFW_RELEASE && !Collision.collisions[3]) {
                hero.setPositionY(position.y - velocity);
                hero.setTexture(hero.getHeroDown());
            } else if (key == GLFW_KEY_LEFT && action != GLFW_RELEASE && !Collision.collisions[1]) {
                hero.setPositionX(position.x - velocity);
                hero.setTurningLeft(true);
                hero.setTexture(hero.getHeroLeft());
            } else if (key == GLFW_KEY_RIGHT && action != GLFW_RELEASE && !Collision.collisions[0]) {
                hero.setPositionX(position.x + velocity);
                hero.setTurningLeft(false);
                hero.setTexture(hero.getHeroRight());
            } else if (key == GLFW_KEY_SPACE && action != GLFW_RELEASE) {
                hero.setPositionX(-3f);
                hero.setPositionY(-3f);
                hero.setTurningLeft(false);
                hero.setTexture(hero.getHeroRight());
            } else {
                hero.setTexture(hero.getHeroIdle());
            }
            sendMsg(tmpX, tmpY);
        });

    }

    private void sendMsg(float tmpX, float tmpY) {
        if (tmpX != hero.getPosition().x || tmpY != hero.getPosition().y) {
            hero.setMesh();
            heroAttributes.update();
            stackEvent.addHeroDto();
        }
    }

}
