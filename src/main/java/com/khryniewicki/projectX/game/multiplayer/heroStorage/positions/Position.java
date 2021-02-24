package com.khryniewicki.projectX.game.multiplayer.heroStorage.positions;

import lombok.Data;

@Data
public class Position {
    private Float X;
    private Float Y;
    private Double positionXD;
    private Double positionYD;

    public Position(Float X, Float Y) {
        this.X = X;
        this.Y = Y;
    }

    public Position(Double positionXD, Double positionYD) {
        this.positionXD = positionXD;
        this.positionYD = positionYD;
    }
}
