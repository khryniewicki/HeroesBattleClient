package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Menu extends com.khryniewicki.projectX.game.user_interface.symbols.Symbol {
    void addEventClick();

    void swapBuffers();

    void start();

    default void subscribe() {
    }

    default void init() {
    }



    void setButtonTransferObject(ButtonTransferObject buttonTransferObject);
}
