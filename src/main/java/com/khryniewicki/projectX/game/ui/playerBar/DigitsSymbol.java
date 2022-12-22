package com.khryniewicki.projectX.game.ui.playerBar;

import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextFactory;
import com.khryniewicki.projectX.game.ui.symbols.GameSymbol;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.WHITE;

@Data
@Slf4j
public class DigitsSymbol implements Symbol {
    private int tmpNumber = 0;
    private String name;
    private UltraHero hero;
    private Position position;
    private GameSymbol number;
    private Map<Integer, Texture> mapRegistry;

    public DigitsSymbol(String name) {
        this.name = name;
        this.hero = HeroesInstances.getInstance().getHero();
        this.position = getPosition(name);
        mapRegistry = new HashMap<>();

        number = new GameSymbol.Builder(takeTexture(), position.getX(), position.getY())
                .withWidth(1f)
                .withHeight(0.5f)
                .withVisibility(1f)
                .build();
    }

    @Override
    public void update() {
        update_number();
    }

    private void update_number() {
        if (getNumber() != tmpNumber) {
            number.setTexture(takeTexture());
            number.updateMesh();
            tmpNumber = getNumber();
        }
    }

    private Texture takeTexture() {
        Integer number = getNumber();
        if (mapRegistry.containsKey(number)) {
            return mapRegistry.get(number);
        } else {
            Texture textureWithNumber = TextFactory.textInPlayerBar(number.toString(), WHITE);
            mapRegistry.put(number, textureWithNumber);
            return textureWithNumber;
        }
    }

    private Integer getNumber() {
        return name.equals("life") ? hero.getLife() : Math.round(hero.getMana());
    }

    private Position getPosition(String name) {
        return new Position(name.equals("life") ? -7.0f : -5.6f, 5.1f);
    }


    @Override
    public void render() {
        number.render();
    }


}
