package com.khryniewicki.projectX.game.board.playerBar.symbols;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.utils.GameUtill;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpellSymbol {
    private final SpellInstance spellInstance;
    private final UltraSpell ultraSpell;
    private final SymbolImp frame;
    private final SymbolImp spellTexture;
    private Position spellPositionPlayerBar;


    public SpellSymbol(UltraSpell spell) {
        this.ultraSpell = spell;
        this.spellInstance = spell.getSpellInstance();
        getPositionPlayerBar(spellInstance.isBasic());

        this.frame = new SymbolImp.Builder(GameUtill.frame, spellPositionPlayerBar.getX(), spellPositionPlayerBar.getY())
                .withWidth(0.62f)
                .withHeight(0.465f)
                .withVisibility(0f)
                .build();

        this.spellTexture = new SymbolImp.Builder(spellInstance.getIcon(), 0.1f + spellPositionPlayerBar.getX(), 0.03f + spellPositionPlayerBar.getY())
                .withWidth(0.45f)
                .withHeight(0.40f)
                .withVisibility(1f)
                .build();
    }

    public void render() {
        isFaded();
        frame.render();
        spellTexture.render();
    }

    private void isFaded() {
        if (ultraSpell.isSpellActivated()) {
            spellTexture.setTexture(spellInstance.getFadedIcon());
        } else
            spellTexture.setTexture(spellInstance.getIcon());

    }


    public void getPositionPlayerBar(boolean isBasic) {
        float Y = 5.12f;
        if (isBasic) {
            spellPositionPlayerBar = new Position(-4f, Y);
        } else {
            spellPositionPlayerBar = new Position(-3f, Y);
        }
    }
}

