package com.khryniewicki.projectX.game.attack.attackActivation;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.SpellTexture;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.khryniewicki.projectX.graphics.textures.SpellTextures.ICEBALL_SIDE;
import static com.khryniewicki.projectX.graphics.textures.SpellTextures.ICEBALL_UP;

@Slf4j
@Data
public class AttackTrajectory {

    private UltraHero hero;
    private Position distance, target;
    private boolean isSpellNotPrepared;
    private int counter;

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
        float half_velocity = velocity / 2;
        if (Math.abs(distance.getX()) > half_velocity || Math.abs(distance.getY()) > half_velocity) {
            if (Math.abs(distance.getX()) > half_velocity && Math.abs(distance.getY()) <= half_velocity) {
                spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
            } else if (Math.abs(distance.getX()) <= half_velocity && Math.abs(distance.getY()) > half_velocity) {
                spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
            } else if (Math.abs(distance.getX()) > half_velocity && Math.abs(distance.getY()) > half_velocity) {
                if (Math.abs(distance.getX()) > Math.abs(distance.getY())) {
                    spell.setPositionX(position.x + Math.signum(distance.getX()) * velocity);
                    spell.setPositionY(position.y + (distance.getY()) / Math.abs(distance.getX()) * velocity);
                } else {
                    spell.setPositionX(position.x + (distance.getX()) / Math.abs(distance.getY()) * velocity);
                    spell.setPositionY(position.y + Math.signum(distance.getY()) * velocity);
                }
            }
            catchSpellBeyondScreen();
            if (Math.abs(position.x - target.getX()) <= half_velocity && Math.abs(position.y - target.getY()) <= half_velocity) {
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
        log.info("Target reached: {}", target);
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
            log.info("[x:{} , y:{}]", spell.getHeroPositionX(), spell.getHeroPositionY());
            distance = new Position(target.getX() - spell.getHeroPositionX(), target.getY() - spell.getHeroPositionY());

            spell.setImage(-Math.signum(distance.getY()), -Math.signum(distance.getX()), spellInstance.getThrowingSpellTexture());
            skull_spell_exception();
            iceball_exception();
            spell.setPosition(new Vector(spell.getHeroPositionX(), spell.getHeroPositionY(), 1f));
            setSpellNotPrepared(false);
            log.info("Target[{}],[{}]", target.getX(), target.getY());
        }
    }

    private void iceball_exception() {
        if (spellInstance.getName().equals("IceBerg") || spellInstance.getName().equals("IceBolt")) {
            float size = spellInstance.getThrowingSpellTexture().getSize();
            if (Math.abs(distance.getY()) < 1) {
                spell.setImage(-Math.signum(distance.getY()), -Math.signum(distance.getX()), new SpellTexture(ICEBALL_SIDE, size * 1.2f));
            } else if (Math.abs(distance.getX()) < 1.5) {
                spell.setImage(Math.signum(distance.getY()), Math.signum(distance.getX()), new SpellTexture(ICEBALL_UP, size * 1.2f));
            }
        }
    }

    private void skull_spell_exception() {
        if (spellInstance.getName().equals("Skull")) {
            spell.setImage(Math.signum(1), (-Math.signum(distance.getX())) * (-Math.signum(distance.getY())), spellInstance.getThrowingSpellTexture());
        }
    }

    private void makeTargetNull() {
        spell.setTarget(null);
        target = null;
    }

}
