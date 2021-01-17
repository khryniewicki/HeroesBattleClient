package com.khryniewicki.projectX.game.user_interface.symbols;

import com.khryniewicki.projectX.game.user_interface.subjects.Listener;
import com.khryniewicki.projectX.game.user_interface.subjects.Subject;
import com.khryniewicki.projectX.game.user_interface.subjects.SubjectHeroName;
import com.khryniewicki.projectX.graphics.GraphicLoader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeListener;

@Getter
@Setter
@Slf4j
public class MenuSymbol extends GraphicLoader implements Listener {

    private boolean disabled;

    private Subject subject;

    public MenuSymbol(Builder builder) {
        super(builder);
        this.disabled = builder.disabled;
        subject = new SubjectHeroName();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        subject.removePropertyChangeListener(pcl);
    }

    @Override
    public void setNews(Object text) {
        subject.setNews(text);
    }

    @Override
    public void render() {
        super.render();
    }

    public static class Builder extends GraphicLoader.Builder<Builder> {

        private boolean disabled;


        public Builder withDisabled(Boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public Builder(String name) {
            super(name);
        }

        public MenuSymbol build() {
            return new MenuSymbol(this);
        }
    }
}
