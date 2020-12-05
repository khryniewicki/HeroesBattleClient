package com.khryniewicki.projectX.game.multiplayer.websocket;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesRegistry;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Message;
import com.khryniewicki.projectX.game.user_interface.menu.menus.WaitingRoomMenu;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestClientException;
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
    private final PropertyChangeSupport support;
    private final WebsocketInitializer websocketInstance;
    private final Timer timer;
    private WaitingRoomMenu waitingRoomMenu;
    private String sessionId;
    private String path = "https://heroes.khryniewicki.com.pl";
    public volatile static ServerState state = ServerState.START;
    private boolean subscribed;

    private WebsocketScheduler() {
        websocketInstance = WebsocketInitializer.getWebsocketInstance();
        support = new PropertyChangeSupport(this);
        timer = new Timer();
    }

    public void observerPlayers() {
        requestScheduler();
    }

    public HashMap<String, Message> playersInGame() {
        ParameterizedTypeReference<HashMap<String, Message>> responseType = new ParameterizedTypeReference<>() {
        };
        return new RestTemplate().exchange(request("/map"), responseType).getBody();
    }

    public Long startCounter() {
        ParameterizedTypeReference<Long> responseType = new ParameterizedTypeReference<>() {
        };
        return new RestTemplate().exchange(request("/time-left-to-log-in"), responseType).getBody();
    }

    protected RequestEntity<Void> request(String s) {
        return RequestEntity.get(URI.create(path + s))
                .accept(MediaType.APPLICATION_JSON).build();
    }

    private void requestScheduler() {

        timer.schedule(new TimerTask() {

            public void run() {
                HashMap<String, Message> map;
                Long timeLeft;
                try {
                    map = playersInGame();
                    timeLeft = startCounter();
                    setTime(timeLeft);
                } catch (RestClientException exception) {
                    map = null;
                }
                if (Objects.nonNull(map)) {
                    initSessionId();
                    if (map.size() == 0) {
                        setState(ServerState.NO_PLAYERS);
                    } else if (map.size() == 1) {
                        setState(ServerState.ONE_PLAYER);
                        subscribeTimer();
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

            private void subscribeTimer() {
                if (Objects.nonNull(sessionId) && !subscribed) {
                    waitingRoomMenu = WaitingRoomMenu.getWaitingRoomMenu();
                    waitingRoomMenu.subscribePlayersInGame();
                    setSubscribed(true);
                }

            }

            private void initSessionId() {
                if (!websocketInstance.getSessionId().isEmpty() && Objects.isNull(sessionId)) {
                    sessionId = websocketInstance.getSessionId();
                }
            }
        }, 0, 500);

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

    public void setTime(Long time) {
        if (Objects.nonNull(time)) {
            support.firePropertyChange("timeLeftToLogOut", null, time);
        }
    }

    public void setState(ServerState new_state) {
        support.firePropertyChange("playersOnline", state, new_state);
        state = new_state;
    }

    private static class HELPER {
        private final static WebsocketScheduler INSTANCE = new WebsocketScheduler();
    }

}
