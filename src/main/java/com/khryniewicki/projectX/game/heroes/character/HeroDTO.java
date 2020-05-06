package com.khryniewicki.projectX.game.heroes.character;

import lombok.Data;

@Data
public class HeroDTO {
    private String name;
    private Integer life=100;
    private Integer mana=100;
    private Float positionX;
    private Float positionY;

    public HeroDTO(Float positionX, Float positionY) {
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
}
