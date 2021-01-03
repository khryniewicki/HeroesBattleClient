package com.khryniewicki.projectX.game.user_interface.subjects;

import static com.khryniewicki.projectX.game.user_interface.subjects.Subjects.HERO_NAME;

public class SubjectHeroName extends Subject {
    private String oldText;

    public SubjectHeroName() {
        super();
    }

    @Override
    public void setNews(Object text) {
        String text1 = (String) text;
        support.firePropertyChange(HERO_NAME.getName(), oldText, text1);
        oldText = text1;
    }
}
