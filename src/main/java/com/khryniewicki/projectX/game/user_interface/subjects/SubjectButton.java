package com.khryniewicki.projectX.game.user_interface.subjects;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonsFactory;

import static com.khryniewicki.projectX.game.user_interface.subjects.Subjects.BUTTONS;

public class SubjectButton extends Subject {

    public SubjectButton() {
        super();
    }

    @Override
    public void setNews(Object news) {
        ButtonsFactory button = (ButtonsFactory) news;
        support.firePropertyChange(BUTTONS.getName(), null, button);
    }
}
