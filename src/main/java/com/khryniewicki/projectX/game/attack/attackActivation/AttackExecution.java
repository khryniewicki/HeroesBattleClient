package com.khryniewicki.projectX.game.attack.attackActivation;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.HeroAttributes;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.sending_service.StackEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
public class AttackExecution {

    private final UltraSpell spell;
    private final SuperHero hero, mock;
    private final LifeBar lifeBar;
    private final StackEvent stackEvent;
    private float ox0, ox1, oy0, oy1, oz0;
    private float bx0, bx1, by0, by1;
    private boolean isAttackSucceeded, isSpellActivated;

    public AttackExecution(UltraSpell spell) {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        stackEvent = StackEvent.getInstance();
        this.spell = spell;
        this.hero = heroesInstances.getHero();
        this.mock = heroesInstances.getMock();
        HeroAttributes heroAttributes = hero.getHeroAttributes();
        this.lifeBar = heroAttributes.getLifeBar();
    }

    public void executeSpell() {

        spellObjectDimensions();
        heroObjectDimenions();

        if (oz0 <= 0) {
            isSpellActivated = false;
            isAttackSucceeded = false;
        } else {
            spellActivation();
        }
    }

    private void spellActivation() {
        mock.updateManaBar();
        isAttackSucceeded();
        reduceHeroLife();
    }

    private void isAttackSucceeded() {
        if (bx1 > ox0 && bx0 < ox1) {
            if (by1 > oy0 && by0 < oy1) {
                isAttackSucceeded = true;
            }
        }
    }

    private void reduceHeroLife() {
        if (isAttackSucceeded && !isSpellActivated) {
            Integer life = hero.getLife();
            Integer powerAttack = spell.getPowerAttack();
            hero.setLife(life > powerAttack ? life - powerAttack : 0);
            updateLifeBar();
            isSpellActivated = true;
        }
    }

    private void updateLifeBar() {
        lifeBar.updateLifeBar();
        stackEvent.addHeroDto();
    }


    private void heroObjectDimenions() {
        float bx = hero.getX();
        float by = hero.getY();
        float hero_top_offset = hero.getHero_top_offset();
        float hero_left_offset = hero.getHero_left_offset();

        bx0 = bx - hero.SIZE / 2.0f + hero_left_offset;
        bx1 = bx + hero.SIZE / 2.0f - hero_left_offset;
        by0 = by - hero.SIZE / 2.0f + hero_left_offset;
        by1 = by + hero.SIZE / 2.0f - hero_top_offset;
    }

    private void spellObjectDimensions() {
        Vector position = spell.getPosition();
        if (Objects.isNull(position)) return;
        ox0 = position.x - spell.getSize() / 2.0f;
        ox1 = position.x + spell.getSize() / 2.0f;
        oy0 = position.y - spell.getSize() / 2.0f;
        oy1 = position.y + spell.getSize() / 2.0f;
        oz0 = position.z;
    }
}
