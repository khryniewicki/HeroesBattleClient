package com.khryniewicki.projectX.game.user_interface.menu.buttons;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import lombok.Data;

@Data
public class ButtonTransferObject {
    private String name;
    private Position position;

    public ButtonTransferObject(String name, Position position) {
        this.name = name;
        this.position = position;
    }
}
