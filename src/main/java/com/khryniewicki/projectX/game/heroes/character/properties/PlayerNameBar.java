package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.graphics.GraphicLoader;


public class PlayerNameBar extends GraphicLoader {
    private UltraHero ultraHero;
    float offsetPositionY = 0.55f;
    float offsetPositionX = -0.3f;
    private float tmpHeroX=0F;
    private float tmpHeroY=0F;

    public PlayerNameBar(Builder builder) {
        super(builder);
        this.ultraHero = builder.ultraHero;
        update_position();
    }


    @Override
    public void update() {
        if (ultraHero.getX() != tmpHeroX || ultraHero.getY() != tmpHeroY) {
            update_position();
        }
    }

    public void update_position() {
        setPositionX(ultraHero.getX() + offsetPositionX);
        setPositionY(ultraHero.getY() + offsetPositionY);
        updateMesh();
        this.tmpHeroX = ultraHero.getX();
        this.tmpHeroY = ultraHero.getY();
    }

    public static class Builder extends GraphicLoader.Builder<Builder> {
        private UltraHero ultraHero;


        public PlayerNameBar.Builder withHero(UltraHero ultraHero) {
            this.ultraHero = ultraHero;
            return this;
        }


        public Builder() {
        }

        public PlayerNameBar build() {
            return new PlayerNameBar(this);
        }
    }
}
