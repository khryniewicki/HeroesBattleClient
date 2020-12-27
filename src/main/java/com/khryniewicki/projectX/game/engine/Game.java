package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketController;
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
public class Game extends GameLoopImp implements Runnable {

    private Board board;
    private WebsocketController websocketController;

    public void start() {
        state = GameState.OK;
        new Thread(this, "Game").start();
    }

    public void run() {
        init();
        loading_menu();
        initialize_game();
    }

    private void loading_menu() {
        LoadingMenu loadingMenu = LoadingMenu.getInstance();
        loadingMenu.execute();
    }

    public void initialize_game() {
        main_menu();
        multiplayer();
        loop();
        restart_menu();
        terminate_game();
    }

    private void main_menu() {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.execute();
    }

    private void multiplayer() {
        MultiplayerController multiplayerController = MultiplayerController.getMultiplayerInstance();
        multiplayerController.execute();
    }

    private void restart_menu() {
        RestartMenu restartMenu = RestartMenu.getInstance();
        restartMenu.execute();
    }

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
        if (state.equals(GameState.PLAYER_IS_DEAD)) {
            restart();
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

    public void terminate_game() {
        stop_websocket();
        terminateIfWindowShutDown();
    }

    public void stop_websocket() {
        websocketController.stop_websocket();
    }

    private Game() {
        websocketController = WebsocketController.getWebsocketInstance();
    }

    public static Game getInstance() {
        return Game.HELPER.WAITING_ROOM_MENU;
    }

    private static class HELPER {
        private final static Game WAITING_ROOM_MENU = new Game();
    }

}
