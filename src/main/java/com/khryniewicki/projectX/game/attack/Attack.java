package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.game.attack.spell_group.Spell;
import com.khryniewicki.projectX.game.attack.spell_group.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.Hero;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Attack {

    private Spell spell;
    private Hero hero;
    private float ox0, ox1, oy0, oy1;
    private float bx0, bx1, by0, by1;
    private float[] heroCoordinates, spellCoordiantes;

    public boolean hitsHeroWithSpell(UltraSpell currentSpell) {
        simpleObjectDimenions(currentSpell);
        Collision.heroObjectDimenions();
        boolean isAttackSucceeded = false;


        if (bx1 > ox0 && bx0 < ox1) {
            if (by1 > oy0 && by0 < oy1) {
                log.info("Spell reached hero");
                isAttackSucceeded = true;
            }
        }
        return isAttackSucceeded;
    }


    private void simpleObjectDimenions(UltraSpell currentSpell) {
        ox0 = currentSpell.getPositionX() - currentSpell.getSize() / 2.0f;
        ox1 = currentSpell.getPositionX() + currentSpell.getSize() / 2.0f;
        oy0 = currentSpell.getPositionY() - currentSpell.getSize() / 2.0f;
        oy1 = currentSpell.getPositionY() + currentSpell.getSize() / 2.0f;
        spellCoordiantes = new float[]{ox1, ox0, oy1, oy0};
    }
}
