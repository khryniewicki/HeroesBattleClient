package com.khryniewicki.projectX.game.user_interface.symbols.observers;

public enum Subjects {
    SERVER("server"),TIME_TO_LOG_OUT("timeLeftToLogOut"),MULTIPLAYER("multiplayer");

    private String name;

    Subjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

