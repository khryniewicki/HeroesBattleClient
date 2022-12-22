package com.khryniewicki.projectX.game.ui.symbols;

import com.khryniewicki.projectX.game.ui.subjects.Listener;
import com.khryniewicki.projectX.game.ui.subjects.Subject;
import com.khryniewicki.projectX.game.ui.subjects.SubjectHeroName;
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
    private String name;
    private Subject subject;

    public MenuSymbol(Builder builder) {
        super(builder);
        this.name = builder.name;
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
