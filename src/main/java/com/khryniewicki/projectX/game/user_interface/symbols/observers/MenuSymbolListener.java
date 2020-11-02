package com.khryniewicki.projectX.game.user_interface.symbols.observers;

import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;

public interface MenuSymbolListener extends Symbol, Listener {

    default void setAction(String text){};
}
