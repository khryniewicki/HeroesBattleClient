package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.messageHandler.Channels;
import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.utils.HeroMove;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.concurrent.ConcurrentLinkedDeque;


@Data
@Slf4j

public class HeroSendingService implements Runnable {
    private Float tmpPositionX, tmpPositionY;
    private HeroDTO tmpHero;
    private SuperHero hero;
    private HeroesInstances heroesInstances;
    private HeroStartingPosition heroStartingPosition;
    private final Channels channel;
    private final StackEvent stackEvent;
    private ConcurrentLinkedDeque<HeroDTO> heroDTOS;
    private int counter;

    public HeroSendingService() {
        channel = Channels.getINSTANCE();
        stackEvent = StackEvent.getInstance();
    }

    private void getHeroInstance() {
        if (hero == null) {
            heroStartingPosition = HeroStartingPosition.getInstance();
            heroesInstances = HeroesInstances.getInstance();
            this.hero = heroesInstances.getHero();
            heroDTOS = stackEvent.getHeroDTOS();
        }
    }

    public Float getHeroPositionX() {
        return hero.getX() == null ? heroStartingPosition.getX() : hero.getX();
    }

    public Float getHeroPositionY() {
        return hero.getY() == null ? heroStartingPosition.getY() : hero.getY();
    }

    public HeroDTO getHeroDTO() {

        return new HeroDTO(hero.getName(), hero.getLife(), hero.getMana(), getHeroPositionX(), getHeroPositionY());
    }

    public synchronized void addMessage() {
        getHeroInstance();
        HeroDTO heroDTO = getHeroDTO();

        if (heroDTO.equals(tmpHero)) {
            counter++;
        } else {
            tmpHero = heroDTO;
            counter = 0;
        }
        if (counter < 4) {
            heroDTOS.offerLast(heroDTO);
        }
    }

    public synchronized void updateLife() {
        getHeroInstance();
        heroDTOS.offerLast(getHeroDTO());
    }

    @Override
    public void run() {
        HeroMove heroMove = HeroMove.getInstance();
        stackEvent.setHeroDTOS(new ConcurrentLinkedDeque<>());

        while (true) {
            if (heroMove.isHeroMoving()) {
                addMessage();
            }
            if (heroDTOS != null && heroDTOS.size() != 0) {
                send();
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private synchronized void send() {
        StompSession session = Application.getSession();
        try {
            session.send("/app/hero/" + channel.getApp(), heroDTOS.pop());
        } catch (IllegalStateException e) {
            e.printStackTrace();
            send();
        }
    }


}
