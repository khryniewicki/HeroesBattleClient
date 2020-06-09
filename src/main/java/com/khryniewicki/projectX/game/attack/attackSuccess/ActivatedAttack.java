package com.khryniewicki.projectX.game.attack.attackSuccess;

import com.khryniewicki.projectX.game.board.Board;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ActivatedAttack {

    private UltraSpell spell;
    private SuperHero hero;
    private float ox0, ox1, oy0, oy1,oz0;
    private float bx0, bx1, by0, by1;
    private float[] heroCoordinates, spellCoordiantes;

    public ActivatedAttack(UltraSpell spell) {
        this.spell=spell;
        this.hero = Board.hero;
    }

    public boolean hitsHeroWithSpell() {
        consumeSpellMana();
        if (oz0<0) return false;

        simpleObjectDimenions();
        heroObjectDimenions();


//        System.out.println(Arrays.toString(heroCoordinates) + "   " + Arrays.toString(spellCoordiantes));

        return isAttackSucceeded();
    }

    private void consumeSpellMana() {
        hero.setMana(hero.getMana()-spell.getManaConsumed());
    }

    private boolean isAttackSucceeded() {
        boolean isAttackSucceeded=false;
        if (bx1 > ox0 && bx0 < ox1) {
            if (by1 > oy0 && by0 < oy1) {
                log.info("Spell reached hero");
                isAttackSucceeded = true;
            }
        }
        reduceHeroLife();

        return isAttackSucceeded;
    }

    private void reduceHeroLife() {
        Float life = hero.getLife();
        hero.setLife(life-spell.getPowerAttack());
        System.out.println(life.toString());
    }

    public void heroObjectDimenions() {

        bx0 = Board.myCollision.getBx0();
        bx1 = Board.myCollision.getBx1();
        by0 = Board.myCollision.getBy0();
        by1 = Board.myCollision.getBy1();
        heroCoordinates = new float[]{bx1, bx0, by1, by0};
    }

    private void simpleObjectDimenions() {
        Vector position = spell.getPosition();
        if (position==null) return;
        ox0 = position.x - spell.getSize() / 2.0f;
        ox1 = position.x + spell.getSize() / 2.0f;
        oy0 = position.y - spell.getSize() / 2.0f;
        oy1 = position.y + spell.getSize() / 2.0f;
        oz0=position.z;
        spellCoordiantes = new float[]{ox1, ox0, oy1, oy0};
    }
}
