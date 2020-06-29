package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.services.DTO.DTO;
import lombok.Data;

import java.util.concurrent.ConcurrentLinkedDeque;


@Data
public class StackEvent {
    private ConcurrentLinkedDeque<DTO> heroDTOS;
    private static StackEvent INSTANCE;

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
