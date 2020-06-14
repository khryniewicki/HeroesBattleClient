package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import lombok.Data;


@Data
public class HeroSendingService {
    private Float tmpPositionX,tmpPositionY;
    private HeroDTO tmpHero;
    private SuperHero hero;
    private HeroesInstances heroesInstances;
    private HeroStartingPosition heroStartingPosition;

    public HeroSendingService() {
        heroesInstances = HeroesInstances.getInstance();
        this.hero = heroesInstances.getHero();
    }

    public Float getHeroPositionX() {return hero.getX()!=null ? hero.getX() : heroStartingPosition.getX(); }

    public Float getHeroPositionY() {
        return hero.getY()!=null? hero.getY() : heroStartingPosition.getY();
    }

    public HeroDTO getHeroPositions() {
        if (verifyIfCoordinatesChanged()) {
            return new HeroDTO(hero.getName(),hero.getLife(),hero.getMana(), getHeroPositionX(), getHeroPositionY());
        }
        return tmpHero;
    }


    private Boolean verifyIfCoordinatesChanged() {
        if (tmpPositionX != null && tmpPositionX ==getHeroPositionX()) {
            if (tmpPositionY != null && tmpPositionY == getHeroPositionY()) {
                return false;
            }
        }

        tmpPositionX = getHeroPositionX();
        tmpPositionY = getHeroPositionY();

        tmpHero = new HeroDTO(hero.getName(), hero.getLife(),hero.getMana(),tmpPositionX, tmpPositionY);
        return true;
    }




}
