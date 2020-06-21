package com.khryniewicki.projectX.config.messageHandler;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Data
@Slf4j
public class MessageHandler {
    private Message message;
    private static Application.MyStompSessionHandler handler;
    private HeroStartingPosition heroStartingPosition;
    private MockStartingPosition mockStartingPosition;
    private boolean flag = true;
    private Channels channels;
    private CountDownLatch latch;

    private MessageHandler() {
        handler = new Application.MyStompSessionHandler();
        channels = Channels.getINSTANCE();
    }

    private final static MessageHandler INSTANCE = new MessageHandler();

    public void setMessage(Message message) {
        this.message = message;

    }

    public static MessageHandler getINSTANCE() {
        return INSTANCE;
    }

    public void latchCountDownMethod() {
        heroStartingPosition = HeroStartingPosition.getInstance();
        mockStartingPosition = MockStartingPosition.getInstance();
        validateMessage();
    }


    public void validateMessage() {
        if (isNumeric(message.getContent())) {
            setChannelsAndStartingPositions();
        }
        Game.latch.countDown();
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void setChannelsAndStartingPositions() {
        int appDTO = Integer.parseInt(message.getContent());

        if ((appDTO == 1 || appDTO == 2) && flag) {

            setChannels(appDTO);
            setHeroesStartingPositions();
            setFlag(false);

            LoadedStatus.INSTANCE().isHeroLoaded = true;
            log.info("APP:" + channels.getApp() + " TOPIC:" + channels.getTopic());

        } else {
            LoadedStatus.INSTANCE().isHeroLoaded = false;
        }
    }

    private void setChannels(int appDTO) {
        channels.setApp(appDTO);
        if (appDTO == 1) {
            channels.setTopic(2);
        } else if (appDTO == 2) {
            channels.setTopic(1);
        }
    }

    private void setHeroesStartingPositions() {
        Integer app = channels.getApp();
        Position A = new Position(4f, 4f);
        Position B = new Position(-3f, -3f);
        if (app == 1) {
            heroStartingPosition.setX_Y(A.getPositionX(), A.getPositionY());
            mockStartingPosition.setX_Y(B.getPositionX(), B.getPositionY());
        } else {
            mockStartingPosition.setX_Y(A.getPositionX(), A.getPositionY());
            heroStartingPosition.setX_Y(B.getPositionX(), B.getPositionY());
        }

    }
}
