package com.khryniewicki.projectX.game.Character;

import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroReceiveService;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.KnightIMG;
import lombok.Data;

@Data
public class HeroMock extends SuperHero {
    private Vector MyPosition;
    private Texture MyTexture;
    private VertexArray MyMesh;
    private Float positionFromClientX;
    private Float positionFromClientY;
    private Float startingPositionX = GameUtill.mockStartingPositionX;
    private Float startingPositionY = GameUtill.mockStartingPositionY;


    public HeroMock() {
        setPosition(new Vector());
        setMesh(createHero());
        setTexture(KnightIMG.BRONZE_KNIGHT_IDLE);
        MyPosition=new Vector();
        setPositionX(startingPositionX);
        setPositionY(startingPositionY);
        MyTexture = getTexture();
        MyMesh = getMesh();
    }

    @Override
    public void render() {
        Shader.MOCKHERO.enable();
        Shader.MOCKHERO.setUniformMat4f("ml_matrix", Matrix4f.translate(MyPosition));
        MyTexture.bind();
        MyMesh.render();
        Shader.MOCKHERO.disable();
    }


    @Override
    public void update() {
        HeroDTO heroMockPositions = HeroReceiveService.getHeroMockPositions();
        setPositionX(heroMockPositions.getPositionX());
        setPositionY(heroMockPositions.getPositionY());
    }

    public void setPositionX(Float positionX) {
        this.MyPosition.x = positionX;
    }

    public void setPositionY(Float positionY) {
        this.MyPosition.y = positionY;
    }
}
