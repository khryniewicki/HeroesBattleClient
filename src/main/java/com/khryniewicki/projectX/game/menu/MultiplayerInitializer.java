package com.khryniewicki.projectX.game.menu;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.Message;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MultiplayerInitializer {
    private Message message;
    private static Application.MyStompSessionHandler handler = new Application.MyStompSessionHandler();
    private boolean isHeroEstablishedCorrectly;

    private HeroStartingPosition heroStartingPosition;


    public void setMessage(Message message) {
        this.message = message;

    }

    public void validateMessage() {
        heroStartingPosition = HeroStartingPosition.getInstance();

        if (message.getContent().equals("1") || message.getContent().equals("2")) {
            int appDTO = Integer.parseInt(message.getContent());
            handler.setApp(appDTO);

            if (appDTO == 1) {
                handler.setTopic(2);
                heroStartingPosition.setX(4f);
                heroStartingPosition.setY(4f);
            } else {
                handler.setTopic(1);
                heroStartingPosition.setX(-3f);
                heroStartingPosition.setY(-3f);
            }
            HelloWorld.isHeroEstablishedCorrectly =true;
            HelloWorld.latch.countDown();

            handler.sendHeroToStompSocket();

        } else {
            HelloWorld.latch.countDown();
            HelloWorld.isHeroEstablishedCorrectly =false;
        }

    }

}
