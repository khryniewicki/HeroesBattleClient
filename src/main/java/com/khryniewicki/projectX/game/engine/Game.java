package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.RestartMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Component
@Getter
@Setter
@Slf4j
public class Game extends LifeCycle {

    private Board board;
    private MultiplayerController multiplayerController;

    @Override
    protected void prepare() {
        create_board();
        begin();
    }

    private void create_board() {
        board = new Board();
    }

    @Override
    public void update() {
        glfwPollEvents();
        board.update();
        is_player_dead();
    }

    private void is_player_dead() {
        if (board.player_dead()) {
            Application.restart_game();
            stop();
            stop_websocket();
        }
    }

    @Override
    public void render() {
        clearBuffers();
        board.render();
        swapBuffers();
    }

    @Override
    public void init() {

    }

    public void stop_websocket() {
        multiplayerController.stop_websocket();
    }

    private Game() {
        multiplayerController = MultiplayerController.getMultiplayerInstance();
    }

    public static Game getInstance() {
        return Game.HELPER.WAITING_ROOM_MENU;
    }

    @Override
    public void execute() {
        loop();
    }

    private static class HELPER {
        private final static Game WAITING_ROOM_MENU = new Game();
    }

}
