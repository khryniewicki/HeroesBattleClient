package com.khryniewicki.projectX.game.engine;

import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.graphics.GameShaders;
import com.khryniewicki.projectX.graphics.Shader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.springframework.stereotype.Service;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@Data
@Service
@Slf4j
public class GameLoopImp implements GameLoop {
    protected boolean running;
    public static long window;
    public static int width = 1600;
    public static int height = 800;
    public static int bar = 40;
    protected static GameState state;


    public void loop() {
        if (!state.equals(GameState.FINISH)) {
            prepare();
            insideLoop();
        }
    }

    protected void prepare() {

    }

    @Override
    public void insideLoop() {

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
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

    }

    public void player_is_dead() {
        setState(GameState.PLAYER_IS_DEAD);
    }

    public void restart() {
        setState(GameState.RESTART);
    }

    public void finish_game() {
        setState(GameState.FINISH);
    }

    public void ok() {
        setState(GameState.OK);
    }

    private void setState(GameState game_state) {
        state = game_state;
        log.info("{}", state);
    }


    @Override
    public void update() {
    }

    @Override
    public void render() {
    }

    @Override
    public void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);
        glfwSwapBuffers(window);
    }

    @Override
    public void clearBuffers() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }


    protected void windowsShouldClose() {
        if (glfwWindowShouldClose(window)) {
            setRunning(false);
            finish_game();
        }
    }


    protected void terminateIfWindowShutDown() {
        if (state.equals(GameState.FINISH)) {
            glfwDestroyWindow(window);
            glfwTerminate();
            WebsocketScheduler websocketScheduler = WebsocketScheduler.getInstance();
            websocketScheduler.cancelTimer();
        }
    }

    @Override
    public void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        // Create the window
        window = glfwCreateWindow(width, height + bar, "HeroesBattle", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);

        glfwShowWindow(window);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

        Shader.loadAll();
        GameShaders.loadAll();
        glfwShowWindow(window);
    }


    @Override
    public void begin() {
        setRunning(true);
    }

    @Override
    public void stop() {
        setRunning(false);
    }

}
