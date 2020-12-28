package com.khryniewicki.projectX.services.sending_service;

import com.khryniewicki.projectX.services.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentLinkedDeque;


@Getter
@Setter
public class StackEvent {
    private ConcurrentLinkedDeque<BaseDto> events;

    private static StackEvent INSTANCE;

    private StackEventSupport stackEventSupport;

    public void addDto(BaseDto baseDto) {
        events.add(baseDto);
    }

    public void addHeroDto() {
        addDto(stackEventSupport.addHeroDto());
    }

    private StackEvent() {
        if (INSTANCE == null) {
            INSTANCE = this;
            stackEventSupport = new StackEventSupport();
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
