package com.khryniewicki.projectX.game.ui.menu.menus;

import com.khryniewicki.projectX.game.engine.LifeCycle;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;

@Component
public interface Menu extends PropertyChangeListener, LifeCycle {


    void addEventClick();
    void start();
    void subscribe();

}
