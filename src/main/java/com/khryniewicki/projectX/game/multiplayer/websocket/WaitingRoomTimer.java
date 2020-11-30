package com.khryniewicki.projectX.game.multiplayer.websocket;

import com.khryniewicki.projectX.game.user_interface.menu.menus.MenuImp;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.TIMER;

@Getter
@Setter
@Slf4j
public class WaitingRoomTimer extends MenuImp {
    protected ServerState serverState;
    protected WebsocketScheduler websocketScheduler;
    protected MenuSymbol counter;
    protected Long timeLeftToLogOut;
    protected Long tmpTime = 0L;
    protected volatile boolean joined;

    public void subscribePlayersInGame() {
        addTimer();
        websocketScheduler.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if (propertyName.equals("timeLeftToLogOut")) {
                    timeLeftToLogOut = (Long) evt.getNewValue();
                    log.info("{}",timeLeftToLogOut);
                    setTimeLeftToLogOut(timeLeftToLogOut);
                } else if (propertyName.equals("playersOnline")) {
                    serverState = (ServerState) evt.getNewValue();
                    if (serverState == ServerState.JOIN_GAME) {
                        setJoined(true);
                        websocketScheduler.removePropertyChangeListener(this);
                    }
                }
            }
        });
    }

    protected void addTimer() {
        permanentImages.add(TIMER);
    }

}
