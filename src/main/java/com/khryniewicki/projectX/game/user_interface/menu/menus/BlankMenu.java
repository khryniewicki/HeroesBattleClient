package com.khryniewicki.projectX.game.user_interface.menu.menus;

import java.util.Collections;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.BG_ANIMATION;

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
