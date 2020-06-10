package com.khryniewicki.projectX.config.messageHandler;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import lombok.Data;

@Data
public class MessageHandler {
    private Message message;
    private static Application.MyStompSessionHandler handler;
    private HeroStartingPosition heroStartingPosition;
    private boolean flag = true;
    private Channels channels;

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

    public boolean validateMessage() {
        heroStartingPosition = HeroStartingPosition.getInstance();
        MockStartingPosition mockStartingPosition = MockStartingPosition.getInstance();

        if ((message.getContent().equals("1") || message.getContent().equals("2")) && flag) {
            int appDTO = Integer.parseInt(message.getContent());
            channels.setApp(appDTO);
            setFlag(false);
            if (appDTO == 1) {
                channels.setTopic(2);
                heroStartingPosition.setX_Y(4f, 4f);
                mockStartingPosition.setX_Y(-3f, -3f);
            } else if (appDTO==2){
                channels.setTopic(1);
                heroStartingPosition.setX_Y(-3f, -3f);
                mockStartingPosition.setX_Y(4.2f, 4.2f);
            }
            System.out.println("APP:"+channels.getApp()+"TOPIC:"+channels.getTopic());

            LoadedStatus.INSTANCE().HeroLoadedProperly = true;
            Game.latch.countDown();

            return true;


        } else {
            LoadedStatus.INSTANCE().HeroLoadedProperly = false;
            return false;
        }
    }
}
