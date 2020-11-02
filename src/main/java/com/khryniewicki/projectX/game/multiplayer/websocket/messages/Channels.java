package com.khryniewicki.projectX.game.multiplayer.websocket.messages;

import lombok.Data;

@Data
public class Channels {
    private Integer app;
    private Integer topic;


    private Channels() {
    }

    private final static Channels INSTANCE=new Channels();

    public static Channels getINSTANCE() {
        return INSTANCE;
    }
}
