package com.khryniewicki.projectX.game.board.playerBar.symbols;

import com.khryniewicki.projectX.graphics.VertexArray;

public interface Symbol {
    VertexArray createVertexArray();
    void render();
}
