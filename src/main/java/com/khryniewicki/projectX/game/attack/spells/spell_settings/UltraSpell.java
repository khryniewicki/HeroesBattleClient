package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.properties.Ultra;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.math.Vector;

public interface UltraSpell extends Ultra {

    void setTarget(Position target);
    void setPosition(Vector position);
    void setImage(Float indexHeight, Float indexWidth, SpellTexture texture);
    void spellCasting();
    void setPositionZ(Float positionZ);

    void createHero();

    Position getTarget();
    String getName();

    Float getHeroPositionX();
    Float getHeroPositionY();
    Float getSize();


    Integer getManaConsumed();
    Integer getPowerAttack();

    SpellInstance getSpellInstance();
    Long getStartingTimeSpell();
    void setStartingTimeSpell(Long startingTimeSpell);

    boolean isSpellActivated();
    void setSpellActivated(boolean isActivated);

    void setSpellInstance(SpellInstance spellInstance);

    Float getCastingSpeed();

    Long getSpellDuration();
}
