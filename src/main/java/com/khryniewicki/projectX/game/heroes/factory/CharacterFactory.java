package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import org.springframework.stereotype.Component;

@Component
public interface CharacterFactory {
    SuperHero create(String character);
}
