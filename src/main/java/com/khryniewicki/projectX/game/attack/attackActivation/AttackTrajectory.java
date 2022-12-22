package com.khryniewicki.projectX.game.attack.attackActivation;

import com.khryniewicki.projectX.game.attack.spell.instance.SpellInstance;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.game.attack.spell.settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.sending.StackEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.khryniewicki.projectX.graphics.textures.SpellTextures.ICEBALL_SIDE;
import static com.khryniewicki.projectX.graphics.textures.SpellTextures.ICEBALL_UP;

@Slf4j
@Getter
@Setter
public class AttackTrajectory {

    private Position distance, target;
    private boolean isSpellNotPrepared;
    private int counter;

    private final UltraHero hero;
    private SpellInstance spellInstance;
    private final UltraSpell spell;
    private final StackEvent stackEvent;

    public AttackTrajectory(UltraSpell spell, UltraHero hero) {
        this.hero = hero;
        this.stackEvent = StackEvent.getInstance();
        this.spell = spell;
        this.spellInstance = spell.getSpellInstance();
        this.isSpellNotPrepared = true;
        makeTargetNull();
    }

    public void castingSpell() {
        target = spell.getTarget();
        if (Objects.nonNull(target)) {
            prepareSpell();
            calculateTrajectory();
        }
        spellDuration();
    }

    public void calculateTrajectory() {
        Vector position = spell.getPosition();
        Float velocity = spellInstance.getCastingSpeed();
        float halfVelocity = velocity / 2;
        if (Math.abs(distance.getX()) > halfVelocity || Math.abs(distance.getY()) > halfVelocity) {
            if (Math.abs(distance.getX()) > halfVelocity && Math.abs(distance.getY()) <= halfVelocity) {
                spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
            } else if (Math.abs(distance.getX()) <= halfVelocity && Math.abs(distance.getY()) > halfVelocity) {
                spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
            } else if (Math.abs(distance.getX()) > halfVelocity && Math.abs(distance.getY()) > halfVelocity) {
                if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
                    spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
                    spell.setPositionY(position.y + (distance.getY()) / Math.abs(distance.getX()) * velocity);
                } else {
                    spell.setPositionX(position.x + (distance.getX()) / Math.abs(distance.getY()) * velocity);
                    spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
                }
            }
            catchSpellBeyondScreen();
            if (Math.abs(position.x - target.getX()) <= halfVelocity && Math.abs(position.y - target.getY()) <= halfVelocity) {
                targetReached();
            }
        } else {
            targetReached();
        }
    }

    private void catchSpellBeyondScreen() {
        if (distance.getX() > 20 || distance.getY() > 10) {
            counter++;
            if (counter > 10) {
                counter = 0;
                restartSpell();
            }
        }
    }

    private void targetReached() {
        spell.setImage(1f, 1f, spellInstance.getConsumedSpellTexture());
        spell.setPosition(new Vector(target.getX(), target.getY(), 1f));
        restartSpell();
    }

    private void restartSpell() {
        makeTargetNull();
        setSpellNotPrepared(true);
        hero.setHeroIdle();
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
        UltraSpell spell;
        if (spellInstance.isBasic()) {
            spell = hero.getBasicSpell();
        } else {
            spell = hero.getUltimateSpell();
        }
        spell.setSpellActivated(false);
    }

    public void prepareSpell() {
        if (isSpellNotPrepared) {
            hero.setHeroAttack();
            distance = new Position(target.getX() - spell.getHeroPositionX(), target.getY() - spell.getHeroPositionY());

            spell.setImage(-Math.signum(distance.getY()), -Math.signum(distance.getX()), spellInstance.getThrowingSpellTexture());
            skullSpellException();
            iceballException();
            spell.setPosition(new Vector(spell.getHeroPositionX(), spell.getHeroPositionY(), 1f));
            setSpellNotPrepared(false);
        }
    }

    private void iceballException() {
        if (spellInstance.getName().equals("Ice Berg") || spellInstance.getName().equals("Ice Bolt")) {
            float size = spellInstance.getThrowingSpellTexture().getSize();
            if (Math.abs(distance.getY()) < 1) {
                spell.setImage(-Math.signum(distance.getY()), -Math.signum(distance.getX()), new SpellTexture(ICEBALL_SIDE, size * 1.2f));
            } else if (Math.abs(distance.getX()) < 1.5) {
                spell.setImage(Math.signum(distance.getY()), Math.signum(distance.getX()), new SpellTexture(ICEBALL_UP, size * 1.2f));
            }
        }
    }

    private void skullSpellException() {
        if (spellInstance.getName().equals("Skull Curse")) {
            spell.setImage(Math.signum(1), (-Math.signum(distance.getX())) * (-Math.signum(distance.getY())), spellInstance.getThrowingSpellTexture());
        }
    }

    private void makeTargetNull() {
        spell.setTarget(null);
        target = null;
    }

}
