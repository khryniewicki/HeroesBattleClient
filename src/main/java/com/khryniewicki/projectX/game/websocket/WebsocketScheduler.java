package com.khryniewicki.projectX.game.websocket;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesRegistry;
import com.khryniewicki.projectX.game.websocket.messages.Channels;
import com.khryniewicki.projectX.game.websocket.messages.Message;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class WebsocketScheduler {
    private final Channels channels;

    public WebsocketScheduler() {
        channels = Channels.getINSTANCE();
    }

    public void getHeroesRegistryFromServer(String path) {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<HashMap<String, Message>> responseType =
                new ParameterizedTypeReference<>() {
                };
        RequestEntity<Void> request = RequestEntity.get(URI.create(path + "/controller/" + channels.getApp()))
                .accept(MediaType.APPLICATION_JSON).build();

        requestScheduler(restTemplate, responseType, request);
    }

    private void requestScheduler(RestTemplate restTemplate, ParameterizedTypeReference<HashMap<String, Message>> responseType, RequestEntity<Void> request) {
        Timer timer = new Timer();
        CountDownLatch latch = new CountDownLatch(1);

        timer.schedule(new TimerTask() {

            public void run() {
                ResponseEntity<HashMap<String, Message>> exchange = restTemplate.exchange(request, responseType);
                if (Objects.requireNonNull(exchange.getBody()).size() == 2) {
                    timer.cancel();
                    HeroesRegistry instance = HeroesRegistry.getINSTANCE();
                    instance.setHeroesRegistryBook(exchange.getBody());
                    latch.countDown();
                }
            }
        }, 0, 500);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
