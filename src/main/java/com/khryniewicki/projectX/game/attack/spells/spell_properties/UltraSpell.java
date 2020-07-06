package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.heroes.character.Ultra;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;

public interface UltraSpell extends Ultra {

    void setFinalX(Float finalX);
    void setFinalY(Float finalY);
    void setDistanceX(Float distanceX);
    void setDistanceY(Float distanceY);
    void setSpell(Float indexHeight, Float indexWidth, Texture texture);
    void setPosition(Vector position);

    void getMousePosition();
    void spellCasting();
    void startSpellCountingTime();


    Float getHeroPositionX();
    Float getHeroPositionY();
    Float getDistanceX();
    Float getDistanceY();
    Float getSize();
    Texture getThrowingSpellTexture();
    Integer getManaConsumed();
    Integer getPowerAttack();



}
