package com.khryniewicki.projectX.game.ui.playerBar;

import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextFactory;
import com.khryniewicki.projectX.game.ui.symbols.GameSymbol;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;

import java.util.List;

import static com.khryniewicki.projectX.graphics.textures.GameTextures.LIFESYMBOL;
import static com.khryniewicki.projectX.graphics.textures.GameTextures.MANASYMBOL;
import static java.awt.Color.WHITE;
import static java.util.Arrays.asList;

public class PlayerBar implements Symbol {

    private List<Symbol> symbolList;

    public PlayerBar() {
        UltraHero hero = HeroesInstances.getInstance().getHero();
        Symbol heroName = new GameSymbol.Builder(TextFactory.textInPlayerBarHeroName(hero.getName(), WHITE), -9.8f, 5.15f)
                .withWidth(2.4f)
                .withHeight(0.4f)
                .build();
        Symbol manaSymbol = new GameSymbol.Builder(MANASYMBOL, -5.8f, 5.2f).build();
        Symbol lifeSymbol = new GameSymbol.Builder(LIFESYMBOL, -7.3f, 5.2f).build();

        Symbol lifeAsNumber = new DigitsSymbol("life");
        Symbol manaAsNumber = new DigitsSymbol("mana");

        Symbol basicSpell = new SpellSymbol(hero.getBasicSpell());
        Symbol ultimateSpell = new SpellSymbol(hero.getUltimateSpell());
        symbolList = asList(heroName, manaSymbol, lifeAsNumber, manaAsNumber, lifeSymbol, basicSpell, ultimateSpell);
    }

    @Override
    public void update() {
        symbolList.forEach(Symbol::update);
    }

    @Override
    public void render() {
        symbolList.forEach(Symbol::render);
    }
}
