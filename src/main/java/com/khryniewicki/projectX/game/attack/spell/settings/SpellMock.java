package com.khryniewicki.projectX.game.attack.spell.settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackExecution;
import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spell.instance.SpellInstance;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.receive.ReceiveServiceSingleton;

public class SpellMock extends Spell {
    private final AttackExecution attackExecution;
    private final SpellInstance spellInstance;
    private final ReceiveServiceSingleton receiveService;
    private Position tmp, spellTarget;

    public SpellMock(SpellInstance spellInstance) {
        super(spellInstance);
        this.spellInstance = spellInstance;
        this.receiveService = ReceiveServiceSingleton.getInstance();
        createHero();
        this.attackExecution = new AttackExecution(this);
        this.attackTrajectory = new AttackTrajectory(this, hero);
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
        spellTarget = receiveService.getSpellMockTarget(spellInstance.isBasic());
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
        UltraSpell spell = spellInstance.isBasic() ? hero.getBasicSpell() : hero.getUltimateSpell();
        spell.setSpellActivated(true);
    }

    private void castingSpell() {
        attackTrajectory.castingSpell();
    }

    private void executeSpell() {
        attackExecution.executeSpell();
    }

}
