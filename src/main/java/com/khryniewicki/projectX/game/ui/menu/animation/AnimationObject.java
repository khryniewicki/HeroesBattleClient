package com.khryniewicki.projectX.game.ui.menu.animation;

import com.khryniewicki.projectX.game.ui.menu.menus.CharacterMenu;
import com.khryniewicki.projectX.game.ui.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter
@Setter
public abstract class AnimationObject {
    protected final MenuSymbol symbol;
    protected CharacterMenu characterMenu;

    public AnimationObject(MenuSymbol symbol) {
        this.symbol = symbol;
    }

    protected void initCharacterMenu() {
        if (isNull(characterMenu)) {
            characterMenu = CharacterMenu.getInstance();
        }
    }

    protected void turnLeft(boolean b) {
        symbol.setTurningLeft(b);
        updateMesh();
    }

    protected void setTexture(Texture texture) {
        symbol.setTexture(texture);
    }

    protected void setSIZE(float SIZE) {
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
