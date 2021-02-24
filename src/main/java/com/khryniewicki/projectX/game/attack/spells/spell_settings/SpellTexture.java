package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;

@Data
public class SpellTexture {

    private Texture texture;
    private float size;

    public SpellTexture(Texture texture, float size) {
        this.texture = texture;
        this.size = size;
    }
}
