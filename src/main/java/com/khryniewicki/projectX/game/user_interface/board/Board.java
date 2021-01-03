package com.khryniewicki.projectX.game.user_interface.board;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.control_settings.collision.Collision;
import com.khryniewicki.projectX.game.control_settings.mouse_settings.MousePosition;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.properties.Ultra;
import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.playerBar.PlayerBar;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.utils.ObstacleStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.khryniewicki.projectX.game.user_interface.board.GameFactory.BACKGROUND;
import static com.khryniewicki.projectX.game.user_interface.board.GameFactory.BAR;

@Data
@Slf4j
public class Board {

    public static Collision collision;
    private SuperHero hero;
    private UltraHero mock;
    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;
    private UltraSpell basicSpellMock;
    private UltraSpell ultimateSpellMock;
    private MousePosition mousePosition;

    private List<Symbol> symbols;
    private List<UltraSpell> spells;
    private List<UltraHero> heroes;
    private boolean notify;

    public Board() {
        createBackground();
        createHeroes();
        createSpells();
    }

    private void createBackground() {
        symbols = new ArrayList<>(Arrays.asList(BACKGROUND, BAR, new PlayerBar()));
    }

    private void createHeroes() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        hero = heroesInstances.getHero();
        mock = heroesInstances.getMock();
        heroes = new ArrayList<>(Arrays.asList(hero, mock));
    }

    private void createSpells() {
        basicSpell = hero.getBasicSpell();
        ultimateSpell = hero.getUltimateSpell();
        basicSpellMock = mock.getBasicSpell();
        ultimateSpellMock = mock.getUltimateSpell();
        spells = new ArrayList<>(Arrays.asList(basicSpell, basicSpellMock, ultimateSpell, ultimateSpellMock));
    }

    public void update() {
        heroes.forEach(Ultra::update);
        spells.forEach(Ultra::update);
        symbols.forEach(Symbol::update);
    }

    public void render() {
        symbols.forEach(Symbol::render);
        heroes.forEach(Ultra::render);
        spells.forEach(spell -> {
            if (spell.isSpellActivated()) {
                spell.render();
            }
        });

    }

    public boolean player_dead() {
        return heroes.stream().anyMatch(ultraHero -> ultraHero.getLife() == 0);
    }

}

