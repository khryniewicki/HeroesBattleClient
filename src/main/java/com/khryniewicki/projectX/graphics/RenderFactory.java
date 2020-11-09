package com.khryniewicki.projectX.graphics;

import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;

import java.util.ArrayList;
import java.util.List;

import static com.khryniewicki.projectX.game.engine.Game.window;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class RenderFactory {
    private float level = 4.5f;
    public List<MenuSymbol> listWithConnectionTexts = new ArrayList<>();

    private RenderFactory() {
    }
    private final static RenderFactory RENDER_FACTORY = new RenderFactory();
    public static RenderFactory getRenderFactory() {
        return RENDER_FACTORY;
    }

    public void render(String text) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        if (level < -4f) {
            listWithConnectionTexts = new ArrayList<>();
        }
        listWithConnectionTexts.add(createText(text));
        listWithConnectionTexts.forEach(MenuSymbol::render);
        swapBuffers();
        level -= 0.7f;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MenuSymbol createText(String text) {
        return new Button.Builder()
                .withTexture(TextFactory.textInConnectionWindow(text))
                .withName(text)
                .withHeight(0.8f)
                .withWidth(7f)
                .withPositionX(-9.5f)
                .withPositionY(level)
                .build();
    }

    public static void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);
        glfwSwapBuffers(window);
    }


}
