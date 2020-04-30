package com.khryniewicki.projectX.config;

import com.khryniewicki.projectX.game.Character.Hero;
import com.khryniewicki.projectX.game.Character.HeroDTO;
import com.khryniewicki.projectX.services.HeroReceiveService;
import com.khryniewicki.projectX.services.HeroService;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;


@Data
public class Application {
    private static StompSession session;

    static public class MyStompSessionHandler
            extends StompSessionHandlerAdapter {
        private static String userId = "spring-" +
                ThreadLocalRandom.current().nextInt(1, 99);

        public MyStompSessionHandler() {
        }

        private HeroService heroService;
        private HeroReceiveService heroReceiveService;

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

        public void sendHeroCoordinatesFromWebsocket() {
            heroService = new HeroService();
            session.send("/app/hero", heroService.getHeroPositions());
        }

        public void subscribeTopic(String topic, StompSession session) {
            session.subscribe(topic, new StompFrameHandler() {

                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return HeroDTO.class;
                }

                @Override
                public void handleFrame(StompHeaders headers,
                                        Object payload) {
                    heroReceiveService = new HeroReceiveService();
                    heroReceiveService.receivedMockPosition((HeroDTO) payload);
//                    System.err.println(payload.toString());
                }
            });
        }

        @Override
        public void afterConnected(StompSession session,
                                   StompHeaders connectedHeaders) {
            System.err.println("Connected! Headers:");
            System.err.println(userId);
            showHeaders(connectedHeaders);

            subscribeTopic("/topic/hero", session);

            sendHeroCoordinatesFromWebsocket();

        }


    }

    public static void startWebsocket() {

        WebSocketClient simpleWebSocketClient =
                new StandardWebSocketClient();
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(simpleWebSocketClient));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient =
                new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        String url = "http://localhost:8081/websocket-example";


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

