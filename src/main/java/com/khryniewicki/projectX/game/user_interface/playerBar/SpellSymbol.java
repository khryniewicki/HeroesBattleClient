package com.khryniewicki.projectX.game.user_interface.playerBar;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.game.user_interface.symbols.SymbolImp;
import com.khryniewicki.projectX.utils.GameUtill;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class SpellSymbol implements Symbol {
    private final SpellInstance spellInstance;
    private final UltraSpell ultraSpell;
    private final SymbolImp spellTexture;
    private List<Symbol> symbolList;
    private Position spellPositionPlayerBar;


    public SpellSymbol(UltraSpell spell) {
        this.ultraSpell = spell;
        this.spellInstance = spell.getSpellInstance();
        float offset = 0.03f;
        getPositionPlayerBar(spellInstance.isBasic());

        SymbolImp frame = new SymbolImp.Builder(GameUtill.FRAME, spellPositionPlayerBar.getX(), spellPositionPlayerBar.getY())
                .withWidth(0.62f)
                .withHeight(0.465f)
                .withVisibility(0.95f)
                .build();

        this.spellTexture = new SymbolImp.Builder(spellInstance.getIcon(), offset + spellPositionPlayerBar.getX(), offset + spellPositionPlayerBar.getY())
                .withWidth(0.56f)
                .withHeight(0.40f)
                .build();
        symbolList = new ArrayList<>(Arrays.asList(frame, this.spellTexture));
    }


    @Override
    public void render() {
        isFaded();
        symbolList.forEach(Symbol::render);
    }

    private void isFaded() {
        spellTexture.setTexture(ultraSpell.isSpellActivated() ? spellInstance.getFadedIcon() : spellInstance.getIcon());
    }

    public void getPositionPlayerBar(boolean isBasic) {
        spellPositionPlayerBar = new Position(isBasic ? -4f : -3f, 5.12f);
    }
}

