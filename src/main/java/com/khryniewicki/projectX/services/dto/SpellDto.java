package com.khryniewicki.projectX.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SpellDto implements Serializable, BaseDto {
    private String name;
    private Float targetSpellX;
    private Float targetSpellY;
    private String sessionId;
    private BaseDtoType type;

    public SpellDto(String name, Float targetSpellX, Float targetSpellY) {
        this.name = name;
        this.targetSpellX = targetSpellX;
        this.targetSpellY = targetSpellY;
        this.type = BaseDtoType.SPELL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpellDto spellDto = (SpellDto) o;
        return Objects.equals(name, spellDto.name) &&
                Objects.equals(targetSpellX, spellDto.targetSpellX) &&
                Objects.equals(targetSpellY, spellDto.targetSpellY) &&
                type == spellDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, targetSpellX, targetSpellY, type);
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


}
