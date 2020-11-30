package com.khryniewicki.projectX.game.multiplayer.websocket;


import com.khryniewicki.projectX.game.user_interface.menu.menus.WaitingRoomMenu;

import java.util.Objects;
import java.util.Optional;

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

    public void waitForSecondPlayer() {
        WaitingRoomMenu waitingRoomMenu = WaitingRoomMenu.getWaitingRoomMenu();
        waitingRoomMenu.execute();
    }

    public void register() {
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
