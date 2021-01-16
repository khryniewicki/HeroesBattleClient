package com.khryniewicki.projectX.game.engine;

import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.utils.GameUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public interface LifeCycle extends Command {
    int width = 1600;
    int height = 800;
    int bar = 40;

    default void prepare() {
    }

    void setRunning(boolean sth);

    boolean isRunning();

    default void loop() {
        if (!Application.state.equals(GameState.FINISH)) {
            prepare();
            insideLoop();
        }
    }

    default void insideLoop() {

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        terminateIfWindowShutDown();
        while (isRunning()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                glfwSetWindowTitle(getWindow(), "HeroesBattle  | " + updates + " ups, " + frames + " fps ");
                updates = 0;
                frames = 0;
            }
            windowsShouldClose();
        }
        terminateIfWindowShutDown();
    }

    default void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);
        glfwSwapBuffers(getWindow());
    }

    default void init() {
    }

    default void update() {
    }

    default void render() {
    }

    default void restart() {
    }

    default void clearBuffers() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }


    default void windowsShouldClose() {
        if (glfwWindowShouldClose(getWindow())) {
            Application.finish_game();
        }
    }

    default void terminateIfWindowShutDown() {
        if (Application.state.equals(GameState.FINISH)) {
            glfwTerminate();
            WebsocketScheduler websocketScheduler = WebsocketScheduler.getInstance();
            websocketScheduler.cancelTimer();
        }
    }

    default void begin() {
        setRunning(true);
    }

    default void stop() {
        setRunning(false);
    }

    default long getWindow() {
        return GameUtil.window;
    }

}
