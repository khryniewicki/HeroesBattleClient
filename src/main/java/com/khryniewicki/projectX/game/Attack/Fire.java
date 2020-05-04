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
    private Float relativeX;
    private Float relativeY;
    private float distanceX;
    private float distanceY;
    private Float castingSpeed;
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
    Long startSpell=null;
    @Override
    public void update() {
        getMousePosition();

        if (relativeX != null && relativeY != null) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                MyPosition.x += Math.signum(distanceX) * 0.2f;
                MyPosition.y += (distanceY) / Math.abs(distanceX) * 0.2f;
            } else {
                MyPosition.x += (distanceX) / Math.abs(distanceY) * 0.2f;
                MyPosition.y += Math.signum(distanceY) * 0.2f;
            }

            if (Math.abs(MyPosition.x - relativeX) < 0.1 && Math.abs(MyPosition.y - relativeY) < 0.1) {
                startSpell = System.currentTimeMillis();
                MyPosition.x = relativeX;
                MyPosition.y = relativeY;
                relativeY = null;
                relativeX = null;
                setTexture(KnightIMG.FIRE);
            }
        }
        if (startSpell!=null && System.currentTimeMillis()-startSpell>4000){
            setPositionZ(-1f);startSpell=null;}
    }

    public void getMousePosition() {
        glfwSetMouseButtonCallback(HelloWorld.window, (window, key, action, mods) -> {
            DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
            glfwGetCursorPos(HelloWorld.window, xBuffer, yBuffer);
            double x = xBuffer.get(0);
            double y = yBuffer.get(0);
            if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE) {
                setTexture(KnightIMG.FIREBALL);
                setPositionX(Level.getHero_x());
                setPositionY(Level.getHero_y());
                setRelativeX((float) (x - HelloWorld.width / 2) / (HelloWorld.width / 20));
                setRelativeY((float) (HelloWorld.height / 2 - y) / (HelloWorld.height / 10));
                setPositionZ(1f);
                distanceX = relativeX - Level.getHero_x();
                distanceY = relativeY - Level.getHero_y();
            }
        });
    }
}

