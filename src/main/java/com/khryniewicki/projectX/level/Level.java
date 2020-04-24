package com.khryniewicki.projectX.level;

import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;

public class Level {

    private VertexArray background;
    private Texture bgTexture;

    private int xScroll = 0;

    public Level() {
        float[] vertices = new float[]{
                -10.0f, -10.0f * 9.0f / 16.0f, 0.0f,
                -10.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                0.0f, 10.0f * 9.0f / 16.0f, 0.0f,
                0.0f, -10.0f * 9.0f / 16.0f, 0.0f
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        background= new VertexArray(vertices,indices,tcs);
        bgTexture=new Texture("res/bg.jpeg");
    }

    public void update(){
        xScroll--;
    }


    public void render(){
        bgTexture.bind();
        Shader.BG.enable();

        background.render();
        Shader.BG.disable();
        bgTexture.unbind();

    }
}


