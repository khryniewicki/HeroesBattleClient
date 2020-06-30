package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.DTO.DTO;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.services.SpellSendingService;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.lwjgl.glfw.GLFW.*;

@Data
public class Spell implements UltraSpell {
    private VertexArray mesh;
    private Texture texture;
    private static SuperHero hero;
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
    private SpellSendingService sendSpellToStompSocket;
    private SendingService sendingService;
    private Integer manaConsumed;
    private StackEvent stackEvent;

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
        getHero();
        sendingService = new SendingService();
        stackEvent=StackEvent.getInstance();
        return new VertexArray(vertices, indices, tcs);
    }

    private void getHero() {
        if (hero == null) {
            HeroesInstances instance = HeroesInstances.getInstance();
            hero = instance.getHero();
            sendingService = new SendingService();
        }
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
            makeFinalPositionsNull();
        }
    }


    private void sendSpellDTO() {
        ConcurrentLinkedDeque<DTO> heroDTOS = stackEvent.getEvents();
        heroDTOS.offerLast(new SpellDTO(name, finalX, finalY));
    }

    private void makeFinalPositionsNull() {
        finalY = null;
        finalX = null;
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
                setSpellCountingTime();
                consumeSpellMana();
                this.setFinalX((float) (x - Game.width / 2) / (Game.width / 20));
                float factor=1.1f;

                this.setFinalY((float) ((Game.height / 2 -  y)*factor )/ (Game.height / 10));
                float X = getHeroPositionX();
                float Y = getHeroPositionY();

                distanceX = finalX - X;
                distanceY = finalY - Y;

                setSpell(-Math.signum(distanceY), -Math.signum(distanceX), throwingSpellTexture);
                setPosition(X, Y, 1f);
                sendSpellDTO();
            }
        });
    }

    public void consumeSpellMana() {
        getHero();
        Integer heroMana = hero.getMana();
        hero.setMana(heroMana - manaConsumed);

        ManaBar manaBar = hero.getManaBar();
        manaBar.updateManaBar();

        sendingService.updatePosition();
    }

    public void setSpellCountingTime() {
        startingTimeSpell = System.currentTimeMillis();
    }

    @Override
    public void render() {
        Shader.SPELL.enable();
        Shader.SPELL.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        getMesh().render();
        Shader.SPELL.disable();
    }


    public Vector getPosition() {
        return position;
    }

    @Override
    public Float getHeroPositionX() {
        return hero.getX();
    }

    @Override
    public Float getHeroPositionY() {
        return hero.getY() + hero.getTexture().getHeight() / 2000;
    }

    @Override
    public Integer getManaConsumed() {
        return manaConsumed;
    }

    @Override
    public Integer getPowerAttack() {
        return powerAttack;
    }

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

    public void setDistanceX(Float distanceX) {
        this.distanceX = distanceX;
    }

    public void setDistanceY(Float distanceY) {
        this.distanceY = distanceY;
    }

    public void setThrowingSpellTexture(Texture throwingSpellTexture) {
        this.throwingSpellTexture = throwingSpellTexture;
    }
}
