package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.receive_services.ReceiveServiceSingleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
@Setter
public class HeroMock extends SuperHero {
    private Position tmp, finalPosition;
    private Integer tmpLife, tmpMana;
    private final ReceiveServiceSingleton receiveService;
    boolean isMovingLeft, isIdle;
    private Long now;

    public HeroMock(SuperHero superHero) {
        super();
        addProperties(superHero);
        receiveService = ReceiveServiceSingleton.getInstance();
        checkLifeAndMana();
    }

    @Override
    public void update() {
        move();
        checkLifeAndMana();
        updateHeroAttributes();
    }

    private void move() {
        if (tmp != null && tmp.equals(getMockPosition())) {
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

        changeMockSide();
        setHeroRun();
        setMesh();
        changePosition();
    }


    private void updateHeroAttributes() {
        heroAttributes.update();
    }

    private void changePosition() {
        tmp = getMockPosition();
        now = System.currentTimeMillis();
        setPositionX(finalPosition.getX());
        setPositionY(finalPosition.getY());
    }

    private void changeMockSide() {
        if (tmp == null) {
            tmp = getMockPosition();
        }
        finalPosition = getMockPosition();
        if (Math.signum(tmp.getX() - finalPosition.getX()) == 1) {
            isMovingLeft = true;
        } else if (Math.signum(tmp.getX() - finalPosition.getX()) == -1) {
            isMovingLeft = false;
        }
        setTurningLeft(isMovingLeft);

    }

    private Position getMockPosition() {
        return receiveService.get_hero_mock_position();
    }

    private void checkLifeAndMana() {
        checkLife();
        checkMana();
    }

    private void checkLife() {
        Integer life = getLife();
        if (life != null && tmpLife != null) {
            if (!tmpLife.equals(life)) {
                updateLifeBar();
                tmpLife = life;
            }
        } else
            tmpLife = life;
    }

    private void updateLifeBar() {
        LifeBar lifeBar = getLifeBar();
        lifeBar.updateLifeBar();
    }

    private void checkMana() {
        Integer mana = getMana();
        if (mana != null && tmpMana != null) {
            if (!tmpMana.equals(mana)) {
                updateManaBar();
                tmpMana = mana;
            }
        } else
            tmpMana = mana;
    }

    @Override
    public void updateManaBar() {
        ManaBar manaBar = getManaBar();
        manaBar.updateManaBar();
    }


    @Override
    public Integer getLife() {
        return Objects.isNull(receiveService.get_hero_mock_life()) ? super.getLife() : receiveService.get_hero_mock_life();
    }

    @Override
    public Integer getMana() {
        return Objects.isNull(receiveService.get_hero_mock_mana()) ? super.getMana() : receiveService.get_hero_mock_mana();
    }


}
