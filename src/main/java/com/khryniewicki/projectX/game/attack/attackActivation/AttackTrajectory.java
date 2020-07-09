package com.khryniewicki.projectX.game.attack.attackActivation;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AttackTrajectory {

    private UltraHero hero;
    private Position distance, target;
    private boolean isSpellNotPrepared;

    private SpellInstance spellInstance;
    private final UltraSpell spell;
    private final SendingService sendingService;
    private final StackEvent stackEvent;

    public AttackTrajectory(UltraSpell spell, UltraHero hero) {
        this.hero = hero;
        this.stackEvent = StackEvent.getInstance();
        this.sendingService = new SendingService();
        this.spell = spell;
        this.spellInstance = spell.getSpellInstance();
        this.isSpellNotPrepared = true;
    }

    public void castingSpell() {
        if (spell.getTarget() != null) {
            target = spell.getTarget();
            prepareSpell();
            calculateTrajectory();
        }
        spellDuration();
    }

    private void calculateTrajectory() {
        Vector position = spell.getPosition();
        Float velocity = spellInstance.getCastingSpeed();
        float half_velocity = velocity / 2;
//        log.info("Distance[{}],[{}]", distance.getX(), distance.getY());
        if (Math.abs(distance.getX()) > half_velocity || Math.abs(distance.getY()) > half_velocity) {
            if (Math.abs(distance.getX()) > half_velocity && Math.abs(distance.getY()) <= half_velocity) {
//                log.info("CASE 1");
                spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
            } else if (Math.abs(distance.getX()) <= half_velocity && Math.abs(distance.getY()) > half_velocity) {
//                log.info("CASE 2");
                spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
            } else if (Math.abs(distance.getX()) > half_velocity && Math.abs(distance.getY()) > half_velocity) {
//                log.info("CASE 3");
                if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
                    spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
                    spell.setPositionY(position.y + (distance.getY()) / Math.abs(distance.getX()) * velocity);
                } else {
                    spell.setPositionX(position.x + (distance.getX()) / Math.abs(distance.getY()) * velocity);
                    spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
                }
            }

            if (Math.abs(position.x - target.getX()) <= half_velocity && Math.abs(position.y - target.getY()) <= half_velocity) {
                targetReached();
            }

        } else {
            targetReached();
        }

//        log.info("Position[{}],[{}]", position.x, position.y);

    }

    private void targetReached() {
        spell.setImage(1f, 1f, spellInstance.getConsumedSpellTexture());
        spell.setPosition(new Vector(target.getX(), target.getY(), 1f));
        makeTargetNull();
        setSpellNotPrepared(true);
    }

    private void spellDuration() {

        if (spell.getStartingTimeSpell() != 0L) {
            if (System.currentTimeMillis() - spell.getStartingTimeSpell() > spellInstance.getSpellDuration()) {
                spell.setPositionZ(-1f);
                spell.setStartingTimeSpell(0L);
                deactivateSpell();
            }
        }

    }

    private void deactivateSpell() {
        if (spellInstance.isBasic()) {
            UltraSpell basicSpell = hero.getBasicSpell();
            basicSpell.setSpellActivated(false);
        } else {
            UltraSpell ultimateSpell = hero.getUltimateSpell();
            ultimateSpell.setSpellActivated(false);
        }

    }


    public void prepareSpell() {
        if (isSpellNotPrepared) {
            distance = new Position(target.getX() - spell.getHeroPositionX(), target.getY() - spell.getHeroPositionY());
            spell.setImage(-Math.signum(distance.getY()), -Math.signum(distance.getX()), spellInstance.getThrowingSpellTexture());
            spell.setPosition(new Vector(spell.getHeroPositionX(), spell.getHeroPositionY(), 1f));
            setSpellNotPrepared(false);
            log.info("Target[{}],[{}]", target.getX(), target.getY());
        }
    }


    private void makeTargetNull() {
        spell.setTarget(null);
        target = null;
    }

}
