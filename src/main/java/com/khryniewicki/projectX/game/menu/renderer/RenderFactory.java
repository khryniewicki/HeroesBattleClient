package com.khryniewicki.projectX.game.menu.renderer;

import com.khryniewicki.projectX.utils.TextUtil;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.khryniewicki.projectX.Game.window;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class RenderFactory {
    private TextureLoader textureLoader;
    public static final Map<String, TextureLoader> mapWithTextures = new HashMap<>();
    private RenderFactory() {
    }

    private final static RenderFactory RENDER_FACTORY = new RenderFactory();

    public static RenderFactory getRenderFactory() {
        return RENDER_FACTORY;
    }

    public void render(String path) {
         textureLoader = new TextureLoader(path);

        textScheme(path);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        textureLoader.render();

        swapBuffers();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void textScheme(String path) {

        if (!mapWithTextures.containsKey(TextUtil.ASK_FOR_CHAR))
            mapWithTextures.put(path, textureLoader);
        if (!path.equals(TextUtil.ASK_FOR_CHAR)) {
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            mapWithTextures.put(generatedString, textureLoader);
        }
        if (mapWithTextures.size()==20){
            mapWithTextures.clear();
        }
    }

    public static void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);

        glfwSwapBuffers(window);
    }


}
