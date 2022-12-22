package com.khryniewicki.projectX.services.sending;

import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.HeroStartingPosition;
import com.khryniewicki.projectX.services.dto.HeroesDto;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Getter
@Setter
public class StackEventSupport {
    private final HeroesInstances heroesInstances;
    private final HeroStartingPosition heroStartingPosition;
    private SuperHero hero;

    public StackEventSupport() {
        heroesInstances = HeroesInstances.getInstance();
        heroStartingPosition = HeroStartingPosition.getInstance();
    }

    public HeroesDto addHeroDto() {

        this.hero = heroesInstances.getHero();

        return new HeroesDto.Builder()
                .heroType(this.hero.getName())
                .life(this.hero.getLife())
                .mana(this.hero.getMana())
                .positionX(getHeroPositionX())
                .positionY(getHeroPositionY())
                .build();
    }

    public Float getHeroPositionX() {
        return isNull(this.hero.getX()) ? heroStartingPosition.getX() : this.hero.getX();
    }

    public Float getHeroPositionY() {
        return isNull(this.hero.getY()) ? heroStartingPosition.getY() : this.hero.getY();
    }
}
