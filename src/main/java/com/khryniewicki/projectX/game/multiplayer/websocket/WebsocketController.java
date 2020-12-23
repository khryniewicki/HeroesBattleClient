package com.khryniewicki.projectX.game.multiplayer.websocket;


import com.khryniewicki.projectX.services.SendingService;

import java.util.Objects;
import java.util.Optional;

public class WebsocketController {

    private WebsocketApplication.MyStompSessionHandler handler;
    private final static WebsocketController WEBSOCKET_INSTANCE = new WebsocketController();
    private final SendingService sendingService;



    public void initializeWebsocket() {
        WebsocketApplication websocketApplication = new WebsocketApplication();
        websocketApplication.startWebsocket();
    }

    public static WebsocketController getWebsocketInstance() {
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

    public void start_sending_service() {
        Thread sender = new Thread(sendingService, "SendingService");
        sender.start();
    }

    public void stop_sending_service() {
        sendingService.stop();
    }

    private WebsocketController() {
        sendingService = new SendingService();
    }
}
