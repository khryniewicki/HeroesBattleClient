package com.khryniewicki.projectX.services.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SpellDTO implements Serializable,DTO {
    private String name;
    private Float targetSpellX;
    private Float targetSpellY;

    public SpellDTO(String name, Float targetSpellX, Float targetSpellY) {
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
