package com.khryniewicki.projectX.game.websocket;


import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.websocket.messages.Channels;
import com.khryniewicki.projectX.game.websocket.messages.ConnectionStatus;
import com.khryniewicki.projectX.game.websocket.messages.Message;
import com.khryniewicki.projectX.game.websocket.messages.MessageHandler;
import com.khryniewicki.projectX.services.DTO.HeroDTO;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import com.khryniewicki.projectX.services.HeroReceiveService;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.services.SpellReceiveService;
import com.khryniewicki.projectX.services.SpellSendingService;
import lombok.Data;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;


@Data
@Slf4j
public class WebsocketApplication implements Runnable{
    private static StompSession session;
    private static StompSession copy_session;
    private static boolean client_running;
    private static boolean server_running;
    public static StompSessionHandler sessionHandler;
    public static String path = "https://heroes.khryniewicki.com.pl";

    public synchronized static StompSession getSession() {
        return copy_session;
    }


    @Data
    @Slf4j
    public static  class MyStompSessionHandler
            extends StompSessionHandlerAdapter {
        private static String userId = "spring-" +
                ThreadLocalRandom.current().nextInt(1, 99);
        private final HeroReceiveService heroReceiveService;
        private final SpellReceiveService spellReceiveService;
        private final SendingService sendingService;
        private final SpellSendingService spellSendingService;
        private final Channels channels;
        private String sessionID;

        public MyStompSessionHandler() {
            heroReceiveService = HeroReceiveService.getInstance();
            spellReceiveService = new SpellReceiveService();
            spellSendingService =new SpellSendingService();
            sendingService =new SendingService();
            channels=Channels.getINSTANCE();
        }
        public String getSessionID() {
            return session.getSessionId();
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

        public void register() {
            HeroesInstances heroesInstances = HeroesInstances.getInstance();
            SuperHero superHero = heroesInstances.getHero();
            session.send("/app/room", new Message(superHero.getName(), session.getSessionId(), ConnectionStatus.CONNECTED));
        }

        public void unregister() {
            session.send("/app/room", new Message(session.getSessionId(), ConnectionStatus.DISCONNECTED));
        }

        public void getHeroesRegistry() {
            WebsocketScheduler websocketScheduler = new WebsocketScheduler();
            websocketScheduler.getHeroesRegistryFromServer(path);
        }



        public  void subscribeHero(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return HeroDTO.class;
                }

                @Override
                public void handleFrame(StompHeaders headers,
                                        Object payload) {
                    heroReceiveService.receivedMockDTO(((HeroDTO) payload));
                }
            });
        }

        public synchronized void subscribeSpell(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return SpellDTO.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    SpellReceiveService.receiveSpellMock((SpellDTO) payload);
                }
            });
        }

        public void subscribeGameInitials(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return Message.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    Message message = (Message) payload;
                    MessageHandler instance = MessageHandler.getINSTANCE();

                    instance.setMessage(message);
                    instance.latchCountDownMethod();

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
            sessionID = session.getSessionId();
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

        String url = path + "/websocket-example";


        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        session = null;

        try {
            session = stompClient.connect(url, sessionHandler)
                    .get();
            copy_session=session;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

