package com.khryniewicki.projectX.game.board.playerBar.symbols;

import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.utils.Digits;
import lombok.Data;

import java.util.Objects;

@Data
public class DigitDisplaySymbol {
    private String name;
    private UltraHero hero;
    private Integer number;
    private Integer tmpNumber;
    private SymbolImp hundred;
    private SymbolImp dozen;
    private SymbolImp unit;
    private Position position;


    public DigitDisplaySymbol(String name) {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        this.name = name;
        this.hero = heroesInstances.getHero();
        this.position = getPosition(name);
        if (Objects.isNull(Digits.digitsRegistry)) {
            Digits.fillDigitsRegistry();
        }
        this.hundred = new SymbolImp.Builder(getHundredDigitTexture(), position.getX(), position.getY())
                .build();
        this.dozen = new SymbolImp.Builder(getDozenDigitTexture(), position.getX() + 0.3f, position.getY())
                .build();
        this.unit = new SymbolImp.Builder(getUnitDigitTexture(), position.getX() + 0.6f, position.getY())
                .build();


    }

    private Position getPosition(String name) {

        float positionX = -5.95f;
        float positionY = 5.2f;

        if (name.equals("life")) {
            positionX = -7.45f;
        }

        return new Position(positionX, positionY);
    }

    public Texture getHundredDigitTexture() {
        return Digits.getHundredDigitTexture(getNumber());
    }

    public Texture getDozenDigitTexture() {
        return Digits.getDozenDigitTexture(getNumber());
    }

    public Texture getUnitDigitTexture() {
        return Digits.getUnitDigitTexture(getNumber());
    }

    private Integer getNumber() {
        if (name.equals("life")) {
            number = hero.getLife();
        } else {
            number = hero.getMana();
        }
        return number;
    }

    public void render() {
        updateNumber();
        hundred.render();
        dozen.render();
        unit.render();
    }

    private void updateNumber() {
        if (Objects.nonNull(tmpNumber) && tmpNumber.equals(getNumber())) {
            return;
        }
        hundred.setTexture(getHundredDigitTexture());
        dozen.setTexture(getDozenDigitTexture());
        unit.setTexture(getUnitDigitTexture());
        tmpNumber = number;

    }


}
