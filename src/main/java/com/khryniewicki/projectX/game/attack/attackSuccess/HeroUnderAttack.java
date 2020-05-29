package com.khryniewicki.projectX.game.attack.attackSuccess;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.SuperHeroProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class HeroUnderAttack {

    private Spell spell;
    private float ox0, ox1, oy0, oy1;
    private float bx0, bx1, by0, by1;
    private float spellCoordZ;
    private float[] heroCoordinates, spellCoordiantes;



    public boolean isAttackedBySpell(UltraSpell currentSpell) {
        simpleObjectDimenions(currentSpell);
        heroObjectDimenions();

        boolean isAttackSucceeded = false;

//        System.out.println(Arrays.toString(heroCoordinates) + "   " + Arrays.toString(spellCoordiantes));
        if (bx1 > ox0 && bx0 < ox1) {
            if (by1 > oy0 && by0 < oy1) {
                log.info("Spell reached hero");
                isAttackSucceeded = true;
            }
        }
        return isAttackSucceeded;
    }

    public void heroObjectDimenions() {
        SuperHeroProperties.getLocationParameters();
        heroCoordinates = new float[]{bx1, bx0, by1, by0};
    }

    private void simpleObjectDimenions(UltraSpell currentSpell) {
        ox0 = currentSpell.getPositionX() - currentSpell.getSize() / 2.0f;
        ox1 = currentSpell.getPositionX() + currentSpell.getSize() / 2.0f;
        oy0 = currentSpell.getPositionY() - currentSpell.getSize() / 2.0f;
        oy1 = currentSpell.getPositionY() + currentSpell.getSize() / 2.0f;
        spellCoordZ=currentSpell.getPositionZ();
        spellCoordiantes = new float[]{ox1, ox0, oy1, oy0};
    }
}
