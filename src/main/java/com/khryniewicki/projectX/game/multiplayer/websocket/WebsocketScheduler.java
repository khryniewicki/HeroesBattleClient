package com.khryniewicki.projectX.game.multiplayer.websocket;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesRegistry;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Data
@Slf4j
public class WebsocketScheduler {
    private final Channels channels;
    private String sessionId;
    private String path = "https://heroes.khryniewicki.com.pl";
    public volatile static ServerState state = ServerState.START;
    private final PropertyChangeSupport support;

    private WebsocketScheduler() {
        channels = Channels.getINSTANCE();
        support = new PropertyChangeSupport(this);
    }

    public void observerPlayers() {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<HashMap<String, Message>> responseType =
                new ParameterizedTypeReference<>() {
                };
        RequestEntity<Void> request = RequestEntity.get(URI.create(path + "/map"))
                .accept(MediaType.APPLICATION_JSON).build();

        requestScheduler(restTemplate, responseType, request);
    }

    private void requestScheduler(RestTemplate restTemplate, ParameterizedTypeReference<HashMap<String, Message>> responseType, RequestEntity<Void> request) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            public void run() {
                ResponseEntity<HashMap<String, Message>> exchange = restTemplate.exchange(request, responseType);
                HashMap<String, Message> map = exchange.getBody();
                if (Objects.nonNull(map)) {
                    if (map.size() == 0) {
                        setState(ServerState.NO_PLAYERS);
                    } else if (map.size() == 1) {
                        setState(ServerState.ONE_PLAYER);
                    } else if (map.size() == 2) {
                        if (Objects.nonNull(sessionId) && map.containsKey(sessionId)) {
                            HeroesRegistry instance = HeroesRegistry.getINSTANCE();
                            instance.setHeroesRegistryBook(map);
                            setState(ServerState.JOIN_GAME);
                            timer.cancel();
                        } else {
                            setState(ServerState.TWO_PLAYERS);
                        }
                    }
                } else {
                    setState(ServerState.SERVER_OFFLINE);
                }
            }
        }, 0, 1000);

    }

    public static WebsocketScheduler getInstance() {
        return WebsocketScheduler.HELPER.INSTANCE;
    }


    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }


    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void setState(ServerState new_state) {
        support.firePropertyChange("playersOnline", state, new_state);
        state = new_state;
    }

    private static class HELPER {
        private final static WebsocketScheduler INSTANCE = new WebsocketScheduler();
    }
}
