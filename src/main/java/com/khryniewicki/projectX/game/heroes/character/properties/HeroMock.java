package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.receive.ReceiveServiceSingleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
@Setter
public class HeroMock extends SuperHero {
    private Position tmp, finalPosition;
    private Integer tmpLife;
    private Float tmpMana;
    private final ReceiveServiceSingleton receiveService;
    boolean isMovingLeft, isIdle;
    private Long now;

    public HeroMock(SuperHero superHero) {
        super();
        addProperties(superHero);
        receiveService = ReceiveServiceSingleton.getInstance();
        verify_attributes();
    }

    @Override
    public void update() {
        move();
        verify_attributes();
        updateHeroAttributes();
    }

    private void move() {
        if (tmp != null && tmp.equals(get_hero_mock_position())) {
            if (now != null && System.currentTimeMillis() - now > 300) {
                isIdle = true;
                now = null;
            }
            if (isIdle) {
                setHeroIdle();
                setMesh();
                isIdle = false;
            }
            return;
        }

        change_mock_side();
        setHeroRun();
        setMesh();
        change_mock_position();
    }


    private void updateHeroAttributes() {
        heroAttributes.update();
    }

    private void change_mock_position() {
        tmp = get_hero_mock_position();
        now = System.currentTimeMillis();
        setPositionX(finalPosition.getX());
        setPositionY(finalPosition.getY());
    }

    private void change_mock_side() {
        if (tmp == null) {
            tmp = get_hero_mock_position();
        }
        finalPosition = get_hero_mock_position();
        if (Math.signum(tmp.getX() - finalPosition.getX()) == 1) {
            isMovingLeft = true;
        } else if (Math.signum(tmp.getX() - finalPosition.getX()) == -1) {
            isMovingLeft = false;
        }
        setTurningLeft(isMovingLeft);

    }

    private Position get_hero_mock_position() {
        return receiveService.get_hero_mock_position();
    }

    private void verify_attributes() {
        verify_life();
        verify_mana();
    }

    private void verify_life() {
        Integer life = getLife();
        if (life != null && tmpLife != null) {
            if (!tmpLife.equals(life)) {
                update_life_bar();
                tmpLife = life;
            }
        } else
            tmpLife = life;
    }

    private void update_life_bar() {
        LifeBar lifeBar = getLifeBar();
        lifeBar.updateLifeBar();
    }

    private void verify_mana() {
        Float mana = getMana();
        if (mana != null && tmpMana != null) {
            if (!tmpMana.equals(mana)) {
                update_mana_bar();
                tmpMana = mana;
            }
        } else
            tmpMana = mana;
    }


    private void update_mana_bar() {
        ManaBar manaBar = getManaBar();
        manaBar.updateManaBar();
    }


    @Override
    public Integer getLife() {
        return Objects.isNull(receiveService.get_hero_mock_life()) ? super.getLife() : receiveService.get_hero_mock_life();
    }

    @Override
    public Float getMana() {
        return Objects.isNull(receiveService.get_hero_mock_mana()) ? super.getMana() : receiveService.get_hero_mock_mana();
    }


}
