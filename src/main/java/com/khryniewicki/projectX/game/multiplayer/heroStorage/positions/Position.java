package com.khryniewicki.projectX.game.multiplayer.heroStorage.positions;

import lombok.Data;

@Data
public class Position {
    private Float positionX;
    private Float positionY;

    public Position(Float positionX, Float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
