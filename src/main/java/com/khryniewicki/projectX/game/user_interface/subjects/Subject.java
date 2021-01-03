package com.khryniewicki.projectX.game.user_interface.subjects;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Subject implements Listener {

    protected PropertyChangeSupport support;

    public Subject() {
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    @Override
    public abstract void setNews(Object news);
}
