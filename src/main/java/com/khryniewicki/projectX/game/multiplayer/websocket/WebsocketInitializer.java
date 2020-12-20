package com.khryniewicki.projectX.game.multiplayer.websocket;


import java.util.Objects;
import java.util.Optional;

public class WebsocketInitializer  {

    private WebsocketApplication.MyStompSessionHandler handler;
    private final static WebsocketInitializer WEBSOCKET_INSTANCE = new WebsocketInitializer();

    private WebsocketInitializer() {

    }

    public void initializeWebsocket() {
        WebsocketApplication websocketApplication = new WebsocketApplication();
        websocketApplication.startWebsocket();
    }

    public static WebsocketInitializer getWebsocketInstance() {
        return WEBSOCKET_INSTANCE;
    }

    public void registerHero() {
        handler = new WebsocketApplication.MyStompSessionHandler();
        handler.register();
    }

    public void disconnect() {
        handler.leave();
    }


    public String getSessionId() {
        Optional<String> sessionId = Optional.empty();
        if (Objects.nonNull(handler)) {
            sessionId = handler.getSessionId();
        }
        return sessionId.orElse("");
    }

}
