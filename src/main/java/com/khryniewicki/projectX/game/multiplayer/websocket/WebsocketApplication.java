package com.khryniewicki.projectX.game.multiplayer.websocket;


import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.MessageHandler;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;
import com.khryniewicki.projectX.services.dto.HeroDto;
import com.khryniewicki.projectX.services.dto.MessageDto;
import com.khryniewicki.projectX.services.dto.SpellDto;
import com.khryniewicki.projectX.services.receive_services.ReceiveServiceSingleton;
import com.khryniewicki.projectX.services.sending_service.StackEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static com.khryniewicki.projectX.utils.GameUtil.WEBSOCKET_PATH;


@Getter
@Setter
@Slf4j
public class WebsocketApplication implements Runnable {
    private static boolean client_running;
    private static boolean server_running;
    public static StompSession session;
    private static StompSession copy_session;
    public static StompSessionHandler sessionHandler;
    private WebSocketStompClient stompClient;

    public synchronized static StompSession getSession() {
        return copy_session;
    }

    public void disconnect() {
        session.disconnect();
    }

    @Slf4j
    @Getter
    @Setter
    public static class MyStompSessionHandler
            extends StompSessionHandlerAdapter {
        private final ReceiveServiceSingleton receiveService;
        private final StackEvent stackEvent;
        private final Channels channels;
        private static String sessionId;

        public Optional<String> getSessionId() {
            return Optional.ofNullable(sessionId);
        }

        public MyStompSessionHandler() {
            receiveService = ReceiveServiceSingleton.getInstance();
            channels = Channels.getINSTANCE();
            stackEvent = StackEvent.getInstance();
        }


        private void showHeaders(StompHeaders headers) {
            for (Map.Entry<String, List<String>> e : headers.entrySet()) {
                System.err.print("  " + e.getKey() + ": ");
                boolean first = true;
                for (String v : e.getValue()) {
                    if (!first) System.err.print(", ");
                    System.err.print(v);
                    first = false;
                }
                System.err.println();
            }
        }

        public void join_room() {
            HeroesInstances heroesInstances = HeroesInstances.getInstance();
            SuperHero superHero = heroesInstances.getHero();
            stackEvent.addDto(new MessageDto.Builder()
                    .status(ConnectionState.CONNECTED)
                    .sessionID(session.getSessionId())
                    .heroType(superHero.getName())
                    .playerName(heroesInstances.getHeroName())
                    .build());
        }

        public void leave_room() {
            stackEvent.addDto(new MessageDto.Builder()
                    .status(ConnectionState.DISCONNECTED)
                    .sessionID(session.getSessionId())
                    .build());
        }


        public void subscribeHero(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return HeroDto.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    HeroDto payload1 = (HeroDto) payload;
                    receiveService.set_hero_mock(payload1);
                }
            });
        }

        public synchronized void subscribeSpell(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return SpellDto.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    SpellDto payload1 = (SpellDto) payload;
                    receiveService.set_spell_mock(payload1);
                }
            });
        }

        public void subscribeGameInitials(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return MessageDto.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {

                    MessageHandler messageHandler = new MessageHandler();
                    messageHandler.setChannelsAndStartingPositions((MessageDto) payload);

                    subscribeHero("/topic/hero/" + channels.getTopic(), session);
                    subscribeSpell("/topic/spell/" + channels.getTopic(), session);
                }
            });
        }

        @Override
        public void handleTransportError(StompSession stompSession, Throwable throwable) {
            if (throwable instanceof ConnectionLostException) {
                System.err.println("Connection lost");
            }
        }

        @Override
        public void handleException(StompSession session, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        }

        @Override
        public void afterConnected(StompSession session,
                                   StompHeaders connectedHeaders) {
            String sessionID = session.getSessionId();
            System.err.println("Connected! Headers:" + "\n" + sessionID);
            showHeaders(connectedHeaders);
            subscribeGameInitials("/topic/room", session);
        }


    }

    @Override
    public void run() {
        startWebsocket();
    }

    public void startWebsocket() {

        WebSocketClient simpleWebSocketClient =
                new StandardWebSocketClient();

        List<Transport> transports = new ArrayList<>(50);
        transports.add(new WebSocketTransport(simpleWebSocketClient));

        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient =
                new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());


        sessionHandler = new MyStompSessionHandler();
        session = null;

        try {
            session = stompClient.connect(WEBSOCKET_PATH, sessionHandler)
                    .get();
            copy_session = session;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        setSessionId();
    }

    private static void setSessionId() {
        MyStompSessionHandler.sessionId = session.getSessionId();
    }


}

