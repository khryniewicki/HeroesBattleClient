package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.config.Application;

public class WebsocketInitializer implements Runnable {

    private Application.MyStompSessionHandler handler;
    private final static WebsocketInitializer WEBSOCKET_INSTANCE = new WebsocketInitializer();
    private Application application;
    private WebsocketInitializer() {
    }

    public static WebsocketInitializer getWebsocketInstance() {
        return WEBSOCKET_INSTANCE;
    }

    @Override
    public void run() {
        application = new Application();
        application.startWebsocket();


    }

    public void initialize() {
        handler = new Application.MyStompSessionHandler();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
