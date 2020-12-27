package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.services.DTO.DTO;
import com.khryniewicki.projectX.services.DTO.HeroDTO;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;


@Data
@Slf4j
public class SendingService implements Runnable {
    private final Channels channel;
    private final StackEvent stackEvent;
    private HeroDTO tmpHero;
    private SuperHero hero;
    private HeroesInstances heroesInstances;
    private HeroStartingPosition heroStartingPosition;
    private ConcurrentLinkedDeque<DTO> events;
    private int counter;
    private StompSession session;
    private boolean running;

    public SendingService() {
        channel = Channels.getINSTANCE();
        stackEvent = StackEvent.getInstance();
    }

    private void getHeroInstance() {
        if (hero == null) {
            this.heroStartingPosition = HeroStartingPosition.getInstance();
            this.heroesInstances = HeroesInstances.getInstance();
            this.hero = heroesInstances.getHero();
            this.events = stackEvent.getEvents();
        }
    }

    public Float getHeroPositionX() {
        return hero.getX() == null ? heroStartingPosition.getX() : hero.getX();
    }

    public Float getHeroPositionY() {
        return hero.getY() == null ? heroStartingPosition.getY() : hero.getY();
    }

    public HeroDTO getHeroDTO() {
        return new HeroDTO.Builder()
                .heroType(hero.getName())
                .life(hero.getLife())
                .mana(hero.getMana())
                .positionX(getHeroPositionX())
                .positionY(getHeroPositionY())
                .build();
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
            events.offerLast(heroDTO);
        }
    }

    public synchronized void updateLife() {
        getHeroInstance();
        stackEvent.setHasAction(true);
    }


    @Override
    public void run() {
        stackEvent.setEvents(new ConcurrentLinkedDeque<>());
        session = WebsocketApplication.getSession();
        running = true;
        while (running) {
            action();
            send();
        }
        if (Objects.nonNull(session) && session.isConnected()) {
            session.disconnect();
        }
    }

    private void action() {
        if (stackEvent.hasAction()) {
            updatePosition();
        }
    }

    private synchronized void send() {
        if (events != null && events.size() != 0) {
            DTO dto = events.pop();
            try {
                if (session.isConnected()) {
                    session.send(path(dto), dto);
                }
            } catch (MessageDeliveryException e) {
                e.printStackTrace();
            }
        }
        sleep();
    }

    private String path(DTO pop) {
        String path = "/app/hero/";
        if (pop.isSpellDTO()) {
            path = "/app/spell/";
        }
        return path + channel.getApp();
    }

    private void sleep() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        setRunning(false);
        events.clear();
        log.info("SENDING SERVICE STOPPED");
    }
}
