package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.attack.attackSuccess.ActivatedAttack;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock2 extends Spell {

    private Position tmp;
    private UltraHero mock;
    private final ActivatedAttack activatedAttack;
    private final SpellTrajectory spellTrajectory;


    public SpellMock2() {
        super();
        createHero();
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
        if (tmp != null && tmp == SpellReceiveService.spellTarget) {
                return;
        }

        if (SpellReceiveService.spellTarget != null) {
            getPositionFromSpellReceiveService();
        }
    }

    private void getPositionFromSpellReceiveService() {
        tmp = SpellReceiveService.spellTarget;
        setSpellInstance(SpellReceiveService.spellInstance);
        setTarget(tmp);
    }

    @Override
    public void createHero() {
        mock = HeroesInstances.getInstance().getMock();
        setUltraHero(mock);
    }



}
