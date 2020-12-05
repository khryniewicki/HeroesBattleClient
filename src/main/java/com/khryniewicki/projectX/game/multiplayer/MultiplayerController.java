package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.user_interface.menu.menus.WaitingRoomMenu;
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
        log.info("waiting for second player");
        waitingRoomMenu.addText("Waiting for other player");
        WebsocketInitializer websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        try {
            websocketInitializer.waitForSecondPlayer();
            heroesInstances.setMock();
            waitingRoomMenu.addText("Other player has joined game. Game is starting, get ready");
            Thread.sleep(5000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
