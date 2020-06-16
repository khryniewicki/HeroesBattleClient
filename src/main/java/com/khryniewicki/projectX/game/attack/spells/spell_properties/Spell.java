package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

@Data


public class Spell implements UltraSpell {
    private VertexArray mesh;
    private Texture texture;
    private static SuperHero superHero;
    private Vector position;
    private Float finalX;
    private Float finalY;

    private Float distanceX;
    private Float distanceY;

    private Texture throwingSpellTexture;

    private Texture consumedSpellTexture;
    private Float castingSpeed;
    private Long startingTimeSpell = null;
    private Long spellDuration;
    private String name;
    private boolean isCastingSpellsActivated = true;
    public Float SIZE = 1f;
    public float[] tcs;
    private float indexHeight = 1;
    private float indexWidth = 1;
    private Integer powerAttack;
    private Integer manaConsumed;

    private Application.MyStompSessionHandler application = new Application.MyStompSessionHandler();

    public Float getDistanceX() {
        return distanceX;
    }

    public Float getDistanceY() {
        return distanceY;
    }

    @Override
    public Float getSize() {
        return SIZE;
    }

    public Texture getThrowingSpellTexture() {
        return throwingSpellTexture;
    }

    public void setDistanceX(Float distanceX) {
        this.distanceX = distanceX;
    }

    public void setDistanceY(Float distanceY) {
        this.distanceY = distanceY;
    }

    public void setThrowingSpellTexture(Texture throwingSpellTexture) {
        this.throwingSpellTexture = throwingSpellTexture;
    }

    public VertexArray createSpell() {
        float[] vertices = new float[]{
                0f + -SIZE / 2.0f, 0f + -SIZE / 2.0f, -0.1f,
                0f + -SIZE / 2.0f, 0f + SIZE / 2.0f, -0.1f,
                0f + SIZE / 2.0f, 0f + SIZE / 2.0f, -0.1f,
                0f + SIZE / 2.0f, 0f + -SIZE / 2.0f, -0.1f
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, indexHeight * 1,
                0, 0,
                indexWidth * 1, 0,
                indexWidth * 1, indexHeight * 1
        };
        texture = throwingSpellTexture;
        HeroesInstances instance = HeroesInstances.getInstance();
        superHero = instance.getHero();

        return new VertexArray(vertices, indices, tcs);

    }

    @Override
    public void update() {
        getMousePosition();
        spellCasting();
    }

    @Override
    public void spellCasting() {
        if (finalX != null && finalY != null) {
            castingSpell();
        }
        spellDuration();
    }

    private void spellDuration() {
        if (startingTimeSpell != null) {
            if (startingTimeSpell > 0L) setCastingSpellsActivated(false);
            if (System.currentTimeMillis() - startingTimeSpell > spellDuration) {
                setPositionZ(-1f);
                startingTimeSpell = null;
                setCastingSpellsActivated(true);
            }
        }
    }

    public void castingSpell() {
        if (Math.abs(distanceX) > Math.abs(distanceY)) {
            position.x += Math.signum(distanceX) * castingSpeed;
            position.y += (distanceY) / Math.abs(distanceX) * castingSpeed;

        } else {
            position.x += (distanceX) / Math.abs(distanceY) * castingSpeed;
            position.y += Math.signum(distanceY) * castingSpeed;
        }

        if (Math.abs(position.x - finalX) <= castingSpeed / 2 && Math.abs(position.y - finalY) <= castingSpeed / 2) {
            setSpell(1f, 1f, consumedSpellTexture);
            setPosition(finalX, finalY, 1f);
            startingTimeSpell = System.currentTimeMillis();
            makeRelativesPositionsNull();
        }
    }


    private void sendSpellDTO() {
        SpellDTO spellDTO = new SpellDTO(name, finalX, finalY);
        application.sendSpellToStompSocket(spellDTO);
    }

    private void makeRelativesPositionsNull() {
        finalY = null;
        finalX = null;
    }

    public Vector getPosition() {
        return position;
    }
    private void setPosition(Float x, Float y, Float z) {
        setPositionZ(z);
        setPositionX(x);
        setPositionY(y);
    }



    public void setSpell(Float indexHeight, Float indexWidth, Texture texture) {
        setIndexHeight(indexHeight);
        setIndexWidth(indexWidth);
        setMesh(createSpell());
        setTexture(texture);
    }

    @Override
    public void getMousePosition() {
        glfwSetMouseButtonCallback(Game.window, (window, key, action, mods) -> {
            DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
            glfwGetCursorPos(Game.window, xBuffer, yBuffer);
            double x = xBuffer.get(0);
            double y = yBuffer.get(0);
            if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE && isCastingSpellsActivated) {
                System.out.println("X:"+x+ " Y:" +y);
                this.setFinalX((float) (x - Game.width / 2) / (Game.width / 20));
                this.setFinalY((float) (Game.height / 2 - y) / (Game.height / 10));

                distanceX = finalX - getHeroPositionX();
                distanceY = finalY - getHeroPositionY();

                setSpell(-Math.signum(distanceY), -Math.signum(distanceX), throwingSpellTexture);
                setPosition(getHeroPositionX(), getHeroPositionY(), 1f);
                sendSpellDTO();
                System.out.println("finalX:"+finalX +" , finalY"+finalY);
            }
        });
    }


    @Override
    public Float getHeroPositionX() {
        return superHero.getX();
    }

    @Override
    public Float getHeroPositionY() {
        return superHero.getY()+superHero.getTexture().getHeight()/2000;
    }

    @Override
    public void render() {
        Shader.SPELL.enable();
        Shader.SPELL.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        getMesh().render();
        Shader.SPELL.disable();
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setMesh(VertexArray mesh) {
        this.mesh = mesh;
    }


    public void setPositionX(Float positionX) {
        this.position.x = positionX;
    }

    public void setPositionY(Float positionY) {
        this.position.y = positionY;
    }

    public void setPositionZ(Float positionZ) {
        this.position.z = positionZ;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setFinalX(Float finalX) {
        this.finalX = finalX;
    }

    public void setFinalY(Float finalY) {
        this.finalY = finalY;
    }

}
