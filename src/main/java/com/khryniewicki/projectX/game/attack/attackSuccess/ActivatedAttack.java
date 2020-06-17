package com.khryniewicki.projectX.game.attack.attackSuccess;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.board.Board;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ActivatedAttack {

    private final UltraSpell spell;
    private final SuperHero hero;
    private float ox0, ox1, oy0, oy1, oz0;
    private float bx0, bx1, by0, by1;
    private float[] heroCoordinates, spellCoordiantes;
    private boolean isAttackSucceeded, isSpellActivated, isManaConsumed;

    public ActivatedAttack(UltraSpell spell) {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        this.spell = spell;
        this.hero = heroesInstances.getHero();

    }

    public void hitsHeroWithSpell() {

        spellObjectDimensions();
        heroObjectDimenions();

        if (oz0 <= 0) {
            isSpellActivated = false;
            isAttackSucceeded = false;
            isManaConsumed = false;
        } else {
            spellActivation();
        }
    }

    private void spellActivation() {
        consumeSpellMana();
        isAttackSucceeded();
        //            log.info(Arrays.toString(heroCoordinates) + "   " + Arrays.toString(spellCoordiantes));
    }

    private void consumeSpellMana() {
        if (!isManaConsumed) {
            Integer heroMana = hero.getMana();
            Integer manaConsumed = spell.getManaConsumed();
            hero.setMana(heroMana - manaConsumed);
            isManaConsumed = true;
        }
    }


    private void isAttackSucceeded() {
        if (bx1 > ox0 && bx0 < ox1) {
            if (by1 > oy0 && by0 < oy1) {
                isAttackSucceeded = true;
            }
        }
        reduceHeroLife();
    }

    private void reduceHeroLife() {
        if (isAttackSucceeded && !isSpellActivated) {
            Integer life = hero.getLife();
            hero.setLife(life - 10);
            log.info("HERO LIFE: {} HERO MANA: {}", hero.getLife(), hero.getMana());
            isSpellActivated = true;
        }
    }

    private void heroObjectDimenions() {

        bx0 = Board.myCollision.getBx0();
        bx1 = Board.myCollision.getBx1();
        by0 = Board.myCollision.getBy0();
        by1 = Board.myCollision.getBy1();
        heroCoordinates = new float[]{bx0, bx1, by0, by1};
    }

    private void spellObjectDimensions() {
        Vector position = spell.getPosition();
        if (position == null) return;
        ox0 = position.x - spell.getSize() / 2.0f;
        ox1 = position.x + spell.getSize() / 2.0f;
        oy0 = position.y - spell.getSize() / 2.0f;
        oy1 = position.y + spell.getSize() / 2.0f;
        oz0 = position.z;
        spellCoordiantes = new float[]{ox0, ox1, oy0, oy1, oz0};
    }
}
