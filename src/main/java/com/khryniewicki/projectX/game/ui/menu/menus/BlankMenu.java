package com.khryniewicki.projectX.game.ui.menu.menus;

import java.util.Collections;

import static com.khryniewicki.projectX.game.ui.menu.graphic.factory.TextureMenuFactory.BG_ANIMATION;

public class BlankMenu extends AbstractMenu {
    private static final BlankMenu instance = new BlankMenu();

    public static BlankMenu getInstance() {
        return instance;
    }

    private BlankMenu() {
        super();
        start();
    }

    @Override
    public void init() {
        setPermanentImages(Collections.singletonList(BG_ANIMATION));
    }

    @Override
    public void execute() {
        render();
    }
}
