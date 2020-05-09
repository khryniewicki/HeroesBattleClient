package com.khryniewicki.projectX.config;

import com.khryniewicki.projectX.game.attack.SpellDTO;
import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.services.HeroReceiveService;
import com.khryniewicki.projectX.services.HeroSendDTO;
import com.khryniewicki.projectX.services.SpellReceiveService;
import com.khryniewicki.projectX.services.SpellSendDTO;
import lombok.Data;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;


@Data
public class Application  {
    private static StompSession session;
    private static boolean client_running;
    private static boolean server_running;
    public static StompSessionHandler sessionHandler;



    @Data
    static public class MyStompSessionHandler
            extends StompSessionHandlerAdapter {
        private static String userId = "spring-" +
                ThreadLocalRandom.current().nextInt(1, 99);
        private HeroReceiveService heroReceiveService;
        private SpellReceiveService spellReceiveService;
        private HeroSendDTO heroSendDTO;
        private SpellSendDTO spellSendDTO;
        private Integer app = 1;
        private Integer topic = 2;

        public MyStompSessionHandler() {
            heroReceiveService = new HeroReceiveService();
            spellReceiveService=new SpellReceiveService();
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

        public void sendHeroToStompSocket() {
            heroSendDTO = new HeroSendDTO();
            session.send("/app/hero/" + app, heroSendDTO.getHeroPositions());
        }

        public void sendSpellToStompSocket(SpellDTO spellDTO) {
            spellSendDTO = new SpellSendDTO();
            session.send("/app/spell/" + app,spellDTO);
        }

        public void subscribeHero(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return HeroDTO.class;
                }

                @Override
                public void handleFrame(StompHeaders headers,
                                        Object payload) {
                    heroReceiveService.receivedMockPosition(((HeroDTO) payload));
                }
            });
        }

        public void subscribeSpell(String topic, StompSession session) {
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

        @Override
        public void handleTransportError(StompSession stompSession, Throwable throwable) {
            if (throwable instanceof ConnectionLostException) {
                System.err.println("Connection lost");
            }
        }

        @Override
        public void handleException(StompSession session, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
            System.err.println("Connected: " + "problems");
        }

        @Override
        public void afterConnected(StompSession session,
                                   StompHeaders connectedHeaders) {
            System.err.println("Connected! Headers:" + "\n" + userId);
            showHeaders(connectedHeaders);

            subscribeHero("/topic/hero/" + topic, session);
            subscribeSpell("/topic/spell/" + topic, session);

            sendHeroToStompSocket();
        }


    }

    public  void startWebsocket() {
        WebSocketClient simpleWebSocketClient =
                new StandardWebSocketClient();

        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(simpleWebSocketClient));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient =
                new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        String url = "https://heroes.khryniewicki.com.pl/websocket-example";


        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        session = null;

        try {
            session = stompClient.connect(url, sessionHandler)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

