package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.graphics.GraphicLoader;


public class PlayerNameBar extends GraphicLoader {
    private UltraHero ultraHero;
    float offsetPositionY = 0.55f;
    float offsetPositionX = -0.3f;

    public PlayerNameBar(Builder builder) {
        super(builder);
        this.ultraHero = builder.ultraHero;
        update();
    }


    @Override
    public void update() {
        setPositionX(ultraHero.getX() + offsetPositionX);
        setPositionY(ultraHero.getY() + offsetPositionY);
        updateMesh();
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
