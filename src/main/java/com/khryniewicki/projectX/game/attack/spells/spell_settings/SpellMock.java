package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackExecution;
import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock extends Spell {

    private Position tmp;
    private UltraHero mock;
    private final AttackExecution attackExecution;
    private final AttackTrajectory attackTrajectory;
    private SpellInstance spellInstance;

    private Position spellTarget;

    public SpellMock(SpellInstance spellInstance) {
        super(spellInstance);
        this.spellInstance = spellInstance;
        getSpellType();
        createHero();
        attackExecution = new AttackExecution(this);
        attackTrajectory = new AttackTrajectory(this);
    }

    private void getSpellType() {
        boolean isBasic = spellInstance.isBasic();
        if (isBasic) {
            spellTarget = SpellReceiveService.basicSpellTarget;
        } else {
            spellTarget = SpellReceiveService.ultimateSpellTarget;

        }
    }

    @Override
    public void createHero() {
        mock = HeroesInstances.getInstance().getMock();
        setUltraHero(mock);
    }

    @Override
    public void update() {
        getSpellMock();
        attackTrajectory.castingSpell();
        attackExecution.hitsHeroWithSpell();
    }

    public void getSpellMock() {
        spellTarget = SpellReceiveService.basicSpellTarget;
        if (tmp != null && tmp == spellTarget) {
            return;
        }

        if (spellTarget != null) {
            getPositionFromSpellReceiveService();
        }
    }

    private void getPositionFromSpellReceiveService() {
        tmp = SpellReceiveService.basicSpellTarget;
        setTarget(tmp);
    }

}
