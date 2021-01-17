package com.khryniewicki.projectX.game.engine;

import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.graphics.GameShaders;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.utils.GameUtil;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;


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
        int index = 0;
        int reload_counter = 0;
        while (isRunning()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                if (index > 500) {
                    init_window();
                    reload();
                    index = 0;
                    System.out.println("RELOAD + "+reload_counter);
                    reload_counter++;
                }
                update();
                updates++;
                delta--;
                index++;
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

    default void init_window(){
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
        GameUtil.window = glfwCreateWindow(width, height + bar, "Heroes Battle", NULL, NULL);
        if (GameUtil.window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(GameUtil.window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {

            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(GameUtil.window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    GameUtil.window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(GameUtil.window);

        glfwShowWindow(GameUtil.window);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

        Shader.loadAll();
        GameShaders.loadAll();
        glfwShowWindow(GameUtil.window);
    };

    default void reload() {
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
            stop();
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
