package com.khryniewicki.projectX.config.messageHandler;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;

public class MessageHandler {
    private Message message;
    private static Application.MyStompSessionHandler handler ;
    private HeroStartingPosition heroStartingPosition;

    public MessageHandler() {
        handler= new Application.MyStompSessionHandler();

    }

    public void setMessage(Message message) {
        this.message = message;

    }

    public boolean validateMessage() {
        heroStartingPosition = HeroStartingPosition.getInstance();

        if (message.getContent().equals("1") || message.getContent().equals("2")) {
            int appDTO = Integer.parseInt(message.getContent());
            handler.setApp(appDTO);

            if (appDTO == 1) {
                handler.setTopic(2);
                HeroStartingPosition.setX(4f);
                HeroStartingPosition.setY(4f);
            } else {
                handler.setTopic(1);
                HeroStartingPosition.setX(-3f);
                HeroStartingPosition.setY(-3f);
            }
            LoadedStatus.INSTANCE().HeroLoadedProperly=true;
            Game.latch.countDown();
            handler.sendHeroToStompSocket();

            return true;


        } else {
            LoadedStatus.INSTANCE().HeroLoadedProperly=false;
            return false;
        }

    }
}
