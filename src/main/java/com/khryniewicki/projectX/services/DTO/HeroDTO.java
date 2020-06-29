package com.khryniewicki.projectX.services.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeroDTO implements Serializable,DTO {
    private String name;
    private Integer life;
    private Integer mana;
    private Float positionX;
    private Float positionY;

    public HeroDTO(Float positionX, Float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public HeroDTO(String name, Integer life, Integer mana, Float positionX, Float positionY) {
        this.name = name;
        this.life = life;
        this.mana = mana;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public HeroDTO(String name, Float positionX, Float positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public HeroDTO() {
    }

    @Override
    public Boolean isSpellDTO() {
        return false;
    }
}
