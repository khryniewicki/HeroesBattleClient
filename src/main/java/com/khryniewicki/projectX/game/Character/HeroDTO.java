package com.khryniewicki.projectX.game.Character;

import lombok.Data;

@Data
public class HeroDTO {
    private String character="KNIGHT";
    private Integer life=100;
    private Integer mana=100;
    private Float positionX;
    private Float positionY;

    public HeroDTO(Float positionX, Float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public HeroDTO() {
    }
}
