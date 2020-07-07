package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.Ultra;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;

public interface UltraSpell extends Ultra {

    void setFinalX(Float finalX);
    void setFinalY(Float finalY);
    void setDistanceX(Float distanceX);
    void setDistanceY(Float distanceY);
    void setImage(Float indexHeight, Float indexWidth, Texture texture);
    void setPosition(Vector position);
    void spellCasting();
    void setPositionZ(Float positionZ);

    String getName();
    Float getHeroPositionX();
    Float getHeroPositionY();
    Float getFinalX();
    Float getFinalY();
    Float getDistanceX();
    Float getDistanceY();
    Float getSize();

    Texture getThrowingSpellTexture();
    Integer getManaConsumed();
    Integer getPowerAttack();

    SpellInstance getSpellInstance();
    void setSpellInstance(SpellInstance spellInstance);

}
