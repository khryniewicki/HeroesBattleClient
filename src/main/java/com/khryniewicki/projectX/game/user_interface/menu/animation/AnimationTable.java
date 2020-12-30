package com.khryniewicki.projectX.game.user_interface.menu.animation;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TableFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;

import java.util.List;
import java.util.stream.Collectors;

public class AnimationTable extends AnimationObject {
    public AnimationTable(MenuSymbol symbol) {
        super(symbol);
    }

    public List<Symbol> update(SuperHero superHero, int spell_instance_number) {
        return animationSymbols.stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        symbol.setTexture(texture_from_table_factory(superHero, spell_instance_number));
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Symbol> toggle(boolean disabled) {
        return animationSymbols.stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        symbol.setDisabled(disabled);
                    }
                })
                .collect(Collectors.toList());
    }

    private Texture texture_from_table_factory(SuperHero superHero, int spell_instance_number) {
        return TableFactory.tableImage(superHero.getName(), spell_instance_number);
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
