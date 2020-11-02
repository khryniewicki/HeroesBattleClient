package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.game.user_interface.symbols.observers.MenuSymbolListener;
import com.khryniewicki.projectX.graphics.GraphicLoader;
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
    private String oldText;
    private boolean disabled;
    private String className;
    private PropertyChangeSupport support;

    public MenuSymbol(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.disabled = builder.disabled;
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
    public void setAction(String text) {
        support.firePropertyChange("heroName", oldText, text);
        oldText = text;
    }

    @Override
    public void setNews(String value) {
             support.firePropertyChange("news", null, value);
    }

    @Override
    public void render() {
        super.render();
    }

    public static class Builder extends GraphicLoader.Builder<Builder> {
        private String name;
        private boolean disabled;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDisabled(Boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public Builder() {
        }

        public MenuSymbol build() {
            return new MenuSymbol(this);
        }
    }
}
