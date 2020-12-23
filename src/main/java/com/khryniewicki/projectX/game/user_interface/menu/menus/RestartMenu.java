package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.engine.GameState;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.MAIN_MENU;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.QUIT;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
public class RestartMenu extends MenuImp {
    private static final RestartMenu instance = new RestartMenu();
    private Board board;

    public static RestartMenu getInstance() {
        return instance;
    }

    private boolean show;

    private RestartMenu() {
        super();
        start();

        board = Board.getInstance();
    }

    @Override
    public void execute() {
        win_or_loose();
        begin();
        addEventClick();
        loop();
        HeroesInstances heroesInstances=HeroesInstances.getInstance();
        heroesInstances.reset();
        log.info("RESTART MENU");
        if (Game.state.equals(GameState.OK)) {
            Game.getInstance().initialize_game();
        }
    }

    @Override
    public void update() {
        glfwPollEvents();
    }

    @Override
    public void render() {
        clearBuffers();
        buttons.forEach(MenuSymbol::render);
        board.render();
        swapBuffers();
    }

    @Override
    public void init() {
        super.setButtons(new ArrayList<>(Arrays.asList(QUIT, MAIN_MENU)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String btnName = (String) evt.getNewValue();
        if (btnName.equals("main_menu")) {
            Game.getInstance().ok();
        } else if (btnName.equals("quit")) {
            Game.getInstance().finish_game();
        }
        stop();
    }

    public void win_or_loose() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        if (Objects.nonNull(heroesInstances.getHero())) {
            Integer herolife = heroesInstances.getHero().getLife();
            if (!show) {
                if (herolife == 0) {
                    log.info("YOU LOST");
                } else {
                    log.info("YOU WIN");
                }
                show = true;
            }
        }
    }
}
