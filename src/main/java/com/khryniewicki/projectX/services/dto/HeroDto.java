package com.khryniewicki.projectX.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class HeroDto implements Serializable, BaseDto {
    private String heroType;
    private Integer life;
    private Integer mana;
    private Float positionX;
    private Float positionY;
    private boolean isSpellDTO;
    private String sessionId;

    public HeroDto(Builder builder) {
        this.heroType = builder.heroType;
        this.life = builder.life;
        this.mana = builder.mana;
        this.positionX = builder.positionX;
        this.positionY = builder.positionY;
    }

    public static class Builder {
        private String heroType;
        private Integer life;
        private Integer mana;
        private Float positionX;
        private Float positionY;

        public Builder heroType(String heroType) {
            this.heroType = heroType;
            return this;
        }

        public Builder life(Integer life) {
            this.life = life;
            return this;
        }

        public Builder mana(Integer mana) {
            this.mana = mana;
            return this;
        }

        public Builder positionX(Float positionX) {
            this.positionX = positionX;
            return this;
        }

        public Builder positionY(Float positionY) {
            this.positionY = positionY;
            return this;
        }

        public HeroDto build() {
            return new HeroDto(this);
        }
    }

    @Override
    public String toString() {
        return "HeroDTO{" +
                "heroType='" + heroType + '\'' +
                ", life=" + life +
                ", mana=" + mana +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", isSpellDTO=" + isSpellDTO +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
