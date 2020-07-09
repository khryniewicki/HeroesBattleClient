package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackExecution;
import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock extends Spell {

    private Position tmp,spellTarget;
    private final AttackExecution attackExecution;
    private final AttackTrajectory attackTrajectory;
    private final SpellInstance spellInstance;
    private UltraHero mock;

    public SpellMock(SpellInstance spellInstance) {
        super(spellInstance);
        this.spellInstance = spellInstance;
        createHero();
        attackExecution = new AttackExecution(this);
        attackTrajectory = new AttackTrajectory(this,mock);
    }

    private void getSpellType() {
        if (spellInstance.isBasic()) {
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
        getSpellType();
        getSpellMock();
        attackTrajectory.castingSpell();
        attackExecution.hitsHeroWithSpell();
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
        setStartingTimeSpell(System.currentTimeMillis());
        setTarget(tmp);
        activateSpell();
    }
    private void activateSpell() {
        if (spellInstance.isBasic()) {
            UltraSpell basicSpell = mock.getBasicSpell();
            basicSpell.setSpellActivated(true);
        } else {
            UltraSpell ultimateSpell = mock.getUltimateSpell();
            ultimateSpell.setSpellActivated(true);
        }
    }
}
