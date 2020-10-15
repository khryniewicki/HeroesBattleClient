package com.khryniewicki.projectX.graphics;

import com.khryniewicki.projectX.utils.BufferUtilsOwn;
import com.khryniewicki.projectX.utils.FileUtils;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

@Data

public class Texture {

    private int width, height;
    private int texture;

    public Texture(String path) {
        texture = load(path);
    }

    private int load(String path) {
        int[] pixels = null;

        try {
            InputStream inputStream = FileUtils.pathTransformer("png", "img", path);
            BufferedImage image = ImageIO.read(inputStream);
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }
        int result = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, result);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtilsOwn.createIntBuffer(data));
        glBindTexture(GL_TEXTURE_2D, 0);
        return result;
    }



    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
