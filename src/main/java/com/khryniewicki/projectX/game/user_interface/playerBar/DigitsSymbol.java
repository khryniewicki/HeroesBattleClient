package com.khryniewicki.projectX.game.user_interface.playerBar;

import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.game.user_interface.symbols.SymbolImp;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.utils.Digits;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
public class DigitsSymbol implements Symbol {
    private String name;
    private UltraHero hero;
    private Integer number;
    private Position position;
    private Integer tmpNumber;
    private Symbol hundred;
    private Symbol dozen;
    private Symbol unit;
    private List<Symbol> numbers;


    public DigitsSymbol(String name) {
        float offset = 0.3f;
        this.name = name;
        this.hero = HeroesInstances.getInstance().getHero();
        this.position = getPosition(name);

        initDigitRegistry();

        this.hundred = new SymbolImp.Builder(getHundredDigitTexture(), position.getX(), position.getY())
                .build();
        this.dozen = new SymbolImp.Builder(getDozenDigitTexture(), position.getX() + offset, position.getY())
                .build();
        this.unit = new SymbolImp.Builder(getUnitDigitTexture(), position.getX() + 2 * offset, position.getY())
                .build();
        this.numbers = new ArrayList<>(Arrays.asList(this.hundred, this.dozen, this.unit));
    }

    private void initDigitRegistry() {
        if (Objects.isNull(Digits.digitsRegistry)) {
            Digits.fillDigitsRegistry();
        }
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
        return name.equals("life") ? hero.getLife() : hero.getMana();
    }

    private Position getPosition(String name) {
        return new Position(name.equals("life") ? -7.45f : -5.95f, 5.2f);
    }

    @Override
    public void render() {
        updateNumber();
        numbers.forEach(Symbol::render);
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
