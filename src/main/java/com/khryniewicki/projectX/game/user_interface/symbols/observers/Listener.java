package com.khryniewicki.projectX.game.user_interface.symbols.observers;

import java.beans.PropertyChangeListener;

public interface Listener {
    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

    void setNews(String news);
}
