package com.khryniewicki.projectX.game.ui.menu.animation;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.ui.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;

import static com.khryniewicki.projectX.game.ui.menu.animation.AnimationSupport.LEFT_BOUNDARY;
import static com.khryniewicki.projectX.game.ui.menu.animation.AnimationSupport.RIGHT_BOUNDARY;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Getter
@Setter
public class AnimationHero extends AnimationObject {
    protected float speed = 0.2f;

    public AnimationHero(MenuSymbol symbol) {
        super(symbol);
    }

    protected void idle(SuperHero superHero) {
        action(superHero.getHeroIdle(), superHero.getSIZE());
    }

    protected void attack(SuperHero superHero) {
        action(superHero.getHeroAttack(), superHero.getSIZE());
    }

    protected void walk(SuperHero superHero) {
        move();
        action(superHero.getHeroRight(), superHero.getSIZE());
    }

    protected void action(Texture texture, float SIZE) {
        glfwPollEvents();
        setTexture(texture);
        setSIZE(SIZE);
        updateMesh();
    }

    protected void move() {
        Float x = getPositionX();
        if (x > RIGHT_BOUNDARY) {
            speed = -0.2f;
            turnLeft(true);
        } else if (x < LEFT_BOUNDARY) {
            speed = 0.2f;
            turnLeft(false);
        }
        updateHeroPositionX(speed);
    }


    @Override
    protected Float getPositionX() {
        return symbol.getPosition().x;
    }

    @Override
    protected Float getPositionY() {
        return symbol.getPositionY() + 0.1f;
    }

    public void updateHeroPositionX(float speed) {
        symbol.getPosition().x += speed;
    }


}
