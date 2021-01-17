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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@Slf4j
public class DigitsSymbol implements Symbol {
    private String name;
    private UltraHero hero;
    private Integer number;
    private Position position;
    private Integer tmpNumber;
    private GameSymbol numbers;
    private Map<Integer, Texture> mapRegistry;

    public DigitsSymbol(String name) {
        this.name = name;
        this.hero = HeroesInstances.getInstance().getHero();
        this.position = getPosition(name);
        mapRegistry = new HashMap<>();

        numbers = new GameSymbol.Builder("number", getTexture(), position.getX(), position.getY())
                .withWidth(1f)
                .withHeight(0.5f)
                .withVisibility(1f)
                .build();
    }


    private Integer getNumber() {
        return name.equals("life") ? hero.getLife() : Math.round(hero.getMana());
    }

    private Position getPosition(String name) {
        return new Position(name.equals("life") ? -7.1f : -5.6f, 5.1f);
    }

    @Override
    public void update() {
        updateNumber();
    }

    @Override
    public void render() {
        numbers.render();
    }

    @Override
    public void reload() {
        this.mapRegistry.clear();
        this.numbers.reload();
    }

    private void updateNumber() {
        if (Objects.nonNull(tmpNumber) && tmpNumber.equals(getNumber())) {
            return;
        }
        numbers.setTexture(getTexture());
        tmpNumber = number;
    }

    private Texture getTexture() {
        Integer number = getNumber();
        if (mapRegistry.containsKey(number)) {
            return mapRegistry.get(number);
        } else {
            Texture textureWithNumber = TextFactory.textInPlayerBar(number.toString(), Color.WHITE);
            mapRegistry.put(number, textureWithNumber);
            return textureWithNumber;
        }
    }
}
