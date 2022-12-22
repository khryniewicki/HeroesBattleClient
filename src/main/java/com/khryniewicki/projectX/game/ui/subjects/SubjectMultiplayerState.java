package com.khryniewicki.projectX.game.ui.subjects;

import com.khryniewicki.projectX.game.multiplayer.websocket.states.MultiplayerState;

import static com.khryniewicki.projectX.game.ui.subjects.Subjects.MULTIPLAYER;

public class SubjectMultiplayerState extends Subject {

    public SubjectMultiplayerState() {
        super();
    }

    @Override
    public void setNews(Object news) {
        MultiplayerState news1 = (MultiplayerState) news;
        support.firePropertyChange(MULTIPLAYER.getName(), null, news1);
    }
}
