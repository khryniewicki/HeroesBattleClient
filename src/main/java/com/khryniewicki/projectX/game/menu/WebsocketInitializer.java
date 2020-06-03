package com.khryniewicki.projectX.game.menu;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;

public class WebsocketInitializer implements Runnable {
    private SuperHero superHero;
    @Override
    public void run() {
        initialize();
    }

    public void setSuperHero(SuperHero superHero) {
        this.superHero = superHero;
    }

    private void initialize() {

        Application.MyStompSessionHandler handler = new Application.MyStompSessionHandler();
        handler.getHeroId(superHero);

    }
}
