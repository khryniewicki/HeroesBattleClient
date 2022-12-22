package com.khryniewicki.projectX.game.ui.subjects;

import java.beans.PropertyChangeListener;

public interface Listener {
    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

    void setNews(Object news);
}
