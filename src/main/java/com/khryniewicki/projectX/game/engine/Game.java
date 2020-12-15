package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.LoadedStatus;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.services.SendingService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;

@Component
@Getter
@Setter
@Slf4j
public class Game extends GameLoopImp implements Runnable {

    private Board board;
    public static CountDownLatch latch;
    private static WebsocketInitializer websocketInitializer;
    private final HeroesInstances heroesInstances;
    private final MultiplayerController multiplayerController;
    private WebsocketApplication websocketApplication;

    private Game() {
        heroesInstances = HeroesInstances.getInstance();
        websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        multiplayerController = new MultiplayerController();
    }


    public void start() {
        latch = new CountDownLatch(1);
        Thread game = new Thread(this, "Game");
        game.start();
        state = GameState.OK;
    }

    public void run() {
        init();
        initLoadingMenu();
        initializeMultiplayerGame();
        loop();
        terminateGame();
    }


    public void initializeMultiplayerGame() {
        initializeMenu();
        if (state.equals(GameState.OK)) {
            initializeWebsocketConnection();
            setMultiplayerGame();
        }
    }

    private void initializeMenu() {
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.execute();
    }

    private void initLoadingMenu() {
        LoadingMenu loadingMenu = LoadingMenu.getInstance();
        loadingMenu.execute();
    }

    private void initializeWebsocketConnection() {
        websocketApplication = new WebsocketApplication();
        websocketApplication.startWebsocket();
    }

    private void setMultiplayerGame() {
        if (setHeroOnPosition()) {
            multiplayerController.waitingForSecondPlayer();
            createBoard();
            createSendingService();
            begin();
        } else {
            stop();
        }

    }

    private void createSendingService() {
        SendingService heroSending = new SendingService();
        Thread sender = new Thread(heroSending);
        sender.start();
    }


    private boolean setHeroOnPosition() {
        registerHero();
        return LoadedStatus.INSTANCE().isHeroLoaded;
    }

    private void registerHero() {
        setHeroesInitialPositions();
        heroesInstances.setHeroBasicProperties();
    }

    private void setHeroesInitialPositions() {
        Thread websocket = new Thread(websocketInitializer, "websocket");
        websocket.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        int error2 = glGetError();
        if (error2 != GL_NO_ERROR)
            System.out.println("ERROR2: " + error2);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        board.render();
        swapBuffers();
    }


    public void terminateGame() {
        if (!websocketInitializer.getSessionId().isEmpty()) {
            websocketInitializer.disconnect();
        }
        terminateIfWindowShutDown();
    }

    public static Game getInstance() {
        return Game.HELPER.WAITING_ROOM_MENU;
    }

    private static class HELPER {
        private final static Game WAITING_ROOM_MENU = new Game();
    }

}
