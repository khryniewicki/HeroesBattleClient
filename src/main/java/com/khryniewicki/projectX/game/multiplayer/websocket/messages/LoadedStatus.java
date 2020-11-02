package com.khryniewicki.projectX.game.multiplayer.websocket.messages;

import lombok.Data;

@Data
public class LoadedStatus {
    public boolean isHeroLoaded;

    private LoadedStatus() {
    }

    private final static LoadedStatus STATUS = new LoadedStatus();

    public static LoadedStatus INSTANCE() {
        return STATUS;
    }
}
