package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.WaitingRoomMenu;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MultiplayerController {

    private WaitingRoomMenu waitingRoomMenu;

    public MultiplayerController() {
        waitingRoomMenu = WaitingRoomMenu.getWaitingRoomMenu();
    }

    public void waitingForSecondPlayer() {
        waitingRoomMenu.addText("Waiting for other player");
        WebsocketInitializer websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        websocketInitializer.waitForSecondPlayer();
        if (waitingRoomMenu.isRestart()) {
            Game game = Game.getInstance();
            game.initializeMultiplayerGame();
        } else {
            try {
                heroesInstances.setMock();
                waitingRoomMenu.addText("Other player has joined game. Game is starting, get ready");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void occupiedRoom() {
        waitingRoomMenu.addText("Try later, room is full");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
