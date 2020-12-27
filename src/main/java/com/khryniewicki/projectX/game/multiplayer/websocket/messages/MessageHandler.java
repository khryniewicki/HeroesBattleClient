package com.khryniewicki.projectX.game.multiplayer.websocket.messages;

import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;
import com.khryniewicki.projectX.game.user_interface.symbols.observers.Subject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MessageHandler {
    private final Channels channels;
    private final Subject subject;
    private final MultiplayerController multiplayer;
    private Message message;

    private MultiplayerState itState;

    public MessageHandler() {
        channels = Channels.getINSTANCE();
        subject = new Subject();
        multiplayer = MultiplayerController.getMultiplayerInstance();
        subject.addPropertyChangeListener(multiplayer);
    }

    public void setChannelsAndStartingPositions(Message message) {
        if (validate(message.getChannel())) {
            this.message = message;
            setChannelsAndStartingPositions();
        }
    }

    public static boolean validate(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void setChannelsAndStartingPositions() {
        int appDTO = Integer.parseInt(message.getChannel());
        itState = multiplayer.getItsState();

        if ((appDTO == 1 || appDTO == 2) && itState.equals(MultiplayerState.CONNECT)) {
            setChannels(appDTO);
            setHeroesStartingPositions();
            subject.setNews(MultiplayerState.WAITING_FOR_SECOND_PLAYER);
            log.info("APP:" + channels.getApp() + " TOPIC:" + channels.getTopic());
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
        HeroStartingPosition heroStartingPosition = HeroStartingPosition.getInstance();
        MockStartingPosition mockStartingPosition = MockStartingPosition.getInstance();
        Integer app = channels.getApp();
        Position A = new Position(4f, 4f);
        Position B = new Position(-10f, -5.0f);
        if (app == 1) {
            heroStartingPosition.setX_Y(A.getX(), A.getY());
            mockStartingPosition.setX_Y(B.getX(), B.getY());
        } else {
            mockStartingPosition.setX_Y(A.getX(), A.getY());
            heroStartingPosition.setX_Y(B.getX(), B.getY());
        }
    }


}
