package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.ui.board.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Component
@Getter
@Setter
@Slf4j
public class Game implements LifeCycle {
    private boolean running;
    private Board board;
    private MultiplayerController multiplayerController;

    @Override
    public void prepare() {
        createBoard();
        begin();
    }

    private void createBoard() {
        board = new Board();
    }

    @Override
    public void update() {
        glfwPollEvents();
        board.update();
        isPlayerDead();
    }

    private void isPlayerDead() {
        if (board.player_dead()) {
            Application.restartGame();
            stop();
            stopWebsocket();
        }
    }

    @Override
    public void render() {
        clearBuffers();
        board.render();
        swapBuffers();
    }


    public void stopWebsocket() {
        multiplayerController.stopWebsocket();
    }

    private Game() {
        multiplayerController = MultiplayerController.getInstance();
    }

    public static Game getInstance() {
        return Game.HELPER.INSTANCE;
    }

    @Override
    public void execute() {
        loop();
    }

    private static class HELPER {
        private final static Game INSTANCE = new Game();
    }

}
