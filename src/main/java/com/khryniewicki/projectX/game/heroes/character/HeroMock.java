package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroReceiveService;

public class HeroMock implements UltraHero {
    private Float tmpPositionX;
    private Float tmpPositionY;
    private final UltraHero ultraHero;
    private HeroReceiveService heroReceiveService;
    boolean isMovingLeft = false;

    public HeroMock(SuperHero superHero) {
        this.ultraHero = superHero;
        heroReceiveService = HeroReceiveService.getInstance();

    }


    private void changeMockPosition() {

        if (tmpPositionX != null && tmpPositionX == heroReceiveService.getMockPositionX()) {
            if (tmpPositionY != null && tmpPositionY == heroReceiveService.getMockPositionY()) {
                setHeroIdle();
                setMesh();
                return;
            }
        }
        changeMockSide();
        changePosition();
        setHeroRun();
        setMesh();
        updateLifeBar();
    }

    private void updateLifeBar() {
        LifeBar lifeBar = getLifeBar();
        lifeBar.updateLifeBar();
    }

    private void changePosition() {
        tmpPositionX = heroReceiveService.getMockPositionX();
        tmpPositionY = heroReceiveService.getMockPositionY();

        setPositionX(heroReceiveService.getMockPositionX());
        setPositionY(heroReceiveService.getMockPositionY());
    }

    private void changeMockSide() {
        if (tmpPositionX != null) {

            if (Math.signum(tmpPositionX - heroReceiveService.getMockPositionX()) == 1) isMovingLeft = true;
            else if (Math.signum(tmpPositionX - heroReceiveService.getMockPositionX()) == -1) isMovingLeft = false;
            setMovingLeft(isMovingLeft);
        }
    }

    @Override
    public void update() {
        changeMockPosition();
    }

    @Override
    public void render() {
        ultraHero.render();
    }

    @Override
    public Vector getPosition() {
        return ultraHero.getPosition();
    }

    @Override
    public Spell getSpell() {
        return ultraHero.getSpell();
    }

    @Override
    public LifeBar getLifeBar() {
        return ultraHero.getLifeBar();
    }

    @Override
    public Integer getLife() {
        return heroReceiveService.getMockLife();
    }


    @Override
    public void setPosition() {
        ultraHero.setPosition();
    }

    @Override
    public void setPositionX(Float positionX) {
        ultraHero.setPositionX(positionX);
    }

    @Override
    public void setPositionY(Float positionY) {
        ultraHero.setPositionY(positionY);
    }

    @Override
    public void setProperties() {
        ultraHero.setProperties();
    }

    @Override
    public void setTexture() {
        ultraHero.setTexture();
    }

    @Override
    public void setHeroIdle() {
        ultraHero.setHeroIdle();
    }

    @Override
    public void setHeroRun() {
        ultraHero.setHeroRun();
    }

    @Override
    public void setSpell() {
        ultraHero.setSpell();
    }

    @Override
    public void setLifeBar(LifeBar lifeBar) {
        ultraHero.setLifeBar(lifeBar);
    }

    @Override
    public void setMesh() {
        ultraHero.setMesh();
    }

    @Override
    public void setMovingLeft(boolean movingLeft) {
        ultraHero.setMovingLeft(movingLeft);
    }


}
