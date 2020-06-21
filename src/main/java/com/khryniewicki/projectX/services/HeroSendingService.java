package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.messageHandler.Channels;
import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.utils.HeroMove;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;


@Data
@Slf4j

public class HeroSendingService implements Runnable {
    private Float tmpPositionX, tmpPositionY;
    private HeroDTO tmpHero;
    private SuperHero hero;
    private final HeroesInstances heroesInstances;
    private HeroStartingPosition heroStartingPosition;
    private final Channels channels;

    public HeroSendingService() {
        heroStartingPosition = HeroStartingPosition.getInstance();
        heroesInstances = HeroesInstances.getInstance();
        this.hero = heroesInstances.getHero();
        channels = Channels.getINSTANCE();
    }

    public Float getHeroPositionX() {
        return hero.getX() == null ? heroStartingPosition.getX() : hero.getX();
    }

    public Float getHeroPositionY() {
        return hero.getY() == null ? heroStartingPosition.getY() : hero.getY();
    }

    public HeroDTO getHeroPositions() {
        if (verifyIfCoordinatesChanged()) {
            return new HeroDTO(hero.getName(), hero.getLife(), hero.getMana(), getHeroPositionX(), getHeroPositionY());
        }
        return tmpHero;
    }


    private Boolean verifyIfCoordinatesChanged() {
        if (tmpPositionX != null && tmpPositionX == getHeroPositionX()) {
            if (tmpPositionY != null && tmpPositionY == getHeroPositionY()) {
                return false;
            }
        }

        tmpPositionX = getHeroPositionX();
        tmpPositionY = getHeroPositionY();

        tmpHero = new HeroDTO(hero.getName(), hero.getLife(), hero.getMana(), tmpPositionX, tmpPositionY);
        return true;
    }


    @Override
    public void run() {
        Channels channel = Channels.getINSTANCE();
        HeroMove heroMove = HeroMove.getInstance();

        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (heroMove.isHeroMoving()) {
                StompSession session = Application.getSession();
                session.send("/app/hero/" + channel.getApp(), getHeroPositions());
            }
        }
    }
}
