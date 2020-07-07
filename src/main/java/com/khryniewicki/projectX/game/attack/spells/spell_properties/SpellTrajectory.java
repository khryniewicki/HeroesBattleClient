package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
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
    private Float distanceX, distanceY;
    private Long startingTimeSpell;
    private SpellInstance spellInstance;
    private final SendingService sendingService;
    private final StackEvent stackEvent;
    private boolean isTrajectoryFixed;

    public SpellTrajectory(UltraSpell spell) {
        this.stackEvent = StackEvent.getInstance();
        this.sendingService = new SendingService();
        this.spell = spell;
    }

    public void spellCasting() {
        if (spell.getFinalX() != null && spell.getFinalY() != null) {
            createSpellInstance();
            fixTrajectory();
            castingSpell();
        }
        spellDuration();
    }

    private void castingSpell() {
        Vector position = spell.getPosition();


        if (Math.abs(distanceX) > Math.abs(distanceY)) {
            spell.setPositionX(position.x + Math.signum(distanceX) * spellInstance.getCastingSpeed());
            spell.setPositionY(position.y + (distanceY) / Math.abs(distanceX) * spellInstance.getCastingSpeed());
        } else {
            spell.setPositionX(position.x + (distanceX) / Math.abs(distanceY) * spellInstance.getCastingSpeed());
            spell.setPositionY(position.y + Math.signum(distanceY) * spellInstance.getCastingSpeed());
        }

        log.info("Position[{}],[{}]", position.x, position.y);
        if (Math.abs(position.x - spell.getFinalX()) <= spellInstance.getCastingSpeed() / 2 && Math.abs(position.y - spell.getFinalY()) <= spellInstance.getCastingSpeed() / 2) {
            spell.setImage(1f, 1f, spellInstance.getConsumedSpellTexture());
            spell.setPosition(new Vector(spell.getFinalX(), spell.getFinalY(), 1f));
            makeFinalPositionsNull();
            setTrajectoryFixed(false);
        }
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




    public void fixTrajectory(){
        if (!isTrajectoryFixed){
            startingTimeSpell = System.currentTimeMillis();

        distanceX = spell.getFinalX() - spell.getHeroPositionX();
        distanceY = spell.getFinalY() - spell.getHeroPositionY();

        spell.setImage(-Math.signum(distanceY), -Math.signum(distanceX), spellInstance.getThrowingSpellTexture());
        spell.setPosition(new Vector(spell.getHeroPositionX(), spell.getHeroPositionY(), 1f));

        setTrajectoryFixed(true);
        log.info("[{}],[{}]", spell.getFinalX(), spell.getFinalY());

        }
    }


    private void makeFinalPositionsNull() {
        spell.setFinalX(null);
        spell.setFinalY(null);

    }


    public void createSpellInstance() {
            spellInstance = spell.getSpellInstance();
            log.info(spellInstance.getName());

    }


}
