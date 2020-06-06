package com.khryniewicki.projectX;


import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.Message;
import com.khryniewicki.projectX.game.board.Board;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.menu.MultiplayerInitializer;
import com.khryniewicki.projectX.game.menu.WebsocketInitializer;
import com.khryniewicki.projectX.game.menu.heroStorage.SuperHeroInstance;
import com.khryniewicki.projectX.game.menu.renderer.RenderFactory;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.utils.TextUtil;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.springframework.stereotype.Component;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import static com.khryniewicki.projectX.game.menu.MultiplayerInitializer.getWizardType;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@Component
public class Game implements Runnable {


    public static int width = 1600;
    public static int height = 800;

    private Thread thread;
    private boolean running = false;
    public static long window;
    private String inputText;
    public RenderFactory renderFactory;
    private Board board;
    public static CountDownLatch latch;
    public static SuperHero wizard;
    public static boolean isHeroEstablishedCorrectly;
    private WebsocketInitializer websocketInitializer;
    public static HashMap<String, Message> mapWithHeroes;
    private SuperHeroInstance superHeroInstance;

    public void start() {
        latch = new CountDownLatch(1);
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        // Create the window
        window = glfwCreateWindow(width, height, "Project X", NULL, NULL);
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
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
//        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);


        GL.createCapabilities();

        glfwMakeContextCurrent(window);

        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        Shader.loadAll();

        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
        createRenderFactoryInstance();
        createWebsocketInitializerInstance();
        createSuperHeroInstance();

        loadGraphicForObjects(pr_matrix);


    }

    private void createRenderFactoryInstance() {
        renderFactory = RenderFactory.getRenderFactory();
    }

    private void createWebsocketInitializerInstance() {
        websocketInitializer = WebsocketInitializer.getWebsocketInstance();
    }

    private void createSuperHeroInstance() {
        superHeroInstance = SuperHeroInstance.getInstance();
    }



    private void loadGraphicForObjects(Matrix4f pr_matrix) {
        Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BG.setUniform1i("tex", 1);
        Shader.OBSTACLE.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.OBSTACLE.setUniform1i("tex", 1);
        Shader.TERRAIN.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.TERRAIN.setUniform1i("tex", 1);


        Shader.HERO.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.HERO.setUniform1i("tex", 1);
        Shader.SPELL.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SPELL.setUniform1i("tex", 1);

        Shader.TEXT.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.TEXT.setUniform1i("tex", 1);

    }

    public void run() {
        init();


        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        initializeMultiplayerGame();

        createBoard();


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
                glfwSetWindowTitle(window, "Project X | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
            if (glfwWindowShouldClose(window))
                running = false;
        }
        websocketInitializer.disconnect();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void initializeMultiplayerGame() {
        MultiplayerInitializer multiplayerInitializer = new MultiplayerInitializer();
        multiplayerInitializer.getHeroFromPlayer();
        initializeWebsocketConnection();
        setMultiplayerGame();
    }


    private void initializeWebsocketConnection() {
        renderFactory.render(TextUtil.CONNECTION);
        new Application().startWebsocket();
        renderFactory.render(TextUtil.CONNECTION_ESTABLISHED);
    }


    private void setMultiplayerGame() {
        if (isInitialHeroPropertiesLoadedProperly()) {
            renderFactory.render(TextUtil.OTHER_PLAYER);
            latch = new CountDownLatch(1);

            try {
                websocketInitializer.getSecondPlayerMockType();
                latch.await();
                superHeroInstance.setMock();

                renderFactory.render(TextUtil.GET_READY);
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else {
            renderFactory.render(TextUtil.TRY_LATER);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            running = false;
        }
    }


    private boolean isInitialHeroPropertiesLoadedProperly() {
        websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        superHeroInstance.setHero(getWizardType());

        Thread websocket = new Thread(websocketInitializer, "websocket");
        websocket.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isHeroEstablishedCorrectly;
    }

    private void createBoard() {
        board = new Board();
    }

    private void update() {
        glfwPollEvents();
        board.update();
    }


    private void render() {
        int error2 = glGetError();
        if (error2 != GL_NO_ERROR)
            System.out.println("ERROR2: " + error2);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        board.render();

        RenderFactory.swapBuffers();
    }


    public static void main(String[] args) {
        new Game().start();
    }


}
