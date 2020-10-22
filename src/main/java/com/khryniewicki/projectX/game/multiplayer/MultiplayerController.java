package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.renderer.RenderFactory;
import com.khryniewicki.projectX.game.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.utils.ConnectionTextFactory;
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
        renderFactory.render(ConnectionTextFactory.OTHER_PLAYER);

        WebsocketInitializer websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        try {
            websocketInitializer.getSecondPlayerMockType();
            heroesInstances.setMock();

            renderFactory.render(ConnectionTextFactory.GET_READY);
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void occupiedRoom() {
        renderFactory.render(ConnectionTextFactory.TRY_LATER);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
