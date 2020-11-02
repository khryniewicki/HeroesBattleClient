package com.khryniewicki.projectX.game.multiplayer.websocket;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesRegistry;
import com.khryniewicki.projectX.game.user_interface.symbols.observers.Listener;
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
public class WebsocketScheduler implements Listener {
    private final Channels channels;
    private String sessionId;
    private String path = "https://heroes.khryniewicki.com.pl";
    public static String numberOfPlayers = "";
    private final PropertyChangeSupport support;

    private volatile DispThreadState state = DispThreadState.PENDING;

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
                    if (map.size() < 2) {
                        setNews("Players online: " + map.size());
                    }
                    if (map.size() == 2) {
                        if (Objects.nonNull(sessionId) && map.containsKey(sessionId)) {
                            HeroesRegistry instance = HeroesRegistry.getINSTANCE();
                            instance.setHeroesRegistryBook(map);
                            setNews("Over");
                            timer.cancel();
                        } else {
                            setNews("Server is full");
                        }
                    }
                } else {
                    setNews("Server offline");
                }
            }
        }, 0, 1000);

    }

    public static WebsocketScheduler getInstance() {
        return WebsocketScheduler.HELPER.INSTANCE;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);

    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void setNews(String news) {
        support.firePropertyChange("playersOnline", numberOfPlayers, news);
        numberOfPlayers = news;
    }

    private static class HELPER {
        private final static WebsocketScheduler INSTANCE = new WebsocketScheduler();
    }
}
