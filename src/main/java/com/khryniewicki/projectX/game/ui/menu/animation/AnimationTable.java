package com.khryniewicki.projectX.game.ui.menu.animation;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.TableFactory;
import com.khryniewicki.projectX.game.ui.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;

import java.util.List;
import java.util.stream.Collectors;

public class AnimationTable extends AnimationObject {
    public AnimationTable(MenuSymbol symbol) {
        super(symbol);
    }

    public List<Symbol> update(SuperHero superHero, int spellInstanceNumber) {
        initCharacterMenu();
        return characterMenu.getAnimationSymbols().stream()
                .peek(menuSymbol -> {
                    if (is(menuSymbol)) {
                        symbol.setTexture(textureFromTableFactory(superHero, spellInstanceNumber));
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Symbol> toggle(boolean disabled) {
        initCharacterMenu();
        return characterMenu.getAnimationSymbols().stream()
                .peek(menuSymbol -> {
                    if (is(menuSymbol)) {
                        symbol.setDisabled(disabled);
                    }
                })
                .collect(Collectors.toList());
    }

    private boolean is(Symbol menuSymbol) {
        return menuSymbol.getName().equals(symbol.getName());
    }

    private Texture textureFromTableFactory(SuperHero superHero, int spellInstanceNumber) {
        return TableFactory.tableImage(superHero.getName(), spellInstanceNumber);
    }

    @Override
    protected Float getPositionX() {
        return null;
    }

    @Override
    protected Float getPositionY() {
        return null;
    }
}
