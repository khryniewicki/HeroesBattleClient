package com.khryniewicki.projectX.game.menu;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.menu.heroStorage.SuperHeroInstance;

public class WebsocketInitializer implements Runnable {
    private SuperHero superHero;
    private Application.MyStompSessionHandler handler;

    @Override
    public void run() {
        initialize();
    }

    public void setSuperHero() {
        SuperHeroInstance instance = SuperHeroInstance.getInstance();
        this.superHero = instance.getHero();
    }

    private void initialize()
    {
        handler = new Application.MyStompSessionHandler();
        setSuperHero();
        handler.register(superHero);
    }

    public void disconnect() {
        handler.unregister();
    }

    public void getMapWithHeroes() {
        handler.getMapWithHeroesFromServer();
    }

    public String getSessionId() {
       return handler.getSessionID();
    }
}
