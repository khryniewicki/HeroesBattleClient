package com.khryniewicki.projectX.services.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpellDTO implements Serializable,DTO {
    private String name;
    private Float targetSpellX;
    private Float targetSpellY;

    public SpellDTO(String name, Float targetSpellX, Float targetSpellY) {
        this.name = name;
        this.targetSpellX = targetSpellX;
        this.targetSpellY = targetSpellY;
    }

    public SpellDTO() {
    }

    @Override
    public String toString() {
        return "SpellDTO{" +
                "name='" + name + '\'' +
                ", targetSpellX=" + targetSpellX +
                ", targetSpellY=" + targetSpellY +
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
    public Boolean isSpellDTO() {
        return true;
    }
}
