package com.khryniewicki.projectX.game.Attack;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.KnightIMG;
import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.time.LocalDateTime;
import java.util.Set;

import static org.lwjgl.glfw.GLFW.*;

@Data
public class Fire extends Spell {
    private Vector MyPosition;
    private Texture MyTexture;

    public Fire() {
        setPosition(new Vector());
        setMesh(createSpell());
        setTexture(KnightIMG.FIREBALL);
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
        setPositionZ(-0.1f);
        MyPosition = getPosition();
        MyTexture = getTexture();

    }

}

