package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.HelloWorld;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.util.Optional;

import static org.lwjgl.glfw.GLFW.*;

@Data
public class Spell implements UltraSpell {
    private VertexArray mesh;
    private Texture texture;

    boolean flag = true;
    private Vector position;
    private Float relativeX;

    private Float relativeY;
    private Float distanceX;

    public void setDistanceX(Float distanceX) {
        this.distanceX = distanceX;
    }

    public void setDistanceY(Float distanceY) {
        this.distanceY = distanceY;
    }

    private Float distanceY;
    private Texture throwingSpellTexture;

    public Float getDistanceX() {
        return distanceX;
    }

    public Float getDistanceY() {
        return distanceY;
    }

    @Override
    public Float getPositionX() {
        return Level.heroMock.getSpell().position.x;
    }

    @Override
    public Float getPositionY() {
        return Level.heroMock.getSpell().position.y;
    }

    @Override
    public Float getSize() {
        return SIZE;
    }

    public Texture getThrowingSpellTexture() {
        return throwingSpellTexture;
    }

    public void setThrowingSpellTexture(Texture throwingSpellTexture) {
        this.throwingSpellTexture = throwingSpellTexture;
    }

    private Texture consumedSpellTexture;
    private Float castingSpeed;
    private Long startingTimeSpell = null;
    private Long spellDuration;
    private String name;
    private boolean isCastingSpellsActivated = true;
    public Float SIZE = 1.0f;
    public float[] tcs;
    private float indexHeight = 1;
    private float indexWidth = 1;
    private Application.MyStompSessionHandler application = new Application.MyStompSessionHandler();

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

        return new VertexArray(vertices, indices, tcs);

    }

    @Override
    public void update() {
        getMousePosition();
        spellCasting();
    }

    @Override
    public void spellCasting() {
        if (relativeX != null && relativeY != null) {
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

        if (Math.abs(position.x - relativeX) <= castingSpeed / 2 && Math.abs(position.y - relativeY) < castingSpeed / 2) {
            setSpell(1f, 1f, consumedSpellTexture);
            setPosition(relativeX, relativeY, 1f);
            startingTimeSpell = System.currentTimeMillis();
            makeRelativesPositionsNull();
        }
    }


    private void sendSpellDTO() {
        SpellDTO spellDTO = new SpellDTO(name, relativeX, relativeY);
        application.sendSpellToStompSocket(spellDTO);
    }

    private void makeRelativesPositionsNull() {
        relativeY = null;
        relativeX = null;
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
        glfwSetMouseButtonCallback(HelloWorld.window, (window, key, action, mods) -> {
            DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
            glfwGetCursorPos(HelloWorld.window, xBuffer, yBuffer);
            double x = xBuffer.get(0);
            double y = yBuffer.get(0);
            if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE && isCastingSpellsActivated) {
                setRelativeX((float) (x - HelloWorld.width / 2) / (HelloWorld.width / 20));
                setRelativeY((float) (HelloWorld.height / 2 - y) / (HelloWorld.height / 10));
                distanceX = relativeX - getHeroPositionX();
                distanceY = relativeY - getHeroPositionY();
                setSpell(-Math.signum(distanceY), -Math.signum(distanceX), throwingSpellTexture);
                setPosition(getHeroPositionX(), getHeroPositionY(), 1f);
                sendSpellDTO();
            }
        });
    }


    @Override
    public Float getHeroPositionX() {
        return Level.getHero_x();
    }

    @Override
    public Float getHeroPositionY() {
        return Level.getHero_y();
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

    public Float getX() {
        return Optional.ofNullable(position.x).orElse(0f);
    }

    public Float getY() {
        return Optional.ofNullable(position.y).orElse(0f);
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

    public void setRelativeX(Float relativeX) {
        this.relativeX = relativeX;
    }

    public void setRelativeY(Float relativeY) {
        this.relativeY = relativeY;
    }

}
