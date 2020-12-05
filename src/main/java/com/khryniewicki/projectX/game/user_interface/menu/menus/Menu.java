package com.khryniewicki.projectX.game.user_interface.menu.menus;

import org.springframework.stereotype.Component;

@Component
public interface Menu extends com.khryniewicki.projectX.game.user_interface.symbols.Symbol {
    void addEventClick();

    void start();
    default void subscribe() {
    }

    default void init() {
    }

    default void execute() {}


}
