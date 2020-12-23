package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketController;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
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
        Thread game = new Thread(this, "Game");
        game.start();
    }

    public void run() {
        init();
        loading_menu();
        initialize_game();
        loop();
        terminate_game();
    }

    private void loading_menu() {
        LoadingMenu loadingMenu = LoadingMenu.getInstance();
        loadingMenu.execute();
    }

    public void initialize_game() {
        main_menu();
        start_multiplayer();
    }

    public void main_menu() {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.execute();
    }


    private void start_multiplayer() {
        MultiplayerController multiplayerController = MultiplayerController.getMultiplayerInstance();
        multiplayerController.execute();
    }

    @Override
    protected void beforeLoop() {
        create_board();
        start_sending_service();
        begin();
    }


    private void create_board() {
        board = Board.getInstance();
    }

    private void start_sending_service() {
        websocketController.start_sending_service();
    }

    @Override
    public void update() {
        glfwPollEvents();
        board.update();
    }

    @Override
    public void render() {
        clearBuffers();
        board.render();
        swapBuffers();
    }

    public void terminate_game() {
        if (!websocketController.getSessionId().isEmpty()) {
            websocketController.disconnect();
        }
        websocketController.stop_sending_service();
        terminateIfWindowShutDown();
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
