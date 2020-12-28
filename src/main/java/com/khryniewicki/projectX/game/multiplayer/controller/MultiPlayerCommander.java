package com.khryniewicki.projectX.game.multiplayer.controller;

import com.khryniewicki.projectX.game.engine.GameLoopImp;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketController;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import com.khryniewicki.projectX.game.user_interface.menu.menus.WaitingRoomMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.LinkedList;

@Getter
@Setter
public class MultiPlayerCommander extends GameLoopImp {
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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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


    protected void execute(Command command) {
        itsCommands.add(command);
    }

    protected interface Command {
        void execute();
    }

}
