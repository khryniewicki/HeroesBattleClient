package com.khryniewicki.projectX.game.ui.playerBar;

import com.khryniewicki.projectX.game.attack.spell.instance.SpellInstance;
import com.khryniewicki.projectX.game.attack.spell.settings.UltraSpell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import com.khryniewicki.projectX.game.ui.symbols.GameSymbol;
import com.khryniewicki.projectX.graphics.textures.GameTextures;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@Slf4j
public class SpellSymbol implements Symbol {
    private final SpellInstance spellInstance;
    private final UltraSpell ultraSpell;
    private final GameSymbol spellTexture;
    private List<Symbol> symbolList;
    private Position spellPositionPlayerBar;
    private boolean isSpellActivated;

    public SpellSymbol(UltraSpell spell) {
        this.ultraSpell = spell;
        this.spellInstance = spell.getSpellInstance();
        float offset = 0.03f;
        getPositionPlayerBar(spellInstance.isBasic());

        GameSymbol frame = new GameSymbol.Builder(GameTextures.FRAME, spellPositionPlayerBar.getX(), spellPositionPlayerBar.getY())
                .withWidth(0.62f)
                .withHeight(0.465f)
                .withVisibility(0.95f)
                .build();

        this.spellTexture = new GameSymbol.Builder(spellInstance.getIcon(), offset + spellPositionPlayerBar.getX(), offset + spellPositionPlayerBar.getY())
                .withWidth(0.56f)
                .withHeight(0.40f)
                .build();
        symbolList = asList(frame, this.spellTexture);
    }

    @Override
    public void update() {
        isFaded();
    }

    @Override
    public void render() {
        symbolList.forEach(Symbol::render);
    }

    private void isFaded() {
        if (ultraSpell.isSpellActivated() != isSpellActivated) {
            isSpellActivated = ultraSpell.isSpellActivated();
            spellTexture.setTexture(isSpellActivated ? spellInstance.getFadedIcon() : spellInstance.getIcon());
        }
    }

    public void getPositionPlayerBar(boolean isBasic) {
        spellPositionPlayerBar = new Position(isBasic ? -4f : -3f, 5.12f);
    }
}

