package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;

public interface UltraHero extends Ultra {
    void setHeroAttributes(HeroAttributes heroAttributes);
    HeroAttributes getHeroAttributes();

    Float getX();
    Float getY();

    BasicSpellInstance getBasicSpellInstance();
    UltimateSpellInstance getUltimateSpellInstance();
    void setTurningLeft(boolean movingLeft);

    LifeBar getLifeBar();
    Integer getLife();
    default void setLife(Integer mana){}
    ManaBar getManaBar();
    Long getManaRenegeration();
    void setManaRenegeration(Long manaRenegeration);

    Integer getMana();
    default void setMana(Integer mana){}

    UltraSpell getBasicSpell();
    void setBasicSpell(UltraSpell ultraSpell);

    UltraSpell getUltimateSpell();
    void setUltimateSpell(UltraSpell ultraSpell);

    StartingPosition getStartingPosition();
    void setStartingPosition(StartingPosition startingPosition);

    default void setSpellBasis(){}
    default void setHeroIdle(){}
    default void setHeroRun(){}
    default void setHeroAttack(){}
    default void updateManaBar(){}

}
