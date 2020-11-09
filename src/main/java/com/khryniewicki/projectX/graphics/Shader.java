package com.khryniewicki.projectX.graphics;

import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.ShaderUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {


    public static final int VERTEX_ATTRIB = 0;
    public static final int TCORD_ATTRIB = 1;
    public static Shader BG, HERO, OBSTACLE, TERRAIN, MOCKHERO, SPELL,TEXT,STRIP,SYMBOL;

    private boolean enabled = false;
    private final int ID;
    private Map<String, Integer> locationCache = new LinkedHashMap<>();

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }

    public static void loadAll() {

        BG = new Shader("bg.vert", "bg.frag");
        HERO = new Shader("hero.vert", "hero.frag");
        OBSTACLE = new Shader("object.vert", "object.frag");
        TERRAIN = new Shader("terrain.vert", "terrain.frag");
        MOCKHERO = new Shader("hero.vert", "hero.frag");
        SPELL = new Shader("spell.vert", "spell.frag");
        TEXT= new Shader("object.vert", "object.frag");
        STRIP=  new Shader("hero.vert", "hero.frag");
        SYMBOL=  new Shader("object.vert", "object.frag");

    }


    public int getUniform(String name) {
        if (locationCache.containsKey(name))
            return locationCache.get(name);

        int result = glGetUniformLocation(ID, name);
        if (result == -1) {
            System.err.println("Could not find uniform variable " + name + "'!");
        } else
            locationCache.put(name, result);
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector vector) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) enable();
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

}
