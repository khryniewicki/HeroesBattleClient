package com.khryniewicki.projectX.game.user_interface.playerBar;

import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.GameSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.textures.GameTextures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerBar implements Symbol {

    private List<Symbol> symbolList;

    public PlayerBar() {
        UltraHero hero = HeroesInstances.getInstance().getHero();
        Symbol heroName = new GameSymbol.Builder("Hero Name",TextFactory.textInPlayerBarHeroName(hero.getName(), Color.WHITE), -9.8f, 5.15f)
                .withWidth(2.4f)
                .withHeight(0.4f)
                .build();
        Symbol manaSymbol = new GameSymbol.Builder("MANA SYMBOL",GameTextures.MANASYMBOL, -5.8f, 5.2f).build();
        Symbol lifeSymbol = new GameSymbol.Builder("LIFE SYMBOL",GameTextures.LIFESYMBOL, -7.3f, 5.2f).build();

        Symbol lifeAsNumber = new DigitsSymbol("life");
        Symbol manaAsNumber = new DigitsSymbol("mana");

        Symbol basicSpell = new SpellSymbol(hero.getBasicSpell());
        Symbol ultimateSpell = new SpellSymbol(hero.getUltimateSpell());
        symbolList = new ArrayList<>(Arrays.asList(heroName,manaSymbol, lifeSymbol, lifeAsNumber, manaAsNumber, basicSpell, ultimateSpell));
    }

    @Override
    public void update() {
        symbolList.forEach(Symbol::update);
    }

    @Override
    public void render() {
        symbolList.forEach(Symbol::render);
    }

    @Override
    public void reload() {
        this.symbolList.forEach(Symbol::reload);
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        this.symbolList.forEach(symbol->sb.append(symbol.getName()).append("\n"));
        return sb.toString();
    }
}
