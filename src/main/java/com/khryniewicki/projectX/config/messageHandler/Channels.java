package com.khryniewicki.projectX.config.messageHandler;

import lombok.Data;

@Data
public class Channels {
    private Integer app=1;
    private Integer topic=2;


    private Channels() {
    }

    private final static Channels INSTANCE=new Channels();

    public static Channels getINSTANCE() {
        return INSTANCE;
    }
}
