package com.khryniewicki.projectX.utils;

import lombok.Data;

@Data
public class HeroMove {

    private boolean isHeroMoving;
    private static HeroMove INSTANCE;

    private HeroMove() {
        if (INSTANCE == null) {
            INSTANCE = this;
        } else
            throw new IllegalArgumentException();
    }

    public static synchronized HeroMove getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HeroMove();
        }
        return INSTANCE;
    }
}
