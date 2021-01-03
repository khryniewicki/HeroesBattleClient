package com.khryniewicki.projectX.game.user_interface.subjects;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Buttons;

import static com.khryniewicki.projectX.game.user_interface.subjects.Subjects.BUTTONS;

public class SubjectButton extends Subject {

    public SubjectButton() {
        super();
    }

    @Override
    public void setNews(Object news) {
        Buttons button = (Buttons) news;
        support.firePropertyChange(BUTTONS.getName(), null, button);
    }
}
