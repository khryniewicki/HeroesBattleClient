package com.khryniewicki.projectX.config;

import com.khryniewicki.projectX.config.messageHandler.Channels;
import com.khryniewicki.projectX.config.messageHandler.ConnectionStatus;
import com.khryniewicki.projectX.config.messageHandler.Message;
import com.khryniewicki.projectX.config.messageHandler.MessageHandler;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.SpellDTO;
import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.MapWithHeroes;
import com.khryniewicki.projectX.services.HeroReceiveService;
import com.khryniewicki.projectX.services.HeroSendingService;
import com.khryniewicki.projectX.services.SpellReceiveService;
import com.khryniewicki.projectX.services.SpellSendingService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;


@Data
public class Application {
    private static StompSession session;
    private static boolean client_running;
    private static boolean server_running;
    public static StompSessionHandler sessionHandler;
    public static String path = "https://heroes.khryniewicki.com.pl";





    @Data
    @Slf4j
    public static  class MyStompSessionHandler
            extends StompSessionHandlerAdapter {
        private static String userId = "spring-" +
                ThreadLocalRandom.current().nextInt(1, 99);
        private HeroReceiveService heroReceiveService;
        private SpellReceiveService spellReceiveService;

        private HeroSendingService heroSendingService =new HeroSendingService();
        private SpellSendingService spellSendingService =new SpellSendingService();
        private final Channels channels;
        private ResponseEntity<HashMap<String, Message>> exchange;

        public String getSessionID() {
            return session.getSessionId();
        }

        private String sessionID;

        public MyStompSessionHandler() {
            heroReceiveService = HeroReceiveService.getInstance();
            spellReceiveService = new SpellReceiveService();
            channels = Channels.getINSTANCE();
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
            log.info("PRE-HeroSENDDTO");
            session.send("/app/hero/" + channels.getApp(), heroSendingService.getHeroPositions());
            log.info("AFTER-HeroSENDDTO");

        }

        public void sendSpellToStompSocket(SpellDTO spellDTO) {
            session.send("/app/spell/" + channels.getApp(), spellDTO);
        }


        public void register() {
            HeroesInstances heroesInstances = HeroesInstances.getInstance();
            SuperHero superHero = heroesInstances.getHero();
            session.send("/app/room", new Message(superHero.getName(), session.getSessionId(), ConnectionStatus.CONNECTED));
        }

        public void unregister() {
            session.send("/app/room", new Message(session.getSessionId(), ConnectionStatus.DISCONNECTED));
        }

        public void getMapWithHeroesFromServer() {
            RestTemplate restTemplate = new RestTemplate();
            ParameterizedTypeReference<HashMap<String, Message>> responseType =
                    new ParameterizedTypeReference<>() {
                    };
            RequestEntity<Void> request = RequestEntity.get(URI.create(path + "/map"))
                    .accept(MediaType.APPLICATION_JSON).build();

            requestSchudeler(restTemplate, responseType, request);
        }

        private void requestSchudeler(RestTemplate restTemplate, ParameterizedTypeReference<HashMap<String, Message>> responseType, RequestEntity<Void> request) {
            Timer timer = new Timer();
            CountDownLatch latch = new CountDownLatch(1);

            timer.schedule(new TimerTask() {

                public void run() {
                    exchange = restTemplate.exchange(request, responseType);
                    if (Objects.requireNonNull(exchange.getBody()).size() == 2) {
                        timer.cancel();
                        MapWithHeroes instance = MapWithHeroes.getINSTANCE();
                        instance.setMapWithHeroes(exchange.getBody());
                        latch.countDown();
                    }
                }
            }, 0, 3 * 1000);

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                    log.info("PRE-subscribeHero");

                    heroReceiveService.receivedMockPosition(((HeroDTO) payload));
                    log.info("AFTER-subscribeHero");

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

    public void startWebsocket() {
        WebSocketClient simpleWebSocketClient =
                new StandardWebSocketClient();

        List<Transport> transports = new ArrayList<>(1);
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

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

