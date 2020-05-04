package com.khryniewicki.projectX.game.Character;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.Attack.Ice;
import com.khryniewicki.projectX.game.Collision.Collision;
import com.khryniewicki.projectX.game.Attack.Fire;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.GameUtill;
import com.khryniewicki.projectX.utils.KnightIMG;
import lombok.Data;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

@Data

public class Hero extends SuperHero {
    private Vector MyPosition;
    private Texture MyTexture;
    private Application.MyStompSessionHandler application = new Application.MyStompSessionHandler();

    public Hero() {
        setPosition(new Vector());
        setMesh(isMovingLeft(false));
        setTexture(KnightIMG.SILVER_KNIGHT_IDLE);
        setPositionX(GameUtill.heroStartingPositionX);
        setPositionY(GameUtill.heroStartingPositionY);
        MyPosition = getPosition();
        MyTexture = getTexture();
        setSpell(new Fire());
    }


    public void update() {


        glfwSetKeyCallback(HelloWorld.window, (window, key, scancode, action, mods) -> {

                    if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_UP && action != GLFW_RELEASE && !Collision.collisions[2]) {
                        MyPosition.y += 0.2f;
                        MyTexture = KnightIMG.SILVER_KNIGHT_WALK_0;
                        setMesh(isMovingLeft(false));

                    } else if (key == GLFW.GLFW_KEY_DOWN && action != GLFW_RELEASE && !Collision.collisions[3]) {
                        MyPosition.y -= 0.2f;
                        MyTexture = KnightIMG.SILVER_KNIGHT_WALK_0;
                        setMesh(isMovingLeft(false));


                    } else if (key == GLFW.GLFW_KEY_LEFT && action != GLFW_RELEASE && !Collision.collisions[1]) {
                        MyPosition.x -= 0.2f;
                        MyTexture = KnightIMG.SILVER_KNIGHT_WALK_0;
                        setMesh(isMovingLeft(true));


                    } else if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT && action != GLFW_RELEASE && !Collision.collisions[0]) {
                        MyPosition.x += 0.2f;
                        MyTexture = KnightIMG.SILVER_KNIGHT_WALK_0;
                        setMesh(isMovingLeft(false));

                    } else {
                        MyTexture = KnightIMG.SILVER_KNIGHT_IDLE;
                        isMovingLeft = false;

                    }
                    application.sendHeroCoordinatesFromWebsocket();
                }

        );
    }


}

