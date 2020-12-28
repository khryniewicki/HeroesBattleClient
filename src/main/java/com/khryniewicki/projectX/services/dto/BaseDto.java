package com.khryniewicki.projectX.services.dto;

public interface BaseDto {
    Float getPositionX();
    Float getPositionY();
    boolean isSpellDTO();
    void setSessionId(String sessionId);
}
