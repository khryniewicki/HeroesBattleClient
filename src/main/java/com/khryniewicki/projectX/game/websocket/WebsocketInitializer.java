package com.khryniewicki.projectX.game.websocket;

public class WebsocketInitializer implements Runnable {

    private WebsocketApplication.MyStompSessionHandler handler;
    private final static WebsocketInitializer WEBSOCKET_INSTANCE = new WebsocketInitializer();

    private WebsocketInitializer() {
    }

    public static WebsocketInitializer getWebsocketInstance() {
        return WEBSOCKET_INSTANCE;
    }

    @Override
    public void run() {
        register();
    }

    public void register() {
        handler = new WebsocketApplication.MyStompSessionHandler();
        handler.register();
    }

    public void disconnect() {
        handler.unregister();
    }

    public void getSecondPlayerMockType() {
        handler.getHeroesRegistry();
    }

    public String getSessionId() {
        return handler.getSessionID();
    }

}
