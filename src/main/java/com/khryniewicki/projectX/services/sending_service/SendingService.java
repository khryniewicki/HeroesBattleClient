package com.khryniewicki.projectX.services.sending_service;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;
import com.khryniewicki.projectX.services.dto.BaseDto;
import com.khryniewicki.projectX.services.dto.BaseDtoType;
import com.khryniewicki.projectX.services.dto.HeroDto;
import com.khryniewicki.projectX.services.dto.SpellDto;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.concurrent.ConcurrentLinkedDeque;


@Data
@Slf4j
public class SendingService implements Runnable {
    private final Channels channel;
    private final StackEvent stackEvent;
    private ConcurrentLinkedDeque<BaseDto> events;
    private int counterHero;
    private int counterSpell;
    private StompSession session;
    private boolean running;
    private BaseDto tmpHero = new HeroDto();
    private BaseDto tmpSpell = new SpellDto();
    private boolean player_left;

    public SendingService() {
        this.channel = Channels.getINSTANCE();
        this.stackEvent = StackEvent.getInstance();
        this.events = stackEvent.getEvents();
    }

    public boolean filter(BaseDto baseDto) {

        switch (baseDto.getType()) {
            case HERO:
                if (baseDto.equals(tmpHero)) {
                    counterHero++;
                } else {
                    tmpHero = baseDto;
                    counterHero = 0;
                }
                return counterHero < 4;
            case SPELL:
                if (baseDto.equals(tmpSpell)) {
                    counterSpell++;
                } else {
                    tmpSpell = baseDto;
                    counterSpell = 0;
                }
                return counterHero < 4;
            case MESSAGE:
                if (baseDto.getStatus().equals(ConnectionState.DISCONNECTED)){
                    setPlayer_left(true);
                }
                return true;
            default:
                return false;
        }

    }


    @Override
    public void run() {
        session = WebsocketApplication.getSession();
        running = true;
        while (running) {
            send();
            is_player_left();
        }
    }

    private void is_player_left() {
        if (player_left) {
            stop();
        }
    }

    private synchronized void send() {
        if (events != null && events.size() != 0) {
            BaseDto baseDto = events.pop();
            try {
                if (session.isConnected() && filter(baseDto)) {
                    String path = path(baseDto);
                    session.send(path, baseDto);
                }
            } catch (MessageDeliveryException e) {
                e.printStackTrace();
            }
        }
        sleep();
    }

    private String path(BaseDto pop) {
        BaseDtoType type = pop.getType();
        switch (type) {
            case HERO:
                return "/app/hero/" + channel.getApp();
            case SPELL:
                return "/app/spell/" + channel.getApp();
            default:
                return "/app/room";
        }
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
