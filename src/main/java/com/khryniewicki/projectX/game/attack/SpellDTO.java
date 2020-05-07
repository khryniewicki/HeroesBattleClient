package com.khryniewicki.projectX.game.attack;

import lombok.Data;

@Data
public class SpellDTO {
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
}
