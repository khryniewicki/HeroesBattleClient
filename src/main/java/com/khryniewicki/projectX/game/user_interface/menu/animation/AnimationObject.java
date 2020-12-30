package com.khryniewicki.projectX.game.user_interface.menu.animation;

import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AnimationObject {
    protected final MenuSymbol  symbol;

    public AnimationObject(MenuSymbol symbol) {
        this.symbol = symbol;
    }

    protected void turnLeft(boolean b) {
        symbol.setTurningLeft(b);
        updateMesh();
    }
    protected void setTexture(Texture texture){
        symbol.setTexture(texture);
    }
    protected void setSIZE(float SIZE){
        symbol.setSIZE(SIZE);
    }
    protected void updateMesh() {
        symbol.updateMesh();
    }
    protected void setPosition(float v) {
        symbol.setPositionX(v);
    }

    protected boolean isTurningLeft() {
        return symbol.isTurningLeft();
    }

    protected abstract Float getPositionX();
    protected abstract Float getPositionY();

}
