package com.khryniewicki.projectX.game.user_interface.playerBar;

import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.GameSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Objects;

@Data
@Slf4j
public class DigitsSymbol implements Symbol {
    private String name;
    private UltraHero hero;
    private Integer number;
    private Position position;
    private Integer tmpNumber;
    private Symbol hundred;
    private Symbol dozen;
    private Symbol unit;
    private Symbol numbers;


    public DigitsSymbol(String name) {
        this.name = name;
        this.hero = HeroesInstances.getInstance().getHero();
        this.position = getPosition(name);
        numbers = new GameSymbol.Builder(getTexture(), position.getX(), position.getY())
                .withWidth(1f)
                .withHeight(0.5f)
                .withVisibility(1f)
                .build();
    }


    private Integer getNumber() {
        return name.equals("life") ? hero.getLife() : hero.getMana();
    }

    private Position getPosition(String name) {
        return new Position(name.equals("life") ? -7.6f : -6.1f, 5.1f);
    }

    @Override
    public void update() {
        updateNumber();
    }

    @Override
    public void render() {
        numbers.render();
    }

    private void updateNumber() {
        if (Objects.nonNull(tmpNumber) && tmpNumber.equals(getNumber())) {
            return;
        }
        numbers.setTexture(getTexture());
        tmpNumber = number;
    }

    private Texture getTexture() {
        return TextFactory.textInPlayerBar(getNumber().toString(), Color.WHITE);
    }
}
