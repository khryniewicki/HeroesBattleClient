package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellRegistry;
import com.khryniewicki.projectX.game.heroes.character.wizards.FireWizard;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.LOADING;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.LOGO;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_GREEN;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
@Getter
@Setter
public class LoadingMenu extends AbstractMenu {
    private long duration;
    private long now;
    private boolean flag;
    private long difference;
    private SpellRegistry spellRegistry;
    private static final LoadingMenu instance = new LoadingMenu();

    private LoadingMenu() {
        super();
        start();
    }

    @Override
    public void init() {
        setVolatileImages(Arrays.asList(LOADING,LOGO));
    }

    @Override
    public void execute() {
        begin();
        loop();
    }

    @Override
    protected void prepare() {
        duration = 5000L;
        now = System.currentTimeMillis();
        flag = true;
    }

    @Override
    public void insideLoop() {
        do {
            setDifference(System.currentTimeMillis() - now);
            getProgress();
            if (flag) {
                initSpells();
                setDifference(System.currentTimeMillis() - now);
                getProgress();
                initSuperHeroes();
                flag = false;
            }
            glfwPollEvents();
            windowsShouldClose();
        } while (difference < duration && running);
    }

    private void initSpells() {
        SpellRegistry spellRegistry = SpellRegistry.getInstance();
        spellRegistry.fill();
    }

    private void initSuperHeroes() {
        new FireWizard();
    }

    private void getProgress() {
        long result = difference / 50;
        long progress = result < 100 ? result : 100;
        String text = "Loading " + progress + "%";
        update_volatiles(LOADING, getTextureForLoading(text));
        render();
    }

    private Texture getTextureForLoading(String text) {
        return TextFactory.textInLoadingMenuToImage(text, BRIGHT_GREEN, 22);
    }

    public static LoadingMenu getInstance() {
        return instance;
    }

}
