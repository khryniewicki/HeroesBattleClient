package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;

public class WebsocketInitializer implements Runnable {
    private SuperHero superHero;
    private Application.MyStompSessionHandler handler;
    private final static WebsocketInitializer WEBSOCKET_INSTANCE = new WebsocketInitializer();

    private WebsocketInitializer() {
    }

    public static WebsocketInitializer getWebsocketInstance() {
        return WEBSOCKET_INSTANCE;
    }

    @Override
    public void run() {
        initialize();
    }

    public void setSuperHero() {
        HeroesInstances instance = HeroesInstances.getInstance();
        this.superHero = instance.getHero();
    }

    private void initialize() {
        handler = new Application.MyStompSessionHandler();
        setSuperHero();
        handler.register(superHero);
    }

    public void disconnect() {
        handler.unregister();
    }

    public void getSecondPlayerMockType() {
        handler.getMapWithHeroesFromServer();
    }

    public String getSessionId() {
        return handler.getSessionID();
    }
}
