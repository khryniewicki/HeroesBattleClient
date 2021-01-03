package com.khryniewicki.projectX.game.multiplayer.websocket;

import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesRegistry;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ServerState;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.WaitingRoomMenu;
import com.khryniewicki.projectX.services.dto.MessageDto;
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

import static com.khryniewicki.projectX.game.user_interface.symbols.observers.Subjects.*;
import static com.khryniewicki.projectX.utils.GameUtil.*;

@Data
@Slf4j
public class WebsocketScheduler {
    private final PropertyChangeSupport support;
    private final WebsocketController websocketInstance;
    private final Timer timer;

    private WaitingRoomMenu waitingRoomMenu;
    private String sessionId;
    private Integer period = 200;


    public MultiplayerState multiplayerState = MultiplayerState.NOT_CONNECTED;
    private boolean hasStarted;

    private WebsocketScheduler() {
        websocketInstance = WebsocketController.getWebsocketInstance();
        support = new PropertyChangeSupport(this);

        timer = new Timer();
    }

    public void observePlayers() {
        hasStarted = true;
        requestScheduler();
    }

    public HashMap<String, MessageDto> playersInGame() {
        ParameterizedTypeReference<HashMap<String, MessageDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return new RestTemplate().exchange(request(MAP), responseType).getBody();
    }

    public Long startCounter() {
        ParameterizedTypeReference<Long> responseType = new ParameterizedTypeReference<>() {
        };
        return new RestTemplate().exchange(request(TIME), responseType).getBody();
    }

    protected RequestEntity<Void> request(String s) {
        return RequestEntity.get(URI.create(SERVER_URL + s))
                .accept(MediaType.APPLICATION_JSON).build();
    }

    private void requestScheduler() {
        timer.schedule(new TimerTask() {

            public void run() {
                HashMap<String, MessageDto> map;
                Long timeLeft;
                HeroesRegistry heroesRegistry = HeroesRegistry.getINSTANCE();

                try {
                    map = playersInGame();
                    timeLeft = startCounter();
                    setTime(timeLeft);
                } catch (RestClientException exception) {
                    map = null;
                }
                if (Objects.nonNull(map)) {
                    initSessionId();
                    heroesRegistry.setHeroesRegistryBook(map);
                    if (map.size() == 0) {
                        setServerState(ServerState.NO_PLAYERS);
                    } else if (map.size() == 1) {
                        setServerState(ServerState.ONE_PLAYER);
                    } else if (map.size() == 2) {
                        if (Objects.nonNull(sessionId) && map.containsKey(sessionId)) {
                            setServerState(ServerState.JOIN_GAME);
                            setMultiplayerState(MultiplayerState.SECOND_PLAYER_REGISTERED);
                        } else {
                            setServerState(ServerState.TWO_PLAYERS);
                        }
                    }
                } else {
                    setServerState(ServerState.SERVER_OFFLINE);
                }
            }

            private void initSessionId() {
                if (!websocketInstance.get_session_id().isEmpty()) {
                    sessionId = websocketInstance.get_session_id();
                }
            }
        }, 0, period);

    }


    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setTime(Long time) {
        if (Objects.nonNull(time)) {
            support.firePropertyChange(TIME_TO_LOG_OUT.getName(), null, time);
        }
    }

    public void setServerState(ServerState new_state) {
        MainMenu mainMenu = MainMenu.getInstance();
        ServerState state = mainMenu.getState();
        support.firePropertyChange(SERVER.getName(), state, new_state);
    }

    public void setMultiplayerState(MultiplayerState new_state) {
        MultiplayerController multiplayerInstance = MultiplayerController.getMultiplayerInstance();
        multiplayerState = multiplayerInstance.getItsState();
        if (multiplayerState.equals(MultiplayerState.WAITING_FOR_SECOND_PLAYER)) {
            support.firePropertyChange(MULTIPLAYER.getName(), multiplayerState, new_state);
            multiplayerState = new_state;
        }
    }

    public static WebsocketScheduler getInstance() {
        return WebsocketScheduler.HELPER.INSTANCE;
    }

    public void cancelTimer() {
        if (hasStarted) {
            timer.cancel();
            hasStarted = false;
        }
    }

    private static class HELPER {
        private final static WebsocketScheduler INSTANCE = new WebsocketScheduler();
    }

}
