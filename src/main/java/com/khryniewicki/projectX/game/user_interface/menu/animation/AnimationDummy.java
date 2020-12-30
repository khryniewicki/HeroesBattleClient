package com.khryniewicki.projectX.game.user_interface.menu.animation;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;

import static com.khryniewicki.projectX.game.user_interface.menu.animation.AnimationSupport.LEFT_BOUNDARY;
import static com.khryniewicki.projectX.game.user_interface.menu.animation.AnimationSupport.RIGHT_BOUNDARY;

public class AnimationDummy extends AnimationObject {

    public AnimationDummy(MenuSymbol symbol) {
        super(symbol);
    }

    @Override
    protected Float getPositionX() {
        return symbol.getPositionX();
    }

    @Override
    protected Float getPositionY() {
        return symbol.getPositionY();
    }

    protected void add_dummy(float animationHeroPositionX) {
        if (((LEFT_BOUNDARY + RIGHT_BOUNDARY) / 2) <= animationHeroPositionX) {
            setPosition(LEFT_BOUNDARY - 2f);
            turnLeft(true);
        } else {
            setPosition(RIGHT_BOUNDARY + 0.5f);
            turnLeft(false);
        }
    }
}
