package com.khryniewicki.projectX.game.ui.menu.animation;

import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;

import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory.*;

@Slf4j
@Getter
@Setter
public class Animation extends AnimationSupport {

    public Animation() {
        heroFactory = HeroFactory.getInstance();
        animationHero = new AnimationHero(ANIMATION_HERO);
        dummy = new AnimationDummy(ANIMATION_DUMMY);
        table = new AnimationTable(TABLE);
        random = new Random(331);
        drawRandomNumber();
    }

    public void execute(String hero) {
        prepare(hero);
        insideLoop();
    }

    protected void prepare(String hero) {
        begin();
        initCharacterMenu();
        setSuperHero(heroFactory.create(hero));
        removeSpellIfExists();
        setSpell(new AnimationSpell(superHero, animationHero));
        addSymbol(animationHero);
        addSymbol(table);
        resetTable();
    }

    @Override
    public void update() {
        if (!spellActive) {
            spellAndDummyController();
            heroController();
        } else {
            resetCounting();
        }
        spell.update();
        loopCycles++;
    }

    private void spellAndDummyController() {
        if (this.spellingCounter == (random_number - 60)) {
            addDummy();
            incrementSpellingCounter();
        } else if (this.spellingCounter == (random_number - 30)) {
            animationHero.attack(superHero);
            castingSpell();
            resetLoopCounter();
            incrementSpellingCounter();
        } else if (this.spellingCounter > random_number) {
            remove_symbol(dummy);
            spell.disappear();
            removeSpellIfExists();
            drawRandomNumber();
            resetTable();
            resetSpellingCounter();
            render();
        }
    }

    private void heroController() {
        if (loopCycles == 5) {
            animationHero.walk(superHero);
            incrementSpellingCounter();
        } else if (loopCycles == 9) {
            animationHero.idle(superHero);
            incrementSpellingCounter();
            resetLoopCounter();
        }
    }

    @Override
    public void render() {
        characterMenu.render();
    }
    @Override
    public void restart(){
        stop();
        initCharacterMenu();
        characterMenu.setAnimationSymbols(new ArrayList<>());
        toggleTable(true);
    }

    @Override
    public void execute() {

    }
}
