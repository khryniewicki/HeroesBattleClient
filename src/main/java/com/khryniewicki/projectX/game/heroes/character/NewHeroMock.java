package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.services.HeroReceiveService;

public class NewHeroMock implements UltraHero {
    private UltraHero ultraHero;
    private HeroReceiveService heroReceiveService=new HeroReceiveService();

    public NewHeroMock(UltraHero insertedHero) {
        this.ultraHero = insertedHero;
    }


    @Override
    public void update() {
        setPositionX(heroReceiveService.heroMockPositionX);
        setPositionY(heroReceiveService.heroMockPositionY);
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
        ultraHero.setPositionX(positionY);
    }
    @Override
    public void setProperties() {
        ultraHero.setProperties();
    }
}
