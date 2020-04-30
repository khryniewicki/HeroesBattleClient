package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.level.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {
    Float tmpPositionX, tmpPositionY;

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
        return true;
    }

    public String getHeroPositions() {
        Boolean isCoordinatesChanged = checkIfCoordinateChanged();
        if (isCoordinatesChanged) {
            return String.format("[x : %f, y : %f ", getPositionX(), getPositionY());
        }
        return "Nothing";

    }
}
