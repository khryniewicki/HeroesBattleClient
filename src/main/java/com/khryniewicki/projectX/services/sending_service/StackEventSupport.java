package com.khryniewicki.projectX.services.sending_service;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;
import com.khryniewicki.projectX.services.dto.HeroDto;
import com.khryniewicki.projectX.services.dto.MessageDto;

import java.util.Objects;

import static com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication.session;

public class StackEventSupport {
    private final HeroesInstances heroesInstances;
    private final HeroStartingPosition heroStartingPosition;
    private SuperHero hero;

    public StackEventSupport() {
        heroesInstances = HeroesInstances.getInstance();
        heroStartingPosition = HeroStartingPosition.getInstance();
    }


    public HeroDto addHeroDto() {
        if (Objects.isNull(hero)) {
            this.hero = heroesInstances.getHero();
        }
        return new HeroDto.Builder()
                .heroType(this.hero.getName())
                .life(this.hero.getLife())
                .mana(this.hero.getMana())
                .positionX(getHeroPositionX())
                .positionY(getHeroPositionY())
                .build();
    }

//    public MessageDto join_room() {
//        if (Objects.isNull(hero)) {
//            this.hero = heroesInstances.getHero();
//        }
//        return new MessageDto.Builder()
//                .status(ConnectionState.CONNECTED)
//                .sessionID(session.getSessionId())
//                .heroType(this.hero.getName())
//                .playerName(heroesInstances.getHeroName())
//                .build();
//    }
//
//    public MessageDto leave_room() {
//        return new MessageDto.Builder()
//                .status(ConnectionState.DISCONNECTED)
//                .sessionID(session.getSessionId())
//                .build();
//    }

    public Float getHeroPositionX() {
        return this.hero.getX() == null ? heroStartingPosition.getX() : this.hero.getX();
    }

    public Float getHeroPositionY() {
        return this.hero.getY() == null ? heroStartingPosition.getY() : this.hero.getY();
    }
}
