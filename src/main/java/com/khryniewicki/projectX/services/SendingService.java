package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.messageHandler.Channels;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.services.DTO.DTO;
import com.khryniewicki.projectX.services.DTO.HeroDTO;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import com.khryniewicki.projectX.utils.HeroAction;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.concurrent.ConcurrentLinkedDeque;


@Data
@Slf4j
public class SendingService implements Runnable {
    private Float tmpPositionX, tmpPositionY;
    private HeroDTO tmpHero;
    private SuperHero hero;
    private HeroesInstances heroesInstances;
    private HeroStartingPosition heroStartingPosition;
    private final Channels channel;
    private final StackEvent stackEvent;
    private ConcurrentLinkedDeque<DTO> heroDTOS;
    private int counter;
    private HeroAction heroAction;

    public SendingService() {
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

    public synchronized void updatePosition() {
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

    public synchronized void sendSpell(SpellDTO spellDTO) {
        getHeroInstance();
        heroDTOS.offerLast(spellDTO);
    }

    @Override
    public void run() {
        heroAction = HeroAction.getInstance();
        stackEvent.setHeroDTOS(new ConcurrentLinkedDeque<>());

        while (true) {
            move();
        }
    }


    private void move() {
        if (heroAction.isHeroMoving()) {
            updatePosition();
        }

        send();
    }


    private synchronized void send() {
        if (heroDTOS != null && heroDTOS.size() != 0) {
            StompSession session = Application.getSession();
            DTO dto = heroDTOS.pop();

            try {
                session.send(path(dto), dto);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            sleep();
        }
    }

    private String path(DTO pop) {
        String path = "/app/hero/";
        if (pop.isSpellDTO()) {
            path = "/app/hero/";
        }
        return path+channel.getApp();
    }

    private void sleep() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
