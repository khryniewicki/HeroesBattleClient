package com.khryniewicki.projectX.utils;

import lombok.Data;

@Data
public class HeroAction {

    private boolean isHeroMoving;
    private boolean isHeroCastingSpell;
    private static HeroAction INSTANCE;

    private HeroAction() {
        if (INSTANCE == null) {
            INSTANCE = this;
        } else
            throw new IllegalArgumentException();
    }

    public static synchronized HeroAction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HeroAction();
        }
        return INSTANCE;
    }
}
