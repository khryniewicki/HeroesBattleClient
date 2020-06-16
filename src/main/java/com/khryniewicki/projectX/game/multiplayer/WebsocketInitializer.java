package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.config.Application;

public class WebsocketInitializer implements Runnable {

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



    public void initialize() {
        handler = new Application.MyStompSessionHandler();
        handler.register();
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
