package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.board.Board;
import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;
import lombok.Data;


@Data
public class HeroSendDTO {
    private Float tmpPositionX = HeroStartingPosition.getX();
    private Float tmpPositionY = HeroStartingPosition.getY();
    private HeroDTO tmpHero;
    private SuperHero hero;

    public HeroSendDTO() {
        this.hero = Board.hero;
    }

    private Boolean checkIfCoordinateChanged() {
        if (tmpPositionX != null && tmpPositionX == (hero.getX())) {
            if (tmpPositionY != null && tmpPositionY == (hero.getY())) {
                return false;
            }
        }
        tmpPositionX = hero.getX();
        tmpPositionY = hero.getY();
        tmpHero = new HeroDTO(hero.getName(), tmpPositionX, tmpPositionY);
        return true;
    }

    public HeroDTO getHeroPositions() {
        Boolean isCoordinatesChanged = checkIfCoordinateChanged();

        if (isCoordinatesChanged) {
            return new HeroDTO(hero.getName(), hero.getX(), hero.getY());
        }
        return tmpHero;
    }


}
