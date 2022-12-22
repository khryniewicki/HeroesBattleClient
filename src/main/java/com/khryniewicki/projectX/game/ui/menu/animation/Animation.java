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
        draw_random_number();
    }

    public void execute(String hero) {
        prepare(hero);
        insideLoop();
    }

    protected void prepare(String hero) {
        begin();
        initCharacterMenu();
        setSuperHero(heroFactory.create(hero));
        remove_spell_if_exists();
        setSpell(new AnimationSpell(superHero, animationHero));
        add_symbol(animationHero);
        add_symbol(table);
        reset_table();
    }

    @Override
    public void update() {
        if (!spellActive) {
            spell_and_dummy_controller();
            hero_controller();
        } else {
            reset_counting();
        }
        spell.update();
        loopCycles++;
    }

    private void spell_and_dummy_controller() {
        if (this.spellingCounter == (random_number - 60)) {
            add_dummy();
            increment_spelling_counter();
        } else if (this.spellingCounter == (random_number - 30)) {
            animationHero.attack(superHero);
            casting_spell();
            reset_loop_counter();
            increment_spelling_counter();
        } else if (this.spellingCounter > random_number) {
            remove_symbol(dummy);
            spell.disappear();
            remove_spell_if_exists();
            draw_random_number();
            reset_table();
            reset_spelling_counter();
            render();
        }
    }

    private void hero_controller() {
        if (loopCycles == 5) {
            animationHero.walk(superHero);
            increment_spelling_counter();
        } else if (loopCycles == 9) {
            animationHero.idle(superHero);
            increment_spelling_counter();
            reset_loop_counter();
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
        toggle_table(true);
    }

    @Override
    public void execute() {

    }
}
