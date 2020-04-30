package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.Character.Hero;
import com.khryniewicki.projectX.game.Character.HeroDTO;
import com.khryniewicki.projectX.game.Map.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {
    private Float tmpPositionX, tmpPositionY;
    private HeroDTO tmpHero;
    private Float getPositionX() {
        return Level.getHero_x();
    }

    private Float getPositionY() {
        return Level.getHero_y();
    }

    private Boolean checkIfCoordinateChanged() {

        if (tmpPositionX != null && tmpPositionX==(getPositionX())) {
            if (tmpPositionY != null && tmpPositionY==(getPositionY())) {
                return false;
            }
        }
        tmpPositionX = getPositionX();
        tmpPositionY = getPositionY();
        tmpHero=new HeroDTO(tmpPositionX,tmpPositionY);
        return true;
    }

    public HeroDTO getHeroPositions() {
        Boolean isCoordinatesChanged = checkIfCoordinateChanged();

        if (isCoordinatesChanged) {
          return new HeroDTO(getPositionX(),getPositionY());
        }
        return tmpHero ;

    }
}
