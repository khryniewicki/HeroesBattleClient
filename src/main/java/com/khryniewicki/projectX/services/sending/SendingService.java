package com.khryniewicki.projectX.services.sending;

import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;
import com.khryniewicki.projectX.services.dto.BaseDto;
import com.khryniewicki.projectX.services.dto.BaseDtoType;
import com.khryniewicki.projectX.services.dto.HeroesDto;
import com.khryniewicki.projectX.services.dto.SpellDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.concurrent.ConcurrentLinkedDeque;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Getter
@Setter
@Slf4j
public class SendingService implements Runnable {
    public static final String APP_HERO = "/app/hero/";
    public static final String APP_SPELL = "/app/spell/";
    public static final String APP_ROOM = "/app/room";

    private final Channels channel;
    private final StackEvent stackEvent;
    private int counterHero;
    private int counterSpell;
    private boolean running;
    private boolean disconnected;
    private ConcurrentLinkedDeque<BaseDto> events;
    private StompSession session;
    private BaseDto tmpHero = new HeroesDto();
    private BaseDto tmpSpell = new SpellDto();

    public SendingService() {
        this.channel = Channels.getINSTANCE();
        this.stackEvent = StackEvent.getInstance();
    }

    public boolean noDuplicatesFilter(BaseDto baseDto) {

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
                if (baseDto.getStatus().equals(ConnectionState.DISCONNECTED)) {
                    setDisconnected(true);
                }
                return true;
            default:
                return false;
        }

    }


    @Override
    public void run() {
        stackEvent.setEvents(new ConcurrentLinkedDeque<>());
        this.events = stackEvent.getEvents();
        running = true;
        while (running) {
            send();
            isPlayerDisconnected();
        }
    }

    private void isPlayerDisconnected() {
        if (disconnected) {
            stop();
        }
    }

    private synchronized void send() {
        if (nonNull(events) && events.size() != 0) {
            BaseDto baseDto = events.pop();
            try {
                if (isNull(session)) {
                    session = WebsocketApplication.getSession();
                }
                log.info("BaseDto: {}", baseDto);
                if (session.isConnected() && noDuplicatesFilter(baseDto)) {
                    String path = path(baseDto);
                    session.send(path, baseDto);
                }
            } catch (MessageDeliveryException e) {
                log.error("Message not delivered: ", e);
            }
        }
        sleep();
    }

    private String path(BaseDto pop) {
        BaseDtoType type = pop.getType();
        switch (type) {
            case HERO:
                return APP_HERO + channel.getApp();
            case SPELL:
                return APP_SPELL + channel.getApp();
            default:
                return APP_ROOM;
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
        if (running) {
            setRunning(false);
            stackEvent.reset();
        }
    }
}
