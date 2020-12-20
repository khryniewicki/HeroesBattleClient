package com.khryniewicki.projectX.game.user_interface.symbols.observers;

import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Subject implements Listener {

    private PropertyChangeSupport support;

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
    public void setNews(Object news) {
        MultiplayerState news1 = (MultiplayerState) news;
        support.firePropertyChange("multiplayer", null, news1);
    }
}
