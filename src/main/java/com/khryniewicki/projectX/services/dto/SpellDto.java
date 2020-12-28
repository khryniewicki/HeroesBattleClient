package com.khryniewicki.projectX.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SpellDto implements Serializable, BaseDto {
    private String name;
    private Float targetSpellX;
    private Float targetSpellY;
    private String sessionId;

    public SpellDto(String name, Float targetSpellX, Float targetSpellY) {
        this.name = name;
        this.targetSpellX = targetSpellX;
        this.targetSpellY = targetSpellY;
    }

    @Override
    public String toString() {
        return "SpellDTO{" +
                "name='" + name + '\'' +
                ", targetSpellX=" + targetSpellX +
                ", targetSpellY=" + targetSpellY +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    @Override
    public Float getPositionX() {
        return targetSpellX;
    }

    @Override
    public Float getPositionY() {
        return targetSpellY;
    }

    @Override
    public boolean isSpellDTO() {
        return true;
    }
}
