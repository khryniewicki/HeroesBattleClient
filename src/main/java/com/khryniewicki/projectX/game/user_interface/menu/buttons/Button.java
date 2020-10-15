package com.khryniewicki.projectX.game.user_interface.menu.buttons;

import com.khryniewicki.projectX.game.multiplayer.renderer.GraphicLoader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Getter
@Setter
@Slf4j
public class Button extends GraphicLoader implements ButtonListener {
    private PropertyChangeSupport support;
    private String message;
    private String name;
    private float positionX0;
    private float positionX1;
    private float positionY0;
    private float positionY1;
    private ButtonTransferObject buttonTransferObject;

    protected Button(Builder builder) {
        super(builder);
        this.name = builder.name;
        setButtonPositions();
        support = new PropertyChangeSupport(this);
    }

    private void setButtonPositions() {
        positionX0 = getPositionX();
        positionX1 = getPositionX() + getWidth();
        positionY0 = getPositionY() - getHeight() / 2;
        positionY1 = getPositionY() + getHeight() / 2;
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
        support.firePropertyChange("news", this.buttonTransferObject, value);
        this.buttonTransferObject = value;
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

        public Button build() {
            return new Button(this);
        }
    }


}
