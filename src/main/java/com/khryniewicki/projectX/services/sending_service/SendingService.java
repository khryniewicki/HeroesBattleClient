package com.khryniewicki.projectX.services.sending_service;

import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.Channels;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;
import com.khryniewicki.projectX.services.dto.BaseDto;
import com.khryniewicki.projectX.services.dto.BaseDtoType;
import com.khryniewicki.projectX.services.dto.HeroDto;
import com.khryniewicki.projectX.services.dto.SpellDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;


@Getter
@Setter
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
    private boolean disconnected;

    public SendingService() {
        this.channel = Channels.getINSTANCE();
        this.stackEvent = StackEvent.getInstance();

    }

    public boolean no_duplicates_filter(BaseDto baseDto) {

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
        log.info("STARTED SENDING SERVICE LOOP");
        while (running) {
            send();
            is_player_disconnected();
        }
    }

    private void is_player_disconnected() {
        if (disconnected) {
            stop();
        }
    }

    private synchronized void send() {
        if (events != null && events.size() != 0) {
            BaseDto baseDto = events.pop();
            if (baseDto.getStatus().equals(ConnectionState.CONNECTED)){
            }
            try {
                if (Objects.isNull(session)) {
                    session = WebsocketApplication.getSession();
                }
                if (session.isConnected() && no_duplicates_filter(baseDto)) {
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
        if (running) {
            setRunning(false);
            stackEvent.reset();
        }
    }
}
