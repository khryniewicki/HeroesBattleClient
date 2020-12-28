package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackExecution;
import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock extends Spell {
    private final AttackExecution attackExecution;
    private final AttackTrajectory attackTrajectory;
    private final SpellInstance spellInstance;
    private Position tmp, spellTarget;

    public SpellMock(SpellInstance spellInstance) {
        super(spellInstance);
        this.spellInstance = spellInstance;
        createHero();
        attackExecution = new AttackExecution(this);
        attackTrajectory = new AttackTrajectory(this, hero);
    }

    @Override
    public void createHero() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        setHero(heroesInstances.getMock());
    }

    @Override
    public void update() {
        getSpellType();
        getSpellMock();
        castingSpell();
        executeSpell();
    }


    private void getSpellType() {
        if (spellInstance.isBasic()) {
            spellTarget = SpellReceiveService.basicSpellTarget;
        } else {
            spellTarget = SpellReceiveService.ultimateSpellTarget;
        }
    }

    public void getSpellMock() {
        if (tmp != null && tmp == spellTarget) {
            return;
        }
        if (spellTarget != null) {
            getPositionFromSpellReceiveService();
        }
    }

    private void getPositionFromSpellReceiveService() {
        tmp = spellTarget;
        setTarget(tmp);
        setStartingTimeSpell(System.currentTimeMillis());
        activateSpell();
    }

    private void activateSpell() {
        UltraSpell spell;
        if (spellInstance.isBasic()) {
            spell = hero.getBasicSpell();
        } else {
            spell = hero.getUltimateSpell();
        }
        spell.setSpellActivated(true);
    }

    private void castingSpell() {
        attackTrajectory.casting_spell();
    }

    private void executeSpell() {
        attackExecution.executeSpell();
    }

}
