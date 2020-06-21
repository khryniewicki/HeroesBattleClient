package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
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


    private Boolean changeMockPosition() {

        if (tmpPositionX != null && tmpPositionX == heroReceiveService.getMockPositionX()) {
            if (tmpPositionY != null && tmpPositionY == heroReceiveService.getMockPositionY()) {
               setHeroIdle();
               setMesh();
                return false;
            }
        }
        changeMockSide();
        tmpPositionX = heroReceiveService.getMockPositionX();
        tmpPositionY = heroReceiveService.getMockPositionY();

        setPositionX(heroReceiveService.getMockPositionX());
        setPositionY(heroReceiveService.getMockPositionY());
        setHeroRun();
        setMesh();

        return true;
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
    public void setTexture() {
        ultraHero.setTexture();
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
    public void setLifeStripClass(StartingPosition startingPosition) {
        ultraHero.setLifeStripClass(startingPosition);
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
    public void setSpell() {
        ultraHero.setSpell();
    }

    @Override
    public void setMesh() {
        ultraHero.setMesh();
    }

    @Override
    public void setMovingLeft(boolean movingLeft) {
        ultraHero.setMovingLeft(movingLeft);
    }


    @Override
    public void setHeroIdle(){
        ultraHero.setHeroIdle();
    };
    @Override
    public void setHeroRun(){
        ultraHero.setHeroRun();
    };
}
