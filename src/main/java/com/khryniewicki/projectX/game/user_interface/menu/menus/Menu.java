package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import org.springframework.stereotype.Component;

@Component
public interface Menu extends Symbol {
    void addEventClick();
    void start();
    default void subscribe() {
    }

    default void init() {
    }

    default void execute() {}


}
