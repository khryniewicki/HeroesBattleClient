package com.khryniewicki.projectX.game.ui.menu.buttons;

import com.khryniewicki.projectX.game.ui.subjects.SubjectButton;
import com.khryniewicki.projectX.game.ui.subjects.Listener;
import com.khryniewicki.projectX.graphics.GraphicLoader;
import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeListener;

@Getter
@Setter
public class Button extends GraphicLoader implements Listener {
    private ButtonsFactory buttonName;
    private float positionX0, positionX1;
    private float positionY0, positionY1;
    private SubjectButton subjectButton;

    public Button(Builder builder) {
        super(builder);
        this.buttonName=builder.buttonName;
        set_positions();
        subjectButton = new SubjectButton();
    }

    private void set_positions() {
        positionX0 = getPositionX();
        positionX1 = positionX0 + getWidth();
        positionY0 = getPositionY();
        positionY1 = positionY0 + getHeight();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subjectButton.addPropertyChangeListener(pcl);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subjectButton.removePropertyChangeListener(pcl);
    }

    @Override
    public void setNews(Object news) {
        subjectButton.setNews(news);
    }

    public static class Builder extends GraphicLoader.Builder<Builder> {
        private ButtonsFactory buttonName;

        public Builder withButtonName(ButtonsFactory button) {
            this.buttonName = button;

            return this;
        }

        public Builder() {
        }

        public Button build() {
            return new Button(this);
        }
    }


}

