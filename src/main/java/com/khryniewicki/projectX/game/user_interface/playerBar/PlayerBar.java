package com.khryniewicki.projectX.game.user_interface.playerBar;

import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.symbols.GameSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.utils.GameUtill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerBar implements Symbol {

    private List<Symbol> symbolList;

    public PlayerBar() {
        UltraHero hero = HeroesInstances.getInstance().getHero();
        Symbol manaSymbol = new GameSymbol.Builder(GameUtill.MANASYMBOL, -6.3f, 5.2f).build();
        Symbol lifeSymbol = new GameSymbol.Builder(GameUtill.LIFESYMBOL, -7.8f, 5.2f).build();

        Symbol lifeAsNumber = new DigitsSymbol("life");
        Symbol manaAsNumber = new DigitsSymbol("mana");

        Symbol basicSpell = new SpellSymbol(hero.getBasicSpell());
        Symbol ultimateSpell = new SpellSymbol(hero.getUltimateSpell());
        symbolList = new ArrayList<>(Arrays.asList(manaSymbol, lifeSymbol, lifeAsNumber, manaAsNumber, basicSpell, ultimateSpell));
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
