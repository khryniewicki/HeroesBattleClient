package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import lombok.Data;

@Data
public class DoublePosition {
 private Double X;
 private Double Y;

    public DoublePosition(Double x, Double y) {
        X = x;
        Y = y;
    }
}
