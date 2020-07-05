package com.khryniewicki.projectX.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


public class ShaderUtils {

    private ShaderUtils() {
    }

    public static int load(String vertPath, String fragPath) {
        int result = 0;
        try {
            String vertTransformed = pathTransformer("vert",vertPath);
            String fragTransformed = pathTransformer("frag",fragPath);
            String vert = FileUtils.loadAsString(vertTransformed);
            String frag = FileUtils.loadAsString(fragTransformed);
            result=create(vert, frag);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
    public static String pathTransformer(String ending,String path) throws IOException {
        String result = null;

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/shaders/**/*."+ending);

        for (Resource resource : resources) {
            if (path.equals(resource.getFilename())) {
                result = resource.getFile().getAbsolutePath();
                return  result;
            }

        }
        return result;
    }

    public static int create(String vert, String frag) {
        int program = glCreateProgram();
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        glCompileShader(vertID);
        int shaderI = glGetShaderi(vertID, GL_COMPILE_STATUS);

        if (shaderI == GL_FALSE) {
            System.err.println("Failed to compile vertex shader!");
            System.err.println(glGetShaderInfoLog(vertID));
            return -1;
        }

        glCompileShader(fragID);

        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile fragment shader!");
            System.err.println(glGetShaderInfoLog(fragID));
            return -1;
        }

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);

        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
    }
}
