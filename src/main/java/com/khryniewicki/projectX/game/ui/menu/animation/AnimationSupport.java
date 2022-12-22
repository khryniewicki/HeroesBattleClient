package com.khryniewicki.projectX.game.ui.menu.animation;

import com.khryniewicki.projectX.game.engine.LifeCycle;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.ui.menu.menus.CharacterMenu;
import com.khryniewicki.projectX.game.ui.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Getter
@Setter
public abstract class AnimationSupport implements LifeCycle {
    protected boolean running;
    public static final float LEFT_BOUNDARY = -6.5f;
    public static final float RIGHT_BOUNDARY = 3.0f;

    protected boolean spellActive;
    private boolean tmpHeroSide;
    protected int spellingCounter;
    protected int random_number;
    protected int loopCycles;

    protected SuperHero superHero;
    protected HeroFactory heroFactory;
    protected CharacterMenu characterMenu;
    protected Random random;
    protected List<Symbol> symbols;
    protected AnimationHero animationHero;
    protected AnimationDummy dummy;
    protected AnimationSpell spell;
    protected AnimationTable table;

    protected void initCharacterMenu() {
        if (Objects.isNull(characterMenu)) {
            characterMenu = CharacterMenu.getInstance();
        }
    }

    protected void add_dummy() {
        dummy.addDummy(animationHero.getPositionX());
        add_symbol(dummy);
    }

    protected void casting_spell() {
        tmpHeroSide = animationHero.isTurningLeft();
        animationHero.turnLeft(dummy.getPositionX() < 0);
        spell.newSpell(random_number);
        spell.setTarget(new Position(dummy.getPositionX(), dummy.getPositionY()));
        add_symbol(spell);
        setSpellActive(true);
        highlight_table();
    }


    protected void increment_spelling_counter() {
        this.spellingCounter++;
    }

    protected void reset_spelling_counter() {
        this.spellingCounter = 0;
    }

    protected void reset_counting() {
        if (loopCycles == 20) {
            reset_loop_counter();
            setSpellActive(false);
            animationHero.turnLeft(tmpHeroSide);
        }
    }

    protected void reset_loop_counter() {
        this.loopCycles = 0;
    }

    protected void draw_random_number() {
        this.random_number = random.ints(90, 120).findFirst().getAsInt();
    }

    protected void add_symbol(AnimationObject animationObject) {
        add_symbol(animationObject.getSymbol());
    }

    protected void add_symbol(Symbol menuSymbol) {
        symbols = characterMenu.getAnimationSymbols();
        symbols.add(menuSymbol);
    }

    protected void remove_symbol(AnimationObject animationObject) {
        MenuSymbol symbol = animationObject.getSymbol();
        if (Objects.nonNull(symbol)) {
            remove_symbol(symbol);
        }
    }

    protected void remove_symbol(Symbol symbol) {
        if (Objects.nonNull(symbol)) {
            symbols = characterMenu.getAnimationSymbols();
            symbols.removeIf(s -> s.getName().equals(symbol.getName()));
        }
    }

    public void remove_spell_if_exists() {
        remove_symbol(spell);
    }

    protected void reset_table() {
        setSymbols(table.update(superHero, 0));
    }

    protected void highlight_table() {
        setSymbols(table.update(superHero, random_number));
    }

    public void toggle_table(boolean disabled) {
        setSymbols(table.toggle(disabled));
    }


}
