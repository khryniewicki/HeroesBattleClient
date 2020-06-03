package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpellDTO implements Serializable {
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
