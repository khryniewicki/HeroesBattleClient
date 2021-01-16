package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.Application;
import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.engine.GameState;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonsFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.textures.GameTextures;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static com.khryniewicki.projectX.game.user_interface.board.GameFactory.BACKGROUND;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.MAIN_MENU;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.QUIT;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.YOU_LOSE;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.YOU_WIN;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
public class RestartMenu extends AbstractMenu {
    private Board board;
    private Game game;
    private boolean show;
    private HeroesInstances heroesInstances;

    private RestartMenu() {
        super();
        start();
        game = Game.getInstance();
        heroesInstances = HeroesInstances.getInstance();
    }

    @Override
    public void execute() {
        loop();
        if (Application.state.equals(GameState.OK)) {
            restart();
            Application.restart();
        }

    }

    @Override
    public void loop() {
        if (Application.state.equals(GameState.RESTART)) {
            prepare();
            insideLoop();
        }
    }

    @Override
    public void prepare() {
        update_background(GameTextures.SHADOW_BACKGROUND);
        win_or_loose();
        begin();
        addEventClick();
    }

    @Override
    public void update() {
        glfwPollEvents();
        board.update();
        if (Application.state.equals(GameState.FINISH)) {
            log.info("STOP IN UPDATE");
            stop();
        }
    }

    @Override
    public void render() {
        clearBuffers();
        Stream.of(board.getSymbols(), board.getHeroes(), buttons, permanentImages)
                .flatMap(Collection::stream)
                .forEach(Symbol::render);
        swapBuffers();
    }

    @Override
    public void restart() {
        reset_heroes();
        reset_background();
        reset_result();
        show = false;
    }

    protected void reset_heroes() {
        heroesInstances.reset();
    }

    private void reset_background() {
        update_background(GameTextures.LIGHT_BACKGROUND);
    }

    private void reset_result() {
        permanentImages = new ArrayList<>();
    }

    private void update_background(Texture texture) {
        board = game.getBoard();
        List<Symbol> symbols = manager.update_texture(board.getSymbols(), BACKGROUND, texture);
        board.setSymbols(symbols);
    }


    @Override
    public void init() {
        super.setButtons(new ArrayList<>(Arrays.asList(QUIT, MAIN_MENU)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ButtonsFactory buttonName = (ButtonsFactory) evt.getNewValue();

        if (buttonName.equals(ButtonsFactory.RESTART_GO_TO_MAIN_MENU)) {
            Application.ok();
        } else if (buttonName.equals(ButtonsFactory.RESTART_QUIT)) {
            Application.finish_game();
        }
        log.info("STOP IN PROPERTY");
        stop();
    }

    public void win_or_loose() {
        SuperHero hero = heroesInstances.getHero();
        Integer herolife = hero.getLife();
        if (!show) {
            if (herolife == 0) {
                log.info("YOU LOSE");
                permanentImages.add(YOU_LOSE);
            } else {
                log.info("YOU WIN");
                permanentImages.add(YOU_WIN);
            }
            show = true;
        }
    }

    private static final RestartMenu instance = new RestartMenu();

    public static RestartMenu getInstance() {
        return instance;
    }
}
