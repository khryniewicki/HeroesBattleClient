package com.khryniewicki.projectX.config.messageHandler;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;
import lombok.Data;

@Data
public class MessageHandler {
    private Message message;
    private static Application.MyStompSessionHandler handler;
    private HeroStartingPosition heroStartingPosition;
    private  boolean flag=true;
    private Channels channels;
    private MessageHandler() {
        handler = new Application.MyStompSessionHandler();
        channels=Channels.getINSTANCE();
    }
    private final static MessageHandler INSTANCE=new MessageHandler();

    public void setMessage(Message message) {
        this.message = message;

    }

    public static MessageHandler getINSTANCE() {
        return INSTANCE;
    }

    public boolean validateMessage() {
        heroStartingPosition = HeroStartingPosition.getInstance();

        if ((message.getContent().equals("1") || message.getContent().equals("2"))&& flag) {
            int appDTO = Integer.parseInt(message.getContent());
            channels.setApp(appDTO);
            setFlag(false);
            System.out.println(message.getContent());

            if (appDTO == 1) {
                channels.setTopic(2);

                HeroStartingPosition.setX(4f);
                HeroStartingPosition.setY(4f);
            } else {
                channels.setTopic(1);
                HeroStartingPosition.setX(-3f);
                HeroStartingPosition.setY(-3f);
            }
            LoadedStatus.INSTANCE().HeroLoadedProperly = true;
            Game.latch.countDown();
            handler.sendHeroToStompSocket();

            return true;


        } else {
            LoadedStatus.INSTANCE().HeroLoadedProperly = false;
            return false;
        }
    }
}
