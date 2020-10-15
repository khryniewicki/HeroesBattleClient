package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;

public interface Menu extends Symbol {
    void addEventClick();
    void swapBuffers();
    void changeButton(Button button, ButtonTransferObject buttonTransferObject);
    void subscribe();
    default void init(){}
}
