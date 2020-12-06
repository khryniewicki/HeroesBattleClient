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
            if (propertyName.equals("timeLeftToLogOut")) {
                timeLeftToLogOut = (Long) evt.getNewValue();
                setTimeLeftToLogOut(timeLeftToLogOut);
            } else if (propertyName.equals("playersOnline")) {
                serverState = (ServerState) evt.getNewValue();
                if (serverState == ServerState.JOIN_GAME) {
                    stop();
                }
            }
        });
    }

    public void suspend() {
        websocketScheduler.removePropertyChangeListener(this);
        websocketScheduler.getTimer().cancel();
        subscribed=false;
    }

    protected void  addTimer() {
        permanentImages.add(TIMER);
    }


    @Override
    public void stop(){
        super.stop();
        log.info("stop loop waiting menu");
    }
}
