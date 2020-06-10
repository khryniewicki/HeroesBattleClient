package com.khryniewicki.projectX.game.multiplayer.heroStorage.positions;

public class HeroStartingPosition {
    private static float X=-4f;
    private static float Y=-4f;
    private static final HeroStartingPosition instance = new HeroStartingPosition();

    private HeroStartingPosition(){}

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
    }
}
