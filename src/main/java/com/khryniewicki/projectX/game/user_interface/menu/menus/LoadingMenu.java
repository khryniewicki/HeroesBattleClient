package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellRegistry;
import com.khryniewicki.projectX.game.heroes.character.wizards.FireWizard;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.graphics.GameShaders;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Arrays;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.LOADING;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.LOGO;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_GREEN;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
@Getter
@Setter
public class LoadingMenu extends AbstractMenu {
    public static long window;
    private long duration;
    private long now;
    private boolean flag;
    private long difference;
    private SpellRegistry spellRegistry;
    private static final LoadingMenu instance = new LoadingMenu();

    private LoadingMenu() {
        super();
        start();
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
        window = glfwCreateWindow(width, height + bar, "Heroes Battle", NULL, NULL);
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
    public void execute() {
        begin();
        loop();
    }

    @Override
    public void prepare() {
        setVolatileImages(Arrays.asList(LOADING, LOGO));
        duration = 5000L;
        now = System.currentTimeMillis();
        flag = true;
    }

    @Override
    public void insideLoop() {
        do {
            setDifference(System.currentTimeMillis() - now);
            getProgress();
            if (flag) {
                initSpells();
                setDifference(System.currentTimeMillis() - now);
                getProgress();
                initSuperHeroes();
                flag = false;
            }
            glfwPollEvents();
            windowsShouldClose();
        } while (difference < duration && running);
    }

    private void initSpells() {
        SpellRegistry spellRegistry = SpellRegistry.getInstance();
        spellRegistry.fill();
    }

    private void initSuperHeroes() {
        new FireWizard();
    }

    private void getProgress() {
        long result = difference / 50;
        long progress = result < 100 ? result : 100;
        String text = "Loading " + progress + "%";
        update_volatile(LOADING, getTextureForLoading(text));
        render();
    }

    private Texture getTextureForLoading(String text) {
        return TextFactory.textInLoadingMenuToImage(text, BRIGHT_GREEN, 22);
    }

    public static LoadingMenu getInstance() {
        return instance;
    }

}
