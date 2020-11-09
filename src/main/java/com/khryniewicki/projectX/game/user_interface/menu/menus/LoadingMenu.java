package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellRegistry;
import com.khryniewicki.projectX.game.heroes.character.wizards.FireWizard;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.LOADING;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_GREEN;

@Slf4j
@Getter
@Setter
public class LoadingMenu extends MenuImp {

    private static final LoadingMenu instance = new LoadingMenu();

    public static LoadingMenu getInstance() {
        return instance;
    }

    private SpellRegistry spellRegistry;

    private LoadingMenu() {
        super();
        start();
    }

    @Override
    public void init() {
        setVolatileImages(Collections.singletonList(LOADING));
    }

    public void execute() {
        long duration = 5000L;
        long now = System.currentTimeMillis();
        boolean flag = true;
        do {
            getProgress(now);

            if (flag) {
                initSpells();
                getProgress(now);
                initSuperHeroes();
                flag = false;
            }
        } while (System.currentTimeMillis() - now < duration);

    }

    private void initSpells() {
        SpellRegistry spellRegistry = SpellRegistry.getInstance();
        spellRegistry.fill();
    }

    private void initSuperHeroes() {
        new FireWizard();
    }

    private void getProgress(long now) {
        long progress = (System.currentTimeMillis() - now) / 50;
        String text = "Loading " + progress + "%";
        updateImage(LOADING, getTextureForLoading(text));
        render();
    }

    private Texture getTextureForLoading(String text) {
        return TextFactory.textInLoadingMenuToImage(text, BRIGHT_GREEN);
    }
}
