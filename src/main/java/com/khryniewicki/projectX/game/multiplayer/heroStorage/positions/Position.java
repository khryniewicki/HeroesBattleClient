package com.khryniewicki.projectX.game.multiplayer.heroStorage.positions;

import lombok.Data;

@Data
public class Position {
    private Float positionX;
    private Float positionY;
    private Double positionXD;
    private Double positionYD;

    public Position(Float positionX, Float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Position(Double positionXD, Double positionYD) {
        this.positionXD = positionXD;
        this.positionYD = positionYD;
    }
}
