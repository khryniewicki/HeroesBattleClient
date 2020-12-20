package com.khryniewicki.projectX.game.engine;

public interface GameLoop {
    void render();
    void update();
    void insideLoop();
    void init();
    void begin();
    void stop();
    void swapBuffers();
    void clearBuffers();
}
