package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.textures.GameTextures;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class HeroAttributes implements Symbol {
    List<Symbol> symbols;
    LifeBar lifeBar;
    ManaBar manaBar;
    PlayerNameBar playerNameBar;

    public HeroAttributes(SuperHero superHero, String name) {
        lifeBar = initLifeBar(superHero);
        manaBar = initManaBar(superHero);
        playerNameBar = initPlayerNameBar(superHero, name);
        symbols = new ArrayList<>(Arrays.asList(lifeBar, playerNameBar, manaBar));

    }

    private ManaBar initManaBar(UltraHero ultraHero) {
        return new ManaBar.Builder("Mana Bar")
                .withHero(ultraHero)
                .withHeight(0.07f)
                .withWidth(0.65f)
                .withVisibility(0.8f)
                .withMaxMana(ultraHero.getMana())
                .withBlueBarTexture(GameTextures.MANA)
                .withBlackBarTexture(GameTextures.EMPTY)
                .build();
    }

    private LifeBar initLifeBar(UltraHero ultraHero) {
        return new LifeBar.Builder("Life Bar")
                .withHero(ultraHero)
                .withMaxLife(ultraHero.getLife())
                .withHeight(0.07f)
                .withWidth(0.65f)
                .withVisibility(0.8f)
                .withGreenBarTexture(GameTextures.LIFE)
                .withBlackBarTexture(GameTextures.EMPTY)
                .build();
    }

    private PlayerNameBar initPlayerNameBar(UltraHero ultraHero, String name) {
        return new PlayerNameBar.Builder("PlayerNameBar")
                .withHero(ultraHero)
                .withHeight(0.5f)
                .withWidth(2f)
                .withVisibility(0.8f)
                .withTexture(TextFactory.textToPlayerName(name))
                .build();
    }

    @Override
    public void update() {
        symbols.forEach(Symbol::update);
    }

    @Override
    public void render() {
        symbols.forEach(Symbol::render);
    }

    @Override
    public void reload() {
        this.symbols.forEach(Symbol::reload);
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        this.symbols.forEach(symbol -> sb.append(symbol.getName()).append("\n"));
        return sb.toString();
    }
}
