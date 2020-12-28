package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.services.dto.BaseDto;
import com.khryniewicki.projectX.services.dto.HeroDto;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;


@Data
public class StackEvent {
    private ConcurrentLinkedDeque<BaseDto> events;
    private final HeroesInstances heroesInstances;
    private final HeroStartingPosition heroStartingPosition;
    private static StackEvent INSTANCE;
    private boolean hasAction;
    private SuperHero hero;

    public void addDto(BaseDto baseDto) {
        events.add(baseDto);
    }

    public void addHeroDto() {
        if (Objects.isNull(hero)) {
            this.hero = heroesInstances.getHero();
        }
        if (Objects.nonNull(this.hero)) {
            HeroDto hero = new HeroDto.Builder()
                    .heroType(this.hero.getName())
                    .life(this.hero.getLife())
                    .mana(this.hero.getMana())
                    .positionX(getHeroPositionX())
                    .positionY(getHeroPositionY())
                    .build();
            addDto(hero);
        }
    }

    public Float getHeroPositionX() {
        return this.hero.getX() == null ? heroStartingPosition.getX() : this.hero.getX();
    }

    public Float getHeroPositionY() {
        return this.hero.getY() == null ? heroStartingPosition.getY() : this.hero.getY();
    }

    private StackEvent() {
        if (INSTANCE == null) {
            INSTANCE = this;
            heroesInstances = HeroesInstances.getInstance();
            heroStartingPosition = HeroStartingPosition.getInstance();
            events = new ConcurrentLinkedDeque<>();
        } else
            throw new IllegalArgumentException();
    }

    public static synchronized StackEvent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StackEvent();
        }
        return INSTANCE;
    }
}
