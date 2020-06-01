package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.Map.Level;
import lombok.Data;

@Data
public class SuperHeroProperties {
    private static float hero_standard_offset,hero_top_offset;
    private static float bx, by, bx0, bx1, by0, by1;
    private static SuperHero hero=Level.hero;



    public static void getHeroLocationParams() {
        bx=hero.getX();
        by=hero.getY();
        bx0 = bx - hero.SIZE / 2.0f + hero_standard_offset;
        bx1 = bx + hero.SIZE / 2.0f - hero_standard_offset;
        by0 = by - hero.SIZE / 2.0f + hero_standard_offset;
        by1 = by + hero.SIZE / 2.0f - hero_top_offset;

    }
}
