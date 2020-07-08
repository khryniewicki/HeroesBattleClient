package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SpellTrajectory {


    private UltraHero hero;
    private UltraSpell spell;
    private Position distance, target;
    private Long startingTimeSpell;
    private SpellInstance spellInstance;
    private final SendingService sendingService;
    private final StackEvent stackEvent;
    private boolean isSpellPrepared;

    public SpellTrajectory(UltraSpell spell) {
        this.stackEvent = StackEvent.getInstance();
        this.sendingService = new SendingService();
        this.spell = spell;
    }

    public void spellCasting() {
        if (spell.getTarget() != null) {
            target = spell.getTarget();
            createSpellInstance();
            prepareSpell();
            castingSpell();
        }
        spellDuration();
    }

    private void castingSpell() {
        Vector position = spell.getPosition();
        Float velocity = spellInstance.getCastingSpeed();
        float half_velocity = velocity / 2;

        if (Math.abs(distance.getX()) > velocity && Math.abs(distance.getY()) > velocity) {
            if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
                spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
                spell.setPositionY(position.y + (distance.getY()) / Math.abs(distance.getX()) * velocity);
            } else {
                spell.setPositionX(position.x + (distance.getX()) / Math.abs(distance.getY()) * velocity);
                spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
            }
            if (Math.abs(position.x - target.getX()) <= half_velocity && Math.abs(position.y - target.getY()) <= half_velocity) {
                targetReached();
            }
        } else {
            targetReached();
        }
        log.info("Position[{}],[{}]", position.x, position.y);

    }

    private void targetReached() {
        spell.setImage(1f, 1f, spellInstance.getConsumedSpellTexture());
        spell.setPosition(new Vector(target.getX(), target.getY(), 1f));
        makeTargetNull();
        setSpellPrepared(false);
    }

    private void spellDuration() {
        if (startingTimeSpell != null) {
            if (System.currentTimeMillis() - startingTimeSpell > spellInstance.getSpellDuration()) {
                spell.setPositionZ(-1f);
                startingTimeSpell = null;
                stackEvent.setCastingSpellsActivated(true);
            }
        }
    }


    public void prepareSpell() {
        if (!isSpellPrepared) {
            startingTimeSpell = System.currentTimeMillis();
            distance = new Position(target.getX() - spell.getHeroPositionX(), target.getY() - spell.getHeroPositionY());

            spell.setImage(-Math.signum(distance.getY()), -Math.signum(distance.getX()), spellInstance.getThrowingSpellTexture());
            spell.setPosition(new Vector(spell.getHeroPositionX(), spell.getHeroPositionY(), 1f));

            setSpellPrepared(true);
            log.info("[{}],[{}]", target.getX(), target.getY());

        }
    }


    private void makeTargetNull() {
        spell.setTarget(null);
        target = null;
    }


    public void createSpellInstance() {
        spellInstance = spell.getSpellInstance();
    }


}
