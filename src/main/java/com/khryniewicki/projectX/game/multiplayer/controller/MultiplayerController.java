package com.khryniewicki.projectX.game.multiplayer.controller;

import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Getter
@Setter
@Slf4j
public class MultiplayerController extends MultiPlayerCommander implements PropertyChangeListener {
    private static final MultiplayerController Instance = new MultiplayerController();
    public static final String multiplayer = "multiplayer";
    public static final String timer = "timeLeftToLogOut";

    private MultiplayerController() {
        super();
        websocketScheduler.addPropertyChangeListener(this);
        restart();
    }

    public void execute() {
        begin();
        loop();
        restart();
    }

    @Override
    public void update() {
        if (itsCommands.size() > 0) {
            Command c = itsCommands.getFirst();
            itsCommands.removeFirst();
            c.execute();
        }
    }

    @Override
    public void render() {
        waitingRoomMenu.render();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (propertyName.equals(multiplayer)) {
            MultiplayerState state = (MultiplayerState) evt.getNewValue();

            if (!itsState.equals(state)) {
                switch (state) {
                    case CONNECT:
                        execute(connect());
                        break;
                    case WAITING_FOR_SECOND_PLAYER:
                        execute(waiting_for_second_player());
                        break;
                    case SECOND_PLAYER_REGISTERED:
                        execute(second_player_registered());
                        break;
                }
                setItsState(state);
            }
        } else if (propertyName.equals(timer)) {
            Long timeLeft = (Long) evt.getNewValue();
            execute(updateTime(timeLeft));
        }
    }

    private void restart() {
        itsState = MultiplayerState.NOT_CONNECTED;
    }

    public static MultiplayerController getMultiplayerInstance() {
        return Instance;
    }



}
