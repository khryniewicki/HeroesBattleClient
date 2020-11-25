package com.khryniewicki.projectX.services.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class HeroDTO implements Serializable, DTO {
    private String heroType;
    private Integer life;
    private Integer mana;
    private Float positionX;
    private Float positionY;
    private boolean isSpellDTO;

    public HeroDTO(String heroType, Integer life, Integer mana, Float positionX, Float positionY) {
        this.heroType = heroType;
        this.life = life;
        this.mana = mana;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public HeroDTO(Builder builder) {
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

        public  Builder heroType(String heroType) {
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

        public HeroDTO build() {
            return new HeroDTO(this);
        }
    }

}
