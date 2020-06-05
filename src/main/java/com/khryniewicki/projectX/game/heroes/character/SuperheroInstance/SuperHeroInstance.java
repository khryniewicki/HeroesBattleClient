package com.khryniewicki.projectX.game.heroes.character.SuperheroInstance;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;


public class SuperHeroInstance {
    private SuperHero hero;
    private SuperHero mock;

    public SuperHero getHero() {
        return hero;
    }
    public SuperHero getMock() {
        return mock;
    }

    public void setHero(SuperHero hero) {
        this.hero = hero;
    }

    public void setMock(SuperHero mock) {
        this.mock = mock;
    }

    private SuperHeroInstance() {
    }

    public static SuperHeroInstance getInstance() {
        return HELPER.INSTANCE;
    }




    private static class HELPER {

        public static final SuperHeroInstance INSTANCE = new SuperHeroInstance();

    }
}
