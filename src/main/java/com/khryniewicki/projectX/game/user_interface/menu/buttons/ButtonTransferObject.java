package com.khryniewicki.projectX.game.user_interface.menu.buttons;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import lombok.Data;

import java.util.Objects;

@Data
public class ButtonTransferObject {
    private String name;
    private Position position;

    public ButtonTransferObject(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ButtonTransferObject that = (ButtonTransferObject) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
