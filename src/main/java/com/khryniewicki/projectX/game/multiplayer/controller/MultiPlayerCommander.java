package com.khryniewicki.projectX.game.multiplayer.controller;

import com.khryniewicki.projectX.game.engine.Command;
import com.khryniewicki.projectX.game.engine.LifeCycle;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketController;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import com.khryniewicki.projectX.game.ui.menu.menus.WaitingRoomMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;
import java.util.Deque;
import java.util.LinkedList;

@Getter
@Setter
@Slf4j
public abstract class MultiPlayerCommander implements LifeCycle, PropertyChangeListener {
    protected final WaitingRoomMenu waitingRoomMenu;
    protected final HeroesInstances heroesInstances;
    protected final WebsocketController websocketController;
    protected final WebsocketScheduler websocketScheduler;
    protected Deque<Command> itsCommands = new LinkedList<>();
    protected MultiplayerState itsState;

    public MultiPlayerCommander() {
        waitingRoomMenu = WaitingRoomMenu.getWaitingRoomMenu();
        heroesInstances = HeroesInstances.getInstance();
        websocketController = WebsocketController.getWebsocketInstance();
        websocketScheduler = WebsocketScheduler.getInstance();
    }

    protected Command connect() {
        return () -> {
            waitingRoomMenu.addText("Waiting for connections...");
            websocketController.initialize_websocket();
            waitingRoomMenu.addText("Connection established");
            websocketController.start_sending_service();
            websocketController.join_room();
        };
    }

    protected Command waiting_for_second_player() {
        return () -> {
            waitingRoomMenu.addText("Waiting for second player");
            waitingRoomMenu.addTimer();
        };
    }

    protected Command updateTime(Long timeLeft) {
        return () -> {
            waitingRoomMenu.setTimeLeftToLogOut(timeLeft);
            waitingRoomMenu.update();
        };
    }

    protected Command second_player_registered() {
        return () -> {
            waitingRoomMenu.addText("Other player has joined game. Game is starting, get ready");
            heroesInstances.setHero();
            heroesInstances.setMock();
            stop();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitingRoomMenu.restart();
        };
    }


    protected void execute(Command Command) {
        itsCommands.add(Command);
    }


    public void stopWebsocket() {
        websocketController.stop_websocket();
    }

    public void killProcess(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().halt(0);
    }
}
