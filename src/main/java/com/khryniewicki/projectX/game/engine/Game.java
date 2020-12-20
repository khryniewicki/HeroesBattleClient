package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.services.SendingService;
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


    public void start() {
        state = GameState.OK;
        Thread game = new Thread(this, "Game");
        game.start();
    }

    public void run() {
        init();
        initLoadingMenu();
        initializeMultiplayerGame();
        loop();
        terminateGame();
    }

    private void initLoadingMenu() {
        LoadingMenu loadingMenu = LoadingMenu.getInstance();
        loadingMenu.execute();
    }

    public void initializeMultiplayerGame() {
        initializeMenu();
        if (state.equals(GameState.OK)) {
            initializeWebsocketConnection();
            createBoard();
            createSendingService();
            begin();
        }
    }

    public void initializeMenu() {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.execute();
    }


    private void initializeWebsocketConnection() {
        MultiplayerController multiplayerController = MultiplayerController.getMultiplayerInstance();
        multiplayerController.execute();
    }


    private void createSendingService() {
        SendingService heroSending = new SendingService();
        Thread sender = new Thread(heroSending);
        sender.start();
    }

    private void createBoard() {
        board = Board.getInstance();
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

    public void terminateGame() {
        WebsocketInitializer websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        if (!websocketInitializer.getSessionId().isEmpty()) {
            websocketInitializer.disconnect();
        }
        terminateIfWindowShutDown();
    }

    private Game() {
    }

    public static Game getInstance() {
        return Game.HELPER.WAITING_ROOM_MENU;
    }

    private static class HELPER {
        private final static Game WAITING_ROOM_MENU = new Game();
    }

}
