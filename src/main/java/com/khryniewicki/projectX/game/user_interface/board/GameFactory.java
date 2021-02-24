package com.khryniewicki.projectX.game.user_interface.board;

import com.khryniewicki.projectX.game.user_interface.symbols.GameSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.textures.GameTextures;

public class GameFactory {
   public static Symbol BACKGROUND = new GameSymbol.Builder(GameTextures.LIGHT_BACKGROUND, -10f, -10.0f * 9.0f / 16.0f)
            .withVisibility(0.0f)
            .withWidth(20f)
            .withHeight(19 * 9.0f / 16.0f)
            .build();
    public static Symbol BAR = new GameSymbol.Builder(GameTextures.BAR, -10f, 9.0f * 9.0f / 16.0f)
            .withVisibility(0.9f)
            .withWidth(20f)
            .withHeight(9.0f / 16.0f)
            .build();

}
