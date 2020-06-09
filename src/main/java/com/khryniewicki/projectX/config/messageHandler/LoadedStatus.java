package com.khryniewicki.projectX.config.messageHandler;

import lombok.Data;

@Data
public class LoadedStatus {
    public boolean HeroLoadedProperly;

    private LoadedStatus() {
    }

    private final static LoadedStatus STATUS = new LoadedStatus();

    public static LoadedStatus INSTANCE() {
        return STATUS;
    }
}
