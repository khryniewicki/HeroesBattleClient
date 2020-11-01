package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.graphics.RenderFactory;
import com.khryniewicki.projectX.game.websocket.WebsocketInitializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MultiplayerController {
    private RenderFactory renderFactory;
    public static String inputText;

    public MultiplayerController() {
        renderFactory = RenderFactory.getRenderFactory();
    }

    public void waitingForSecondPlayer() {
        renderFactory.render("Waiting for other player");

        WebsocketInitializer websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        try {
            websocketInitializer.getSecondPlayerMockType();
            heroesInstances.setMock();

            renderFactory.render("Other player has joined game. Game is starting, get ready");
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void occupiedRoom() {
        renderFactory.render("Try later, room is full");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
