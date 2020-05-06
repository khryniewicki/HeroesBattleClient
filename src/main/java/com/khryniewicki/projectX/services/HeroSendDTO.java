package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.utils.GameUtill;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class HeroSendDTO {
    private Float tmpPositionX= GameUtill.heroStartingPositionX;
    private Float tmpPositionY=GameUtill.heroStartingPositionY;
    private HeroDTO tmpHero;
    private Float getPositionX() {
        return Level.getHero_x();
    }

    private Float getPositionY() {
        return Level.getHero_y();
    }

    private Boolean checkIfCoordinateChanged() {

        if (tmpPositionX != null && tmpPositionX == (getPositionX())) {
            if (tmpPositionY != null && tmpPositionY == (getPositionY())) {
                return false;
            }
        }
        tmpPositionX = getPositionX();
        tmpPositionY = getPositionY();
        tmpHero = new HeroDTO(Level.hero.getName(),tmpPositionX, tmpPositionY);
        return true;
    }

    public HeroDTO getHeroPositions() {
        Boolean isCoordinatesChanged = checkIfCoordinateChanged();

        if (isCoordinatesChanged) {
            return new HeroDTO(Level.hero.getName(),getPositionX(), getPositionY());
        }
        return tmpHero;
    }


}
