package com.khryniewicki.projectX.game.user_interface.symbols;

import java.beans.PropertyChangeListener;

public interface MenuSymbolListener extends Symbol {
    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

    void setNews(String news);
    default void setAction(String text){};
}
