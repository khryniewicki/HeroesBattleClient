package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.multiplayer.websocket.states.ServerState;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Colors;
import com.khryniewicki.projectX.graphics.Texture;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.graphics.textures.MenuTextures.*;
import static com.khryniewicki.projectX.graphics.textures.MenuTextures.SERVER_OFFLINE;

public class CollectionManager {

    public List<Symbol> update_texture(List<Symbol> volatileImages, Symbol symbol, Texture texture) {
       return volatileImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        symbol.setTexture(texture);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Symbol> toggle_image(List<Symbol> volatileImages, Symbol symbol, boolean disabled) {
        return volatileImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        menuSymbol.setDisabled(disabled);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<MenuSymbol> update_label_description(List<MenuSymbol> permamentImages,MenuSymbol symbol, ServerState state) {
        return permamentImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        Color color = Colors.BRIGHT_YELLOW;
                        if (state.equals(ServerState.SERVER_OFFLINE)) {
                            color = Colors.BRIGHT_RED;
                        }
                        symbol.setTexture(TextFactory.textInPlayersMenuToImage(state.getTitle(), color));
                    }
                })
                .collect(Collectors.toList());
    }

    public List<MenuSymbol> update_label(List<MenuSymbol> permamentImages, MenuSymbol symbol, ServerState state) {
        return permamentImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        switch (state) {
                            case NO_PLAYERS:
                                symbol.setTexture(SERVER_EMPTY);
                                break;
                            case ONE_PLAYER:
                                symbol.setTexture(SERVER_HALF_FULL);
                                break;
                            case TWO_PLAYERS:
                                symbol.setTexture(SERVER_FULL);
                                break;
                            case SERVER_OFFLINE:
                                symbol.setTexture(SERVER_OFFLINE);
                                break;
                        }
                    }
                })
                .collect(Collectors.toList());
    }

}
