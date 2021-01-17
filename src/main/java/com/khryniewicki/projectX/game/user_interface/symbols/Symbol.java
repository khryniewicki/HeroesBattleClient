package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.graphics.Texture;

public interface Symbol {
    void render();
    void reload();
    default String getName() {
        return "";
    }

    default void setTexture(Texture texture) {
    }

    default void update() {
    }

    default boolean isDisabled() {
        return false;
    }

    default void setDisabled(boolean disabled) {
    }

}
