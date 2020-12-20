package com.khryniewicki.projectX.game.multiplayer.controller;

import com.khryniewicki.projectX.game.engine.GameLoopImp;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
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
    protected final WebsocketInitializer websocketInitializer;
    protected final WebsocketScheduler websocketScheduler;
    protected Deque<Command> itsCommands = new LinkedList<>();
    protected MultiplayerState itsState;

    public MultiPlayerCommander() {
        waitingRoomMenu = WaitingRoomMenu.getWaitingRoomMenu();
        heroesInstances = HeroesInstances.getInstance();
        websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        websocketScheduler = WebsocketScheduler.getInstance();
    }

    protected Command connect() {
        return () -> {
            waitingRoomMenu.addText("Waiting for connections...");
            websocketInitializer.initializeWebsocket();
            waitingRoomMenu.addText("Connection established");
            websocketInitializer.registerHero();
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
            stop();
            heroesInstances.setHeroBasicProperties();
            heroesInstances.setHeroMoveSetting();
            heroesInstances.setMock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
    }


    protected void execute(Command command) {
        itsCommands.add(command);
    }

    protected interface Command {
        void execute();
    }

}
