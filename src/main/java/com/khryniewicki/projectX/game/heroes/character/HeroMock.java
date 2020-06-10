package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroReceiveService;

public class HeroMock implements UltraHero {
    private Float tmpPositionX;
    private Float tmpPositionY;
    private final UltraHero ultraHero;
    private HeroReceiveService heroReceiveService;
    boolean isMovingLeft = false;

    private HeroMock() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        this.ultraHero = heroesInstances.getMock();
        heroReceiveService = HeroReceiveService.getInstance();
    }

    public static HeroMock getInstance() {
        return HELPER.INSTANCE;
    }

    private Boolean changeMockPosition() {

        if (tmpPositionX != null && tmpPositionX == heroReceiveService.getHeroMockPositionX()) {
            if (tmpPositionY != null && tmpPositionY == heroReceiveService.getHeroMockPositionY()) {
               setHeroIdl();
               setMesh();
                return false;
            }
        }
        changeMockSide();
        tmpPositionX = heroReceiveService.getHeroMockPositionX();
        tmpPositionY = heroReceiveService.getHeroMockPositionY();

        setPositionX(heroReceiveService.getHeroMockPositionX());
        setPositionY(heroReceiveService.getHeroMockPositionY());
        setHeroRun();
        setMesh();

        return true;
    }

    private void changeMockSide() {
        if (tmpPositionX != null) {

            if (Math.signum(tmpPositionX - heroReceiveService.getHeroMockPositionX()) == 1) isMovingLeft = true;
            else if (Math.signum(tmpPositionX - heroReceiveService.getHeroMockPositionX()) == -1) isMovingLeft = false;
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


    private static class HELPER {
        private final static HeroMock INSTANCE = new HeroMock();
    }

    @Override
    public void setHeroIdl(){
        ultraHero.setHeroIdl();
    };
    @Override
    public void setHeroRun(){
        ultraHero.setHeroRun();
    };
}
