package com.khryniewicki.projectX.game.multiplayer.websocket;


import com.khryniewicki.projectX.services.SendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class WebsocketController {

    private WebsocketApplication.MyStompSessionHandler handler;
    private SendingService sendingService;

    private WebsocketController() {
    }

    public void initialize_websocket() {
        StompSession session = WebsocketApplication.getSession();
        if (Objects.nonNull(session) && session.isConnected()) {
            session.disconnect();
        }
        new WebsocketApplication().startWebsocket();
    }

    public void join_room() {
        handler = new WebsocketApplication.MyStompSessionHandler();
        handler.join_room();
    }

    public void leave_room() {
        handler.leave_room();
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
            leave_room();
        }
        stop_sending_service();
    }

    protected void stop_sending_service() {
        sendingService.stop();
    }

    private final static WebsocketController WEBSOCKET_INSTANCE = new WebsocketController();

    public static WebsocketController getWebsocketInstance() {
        return WEBSOCKET_INSTANCE;
    }

}
