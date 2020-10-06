package com.khryniewicki.projectX.game.attack.attackActivation;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.SendingService;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
public class AttackExecution {

    private final UltraSpell spell;
    private final UltraHero hero,mock;
    private final LifeBar lifeBar;
    private final SendingService sendingService;
    private float ox0, ox1, oy0, oy1, oz0;
    private float bx0, bx1, by0, by1;
    private boolean isAttackSucceeded, isSpellActivated;


    public AttackExecution(UltraSpell spell) {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        this.sendingService = new SendingService();
        this.spell = spell;
        this.hero = heroesInstances.getHero();
        this.mock = heroesInstances.getMock();
        this.lifeBar = hero.getLifeBar();
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
            Integer powerAttack = spell.getPowerAttack();
            hero.setLife(life - powerAttack);

            updateLifeBar();
            isSpellActivated = true;
        }
    }

    private void updateLifeBar() {
        lifeBar.updateLifeBar();
        sendingService.updateLife();
    }


    private void heroObjectDimenions() {

        bx0 = Board.collision.getBx0();
        bx1 = Board.collision.getBx1();
        by0 = Board.collision.getBy0();
        by1 = Board.collision.getBy1();

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
