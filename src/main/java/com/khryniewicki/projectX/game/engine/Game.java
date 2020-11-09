package com.khryniewicki.projectX.game.engine;


import com.khryniewicki.projectX.game.multiplayer.MultiplayerController;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketApplication;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketInitializer;
import com.khryniewicki.projectX.game.multiplayer.websocket.messages.LoadedStatus;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.graphics.RenderFactory;
import com.khryniewicki.projectX.services.SendingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

@Component
@Getter
@Setter
public class Game extends GameLoopImp implements Runnable {

    private Board board;
    public static CountDownLatch latch;
    private final WebsocketInitializer websocketInitializer;
    private final HeroesInstances heroesInstances;
    private final MultiplayerController multiplayerController;


    public Game() {
        heroesInstances = HeroesInstances.getInstance();
        websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        multiplayerController = new MultiplayerController();
    }

    public static void main(String[] args) {
        new Game().begin();
    }

    public void start() {
        latch = new CountDownLatch(1);
        begin();
        Thread game = new Thread(this, "Game");
        game.start();
    }

    public void run() {
        init();
        initializeMultiplayerGame();
        loop();
        terminateGame();
    }

    @Override
    public void init() {
        super.init();
    }

    private void initializeMultiplayerGame() {
        initializeMenu();
        initializeWebsocketConnection();
        setMultiplayerGame();
    }

    private void initializeMenu() {
        LoadingMenu loadingMenu = LoadingMenu.getInstance();
        loadingMenu.execute();
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.render();
        mainMenu.execute();
    }

    private void initializeWebsocketConnection() {
        new WebsocketApplication().startWebsocket();
    }

    private void setMultiplayerGame() {

        if (isHeroLoadedProperly()) {
            multiplayerController.waitingForSecondPlayer();
            createBoard();
            createSendingService();
        } else {
            multiplayerController.occupiedRoom();
            stop();
        }
    }

    private void createSendingService() {
        SendingService heroSending = new SendingService();
        Thread sender = new Thread(heroSending);
        sender.start();
    }


    private boolean isHeroLoadedProperly() {
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
        RenderFactory.swapBuffers();
    }


    private void terminateGame() {
        websocketInitializer.disconnect();
        glfwDestroyWindow(window);
        glfwTerminate();
    }


}
