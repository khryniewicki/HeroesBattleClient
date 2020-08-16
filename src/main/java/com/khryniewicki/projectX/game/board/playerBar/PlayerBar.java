package com.khryniewicki.projectX.game.board.playerBar;

import com.khryniewicki.projectX.game.board.playerBar.symbols.DigitDisplaySymbol;
import com.khryniewicki.projectX.game.board.playerBar.symbols.SpellSymbol;
import com.khryniewicki.projectX.game.board.playerBar.symbols.SymbolImp;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.utils.GameUtill;

public class PlayerBar {

    private SymbolImp manaSymbol;
    private SymbolImp lifeSymbol;
    private DigitDisplaySymbol lifeAsNumber;
    private DigitDisplaySymbol manaAsNumber;
    private SpellSymbol basicSpell;
    private SpellSymbol ultimateSpell;
    private UltraHero hero;

    public PlayerBar() {

        hero= HeroesInstances.getInstance().getHero();
        manaSymbol = new SymbolImp.Builder(GameUtill.manaSymbol, -6.3f, 5.2f)
                .withVisibility(1.0f)
                .build();
        lifeSymbol = new SymbolImp.Builder(GameUtill.lifeSymbol, -7.8f, 5.2f)
                .withVisibility(1.0f)
                .build();

        lifeAsNumber = new DigitDisplaySymbol("life");
        manaAsNumber = new DigitDisplaySymbol("mana");

        basicSpell=new SpellSymbol(hero.getBasicSpell());
        ultimateSpell=new SpellSymbol(hero.getUltimateSpell());

    }


    public void render() {
        lifeSymbol.render();
        manaSymbol.render();
        lifeAsNumber.render();
        manaAsNumber.render();
        basicSpell.render();
        ultimateSpell.render();
    }


}
