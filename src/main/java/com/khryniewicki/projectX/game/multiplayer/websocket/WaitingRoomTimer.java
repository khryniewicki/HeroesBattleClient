package com.khryniewicki.projectX.game.multiplayer.websocket;

import com.khryniewicki.projectX.game.user_interface.menu.menus.MenuImp;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
    protected boolean subscribed;
    public void subscribePlayersInGame() {
        addTimer();
        subscribed=true;
        websocketScheduler.addPropertyChangeListener(evt -> {
            String propertyName = evt.getPropertyName();
//            log.info("EVENT NAME: {} , NEW VALUE: {}", propertyName, evt.getNewValue());
//            log.info("RUNNING: {}", isRunning());

            if (propertyName.equals("timeLeftToLogOut")) {
                timeLeftToLogOut = (Long) evt.getNewValue();
                setTimeLeftToLogOut(timeLeftToLogOut);
            } else if (propertyName.equals("playersOnline")) {
                serverState = (ServerState) evt.getNewValue();
                if (serverState == ServerState.JOIN_GAME) {
//                    log.info("JOINED");
                    stop();
                }
            }
        });
    }

    public void suspend() {
        websocketScheduler.removePropertyChangeListener(this);
        subscribed=false;
        log.info("suspend");
    }

    protected void addTimer() {
        permanentImages.add(TIMER);
    }

}
