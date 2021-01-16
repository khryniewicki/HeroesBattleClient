package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.LifeCycle;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;

@Component
public interface Menu extends PropertyChangeListener, LifeCycle {
    void addEventClick();
    void start();
    void subscribe();

}
