package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;

import java.beans.PropertyChangeListener;

public interface MenuSymbolListener extends Symbol {
    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

    void setNews(ButtonTransferObject value);
    default void setAction(String text){};
}
