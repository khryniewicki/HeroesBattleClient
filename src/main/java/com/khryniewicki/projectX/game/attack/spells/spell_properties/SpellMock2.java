package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.attack.attackSuccess.ActivatedAttack;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock2 extends Spell {

    private final UltraSpell ultraSpell;
    private UltraHero mock;
    private final ActivatedAttack activatedAttack;
    private final SpellTrajectory spellTrajectory;
    private Float tmpX, tmpY;

    public SpellMock2() {
        super();
        createHero();
        ultraSpell = mock.getUltraSpell();
        activatedAttack = new ActivatedAttack(this);
        spellTrajectory = new SpellTrajectory(this);
    }


    @Override
    public void update() {
        getSpellMock();
        spellTrajectory.spellCasting();
        activatedAttack.hitsHeroWithSpell();
    }

    public void getSpellMock() {
        if (tmpX != null && tmpX == SpellReceiveService.spellMockPositionX) {
            if (tmpY != null && tmpY == SpellReceiveService.spellMockPositionY) {
                return;
            }
        }
        if (SpellReceiveService.spellMockPositionX != null) {
            getPositionFromSpellReceiveService();
        }
    }

    private void getPositionFromSpellReceiveService() {
        tmpX = SpellReceiveService.spellMockPositionX;
        tmpY = SpellReceiveService.spellMockPositionY;
        setSpellInstance(SpellReceiveService.spellInstance);
        setFinalX(tmpX);
        setFinalY(tmpY);

    }

    @Override
    public void createHero() {
        mock = HeroesInstances.getInstance().getMock();
        setUltraHero(mock);
    }

    @Override
    public void setPosition(Vector position) {
        ultraSpell.setPosition(position);
    }

}
