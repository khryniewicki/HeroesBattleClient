package com.khryniewicki.projectX.game.multiplayer.websocket;

public enum ServerState {
    START("start"),NO_PLAYERS("Players online: 0 / 2"), ONE_PLAYER("Players online: 1 / 2"),
    TWO_PLAYERS("Players online: 2 / 2"), SERVER_OFFLINE("Server offline"), JOIN_GAME("join");

    private String title;

    ServerState(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
