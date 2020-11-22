package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.HeroReceiveService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class HeroMock extends SuperHero {
    private Position tmp, finalPosition;
    private Integer tmpLife, tmpMana;
    private final HeroReceiveService heroReceiveService;
    boolean isMovingLeft, isIdle;
    private Long now;

    public HeroMock(SuperHero superHero) {
        super();
        addProperties(superHero);
        heroReceiveService = HeroReceiveService.getInstance();
    }

    @Override
    public void update() {
        mockMove();
        checkLifeAndMana();
        updateHeroAttributes();
    }

    private void mockMove() {
        if (tmp != null && tmp.equals(heroReceiveService.getMockPosition())) {
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
        updateManaBar();
    }

    private void checkLifeAndMana() {
        checkLife();
        checkMana();
    }

    private void updateHeroAttributes() {
        heroAttributes.update();
    }

    private void changePosition() {
        tmp = heroReceiveService.getMockPosition();
        now = System.currentTimeMillis();
        setPositionX(finalPosition.getX());
        setPositionY(finalPosition.getY());
    }

    private void changeMockSide() {
        if (tmp == null) {
            tmp = heroReceiveService.getMockPosition();
        }
        finalPosition = heroReceiveService.getMockPosition();
        if (Math.signum(tmp.getX() - finalPosition.getX()) == 1) {isMovingLeft = true;}
        else if (Math.signum(tmp.getX() - finalPosition.getX()) == -1) {isMovingLeft = false;}
        setTurningLeft(isMovingLeft);

    }

    private void updateLifeBar() {
        LifeBar lifeBar = getLifeBar();
        lifeBar.updateLifeBar();
    }

    @Override
    public void updateManaBar() {
        ManaBar manaBar = getManaBar();
        manaBar.updateManaBar();
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
    public Integer getLife() {
        return heroReceiveService.getMockLife();
    }

    @Override
    public Integer getMana() {
        return heroReceiveService.getMockMana();
    }


}
