package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackExecution;
import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock extends Spell {

    private Position tmp;
    private UltraHero mock;
    private final AttackExecution attackExecution;
    private final AttackTrajectory attackTrajectory;


    public SpellMock() {
        super();
        createHero();
        attackExecution = new AttackExecution(this);
        attackTrajectory = new AttackTrajectory(this);
    }

    @Override
    public void createHero() {
        mock = HeroesInstances.getInstance().getMock();
        setUltraHero(mock);
    }

    @Override
    public void update() {
        getSpellMock();
        attackTrajectory.spellCasting();
        attackExecution.hitsHeroWithSpell();
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

}
