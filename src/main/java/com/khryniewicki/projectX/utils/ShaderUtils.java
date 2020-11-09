package com.khryniewicki.projectX.utils;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


public class ShaderUtils {

    private ShaderUtils() {
    }

    public static int load(String vertPath, String fragPath) {
        int result = 0;
        try {
            InputStream vertTransformed = FileUtils.pathTransformer("vert","shaders",vertPath);
            InputStream fragTransformed = FileUtils.pathTransformer("frag","shaders",fragPath);
            String vert = FileUtils.loadAsString(vertTransformed);
            String frag = FileUtils.loadAsString(fragTransformed);
            result=create(vert, frag);
        } catch (IOException e) {
            e.printStackTrace();
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
