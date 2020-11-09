package com.khryniewicki.projectX.game.engine;

public interface GameLoop {
    void render();
    void update();
    void loop();
    void init();
    void begin();
    void stop();
}
