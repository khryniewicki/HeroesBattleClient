package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.graphics.Texture;

public interface Symbol {
    void render();
    default void setTexture(Texture texture){};
}
