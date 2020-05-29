package com.khryniewicki.projectX.game.heroes.character;

import lombok.Data;

@Data
public class SuperHeroProperties {
    private static float bx, by, bx0, bx1, by0, by1;
    private static float hero_standard_offset;
    private static float hero_top_offset;
    private static SuperHero hero;

    public SuperHeroProperties(SuperHero hero) {
        this.hero = hero;
    }

    public static void getLocationParameters() {
        bx0 = bx - hero.SIZE / 2.0f + hero_standard_offset;
        bx1 = bx + hero.SIZE / 2.0f - hero_standard_offset;
        by0 = by - hero.SIZE / 2.0f + hero_standard_offset;
        by1 = by + hero.SIZE / 2.0f - hero_top_offset;
    }
}