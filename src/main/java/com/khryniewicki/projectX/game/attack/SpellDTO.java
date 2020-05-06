package com.khryniewicki.projectX.game.attack;

import lombok.Data;

@Data
public class SpellDTO {
    private String name="ICEBERG";
    private Float targetSpellX=1f;
    private Float targetSpellY=1f;

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
