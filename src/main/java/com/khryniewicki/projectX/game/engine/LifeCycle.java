package com.khryniewicki.projectX.game.engine;

import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

@Getter
@Setter
@Service
@Slf4j
public abstract class LifeCycle implements Command {
    protected volatile boolean running;
    public static long window;
    public static int width = 1600;
    public static int height = 800;
    public static int bar = 40;
    private volatile boolean loopTerminated;

    public void loop() {
        if (!Application.state.equals(GameState.FINISH)) {
            prepare();
            insideLoop();
        }
    }
    public  void init(){}

    protected  void prepare(){}

    public  void update(){}

    public  void render(){}

    public void restart(){}

    public void insideLoop() {

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        terminateIfWindowShutDown();
        setLoopTerminated(false);
        while (running) {
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
                glfwSetWindowTitle(window, "HeroesBattle  | " + updates + " ups, " + frames + " fps ");
                updates = 0;
                frames = 0;
            }
            windowsShouldClose();
        }
        terminateIfWindowShutDown();
    }




    public void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);
        glfwSwapBuffers(window);
    }


    public void clearBuffers() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }


    protected void windowsShouldClose() {
        if (glfwWindowShouldClose(window)) {
            Application.finish_game();
        }
    }

    protected void terminateIfWindowShutDown() {
        if (Application.state.equals(GameState.FINISH)) {
            glfwTerminate();
            WebsocketScheduler websocketScheduler = WebsocketScheduler.getInstance();
            websocketScheduler.cancelTimer();
        }
    }

    public void begin() {
        setRunning(true);
    }

    public void stop() {
        setRunning(false);
    }

}
