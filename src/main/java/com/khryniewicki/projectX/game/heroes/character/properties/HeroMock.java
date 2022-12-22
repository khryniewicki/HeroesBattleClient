package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.receive.ReceiveServiceSingleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Getter
@Setter
public class HeroMock extends SuperHero {
    private final ReceiveServiceSingleton receiveService;
    private Position tmp, finalPosition;
    private Integer tmpLife;
    private Float tmpMana;
    private Long now;
    boolean isMovingLeft, isIdle;

    public HeroMock(SuperHero superHero) {
        super();
        addProperties(superHero);
        receiveService = ReceiveServiceSingleton.getInstance();
        verifyAttributes();
    }

    @Override
    public void update() {
        move();
        verifyAttributes();
        updateHeroAttributes();
    }

    private void move() {
        if (nonNull(tmp) && tmp.equals(getHeroMockPosition())) {
            if (nonNull(now) && System.currentTimeMillis() - now > 300) {
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
        changeMockPosition();
    }


    private void updateHeroAttributes() {
        heroAttributes.update();
    }

    private void changeMockPosition() {
        tmp = getHeroMockPosition();
        now = System.currentTimeMillis();
        setPositionX(finalPosition.getX());
        setPositionY(finalPosition.getY());
    }

    private void changeMockSide() {
        if (isNull(tmp)) {
            tmp = getHeroMockPosition();
        }
        finalPosition = getHeroMockPosition();
        float direction = Math.signum(tmp.getX() - finalPosition.getX());
        if (direction == 1) {
            isMovingLeft = true;
        } else if (direction == -1) {
            isMovingLeft = false;
        }
        setTurningLeft(isMovingLeft);
    }

    private Position getHeroMockPosition() {
        return receiveService.getHeroMockPosition();
    }

    private void verifyAttributes() {
        verifyLife();
        verifyMana();
    }

    private void verifyLife() {
        Integer life = getLife();
        if (nonNull(life) && nonNull(tmpLife)) {
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

    private void verifyMana() {
        Float mana = getMana();
        if (nonNull(mana) && nonNull(tmpMana)) {
            if (!tmpMana.equals(mana)) {
                updateManaBar();
                tmpMana = mana;
            }
        } else
            tmpMana = mana;
    }


    public void updateManaBar() {
        ManaBar manaBar = getManaBar();
        manaBar.updateManaBar();
    }


    @Override
    public Integer getLife() {
        return isNull(receiveService.getHeroMockLife()) ? super.getLife() : receiveService.getHeroMockLife();
    }

    @Override
    public Float getMana() {
        return isNull(receiveService.getHeroMockMana()) ? super.getMana() : receiveService.getHeroMockMana();
    }


}
