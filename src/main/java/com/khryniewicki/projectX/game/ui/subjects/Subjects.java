package com.khryniewicki.projectX.game.ui.subjects;

public enum Subjects {
    SERVER("server"),TIME_TO_LOG_OUT("timeLeftToLogOut"),MULTIPLAYER("multiplayer"),
    BUTTONS("news"), HERO_NAME("heroName"), VERSION_PROPERTY("version_property");

    private String name;

    Subjects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

