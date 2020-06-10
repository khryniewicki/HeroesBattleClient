package com.khryniewicki.projectX.game.multiplayer.heroStorage.positions;

public class MockStartingPosition {
    private  float X;
    private  float Y;
    private static final MockStartingPosition instance = new MockStartingPosition();

    private MockStartingPosition(){}

    public static MockStartingPosition getInstance(){
        return instance;
    }


    public  void setX(float x) {
        X = x;
    }

    public  void setY(float y) {
        Y = y;
    }

    public  float getX() {
        return X;
    }

    public  float getY() {
        return Y;
    }

    public  void setX_Y(float x,float y){
        setX(x);
        setY(y);
    }
}
