package com.khryniewicki.projectX.game.multiplayer.heroStorage.positions;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;

public class HeroStartingPosition {
    private static float X;
    private static float Y;
    private static final HeroStartingPosition instance = new HeroStartingPosition();

    private HeroStartingPosition(){

    }

    public static HeroStartingPosition getInstance(){
        return instance;
    }


    public static void setX(float x) {
        X = x;
    }

    public static void setY(float y) {
        Y = y;
    }

    public static float getX() {
        return X;
    }

    public static float getY() {
        return Y;
    }

    public static void setX_Y(float x,float y){
        setX(x);
        setY(y);
        HeroesInstances heroesInstances=HeroesInstances.getInstance();
        SuperHero hero = heroesInstances.getHero();
        hero.setPositionX(getX());
        hero.setPositionY(getY());
    }



}
