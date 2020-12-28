package com.khryniewicki.projectX.game.multiplayer.websocket;


import com.khryniewicki.projectX.services.sending_service.SendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class WebsocketController {

    private WebsocketApplication.MyStompSessionHandler handler;
    private SendingService sendingService;
    private WebsocketApplication websocketApplication;

    private WebsocketController() {
    }

    public void initialize_websocket() {
        disconnect();
        websocketApplication = new WebsocketApplication();
        websocketApplication.startWebsocket();
        handler = new WebsocketApplication.MyStompSessionHandler();
    }

    public void disconnect() {
        StompSession session = WebsocketApplication.getSession();
        if (Objects.nonNull(session) && session.isConnected()) {
            websocketApplication.disconnect();
        }
    }


    public String get_session_id() {
        Optional<String> sessionId = Optional.empty();
        if (Objects.nonNull(handler)) {
            sessionId = handler.getSessionId();
        }
        return sessionId.orElse("");
    }

    public void start_sending_service() {
        sendingService = new SendingService();
        new Thread(sendingService, "SendingService").start();
    }

    public void stop_websocket() {
        if (!get_session_id().isEmpty()) {
            sendingService.leave_room();
        }
    }

    public void stop_sending_service() {
        sendingService.stop();
    }

    private final static WebsocketController WEBSOCKET_INSTANCE = new WebsocketController();

    public static WebsocketController getWebsocketInstance() {
        return WEBSOCKET_INSTANCE;
    }

}
