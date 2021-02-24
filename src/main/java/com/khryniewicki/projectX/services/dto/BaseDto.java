package com.khryniewicki.projectX.services.dto;

import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;

public interface BaseDto {
    BaseDtoType getType();

    default ConnectionState getStatus() {
        return ConnectionState.CONNECTED;
    }
}
