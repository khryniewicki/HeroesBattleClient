package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.services.DTO.DTO;
import lombok.Data;

import java.util.concurrent.ConcurrentLinkedDeque;


@Data
public class StackEvent {
    private ConcurrentLinkedDeque<DTO> events;
    private static StackEvent INSTANCE;
    private boolean hasAction;
    private boolean isBasicSpellActivated = true;
    private boolean isUltimateSpellActivated = true;

    public boolean hasAction() {
        return hasAction;
    }

    public void setHasAction(boolean hasAction) {
        this.hasAction = hasAction;
    }

    private StackEvent() {
        if (INSTANCE == null) {
            INSTANCE = this;
        } else
            throw new IllegalArgumentException();
    }

    public static synchronized StackEvent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StackEvent();
        }
        return INSTANCE;
    }
}
