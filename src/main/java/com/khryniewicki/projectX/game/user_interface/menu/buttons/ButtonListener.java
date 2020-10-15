package com.khryniewicki.projectX.game.user_interface.menu.buttons;

import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;

import java.beans.PropertyChangeListener;

public interface ButtonListener extends Symbol {
    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

    void setNews(ButtonTransferObject value);
}
