package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.graphics.Texture;

import java.beans.PropertyChangeListener;

public interface Symbol {
    void render();

    default String getName() {
        return "";
    }

    default void setTexture(Texture texture) {
    }

    default void update(){}
}
