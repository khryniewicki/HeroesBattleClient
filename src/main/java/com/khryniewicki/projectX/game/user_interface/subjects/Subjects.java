package com.khryniewicki.projectX.game.user_interface.subjects;

public enum Subjects {
    SERVER("server"),TIME_TO_LOG_OUT("timeLeftToLogOut"),MULTIPLAYER("multiplayer"),
    BUTTONS("news"), HERO_NAME("heroName");

    private String name;

    Subjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

