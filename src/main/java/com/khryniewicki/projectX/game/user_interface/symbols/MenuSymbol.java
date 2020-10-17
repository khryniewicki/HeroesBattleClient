package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.game.multiplayer.renderer.GraphicLoader;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Getter
@Setter
@Slf4j
public class MenuSymbol extends GraphicLoader implements MenuSymbolListener {

    private float positionX0;
    private float positionX1;
    private float positionY0;
    private float positionY1;
    private String name;
    private String className;
    private PropertyChangeSupport support;
    private ButtonTransferObject buttonTransferObject;

    public MenuSymbol(Builder builder) {
        super(builder);
        this.name = builder.name;

        setButtonPositions();
        support = new PropertyChangeSupport(this);
    }

    private void setButtonPositions() {
        positionX0 = getPositionX();
        positionX1 = getPositionX() + getWidth();
        positionY0 = getPositionY();
        positionY1 = getPositionY() + getHeight();
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
    public void setNews(ButtonTransferObject value) {
        ButtonTransferObject oldValue = this.buttonTransferObject;
        this.buttonTransferObject = value;
        support.firePropertyChange("news", oldValue, value);
    }

    @Override
    public void render() {
        super.render();
    }

    public static class Builder extends GraphicLoader.Builder<Builder> {
        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder() {
        }

        public MenuSymbol build() {
            return new MenuSymbol(this);
        }
    }
}
