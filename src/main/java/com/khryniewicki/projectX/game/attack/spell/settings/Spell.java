package com.khryniewicki.projectX.game.attack.spell.settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spell.instance.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.properties.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.sending.StackEvent;
import lombok.Data;

@Data
public class Spell implements UltraSpell {

    private VertexArray mesh;
    private Texture texture;
    private Vector position = new Vector();
    public Float SIZE = 1f;

    private Position target;
    private SpellTexture missleSpell, executedSpell;
    private Texture icon, fadedIcon;
    private Float castingSpeed;
    private SpellTexture spellTexture;

    private Long startingTimeSpell = 0L;
    private Long spellDuration;
    private boolean isBasic;
    private boolean isSpellActivated;

    private float indexHeight = 1;
    private float indexWidth = 1;

    private String name;
    private Integer powerAttack;
    private Integer manaConsumed;

    private StackEvent stackEvent;
    protected SpellInstance spellInstance;
    protected UltraHero hero;
    protected AttackTrajectory attackTrajectory;
    private StartingPosition startingPosition;

    public Spell() {
    }

    public Spell(SpellInstance spellInstance) {
        createHero();
        this.spellInstance = spellInstance;
        this.stackEvent = StackEvent.getInstance();
        this.attackTrajectory = new AttackTrajectory(this, hero);
        setSpellDetails();
    }

    @Override
    public void createHero() {
        if (hero == null) {
            HeroesInstances heroesInstances = HeroesInstances.getInstance();
            hero = heroesInstances.getHero();
        }
    }

    public void setSpellDetails() {
        createProperties();
        setPosition();
        this.mesh = createMesh();
    }

    public VertexArray createMesh() {
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
                0, indexHeight,
                0, 0,
                indexWidth, 0,
                indexWidth, indexHeight
        };

        return new VertexArray(vertices, indices, tcs);
    }


    @Override
    public void update() {
        spellCasting();
    }

    @Override
    public void render() {
        Shader.SPELL.enable();
        Shader.SPELL.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.SPELL.disable();
    }

    protected void createProperties() {
        this.name = spellInstance.getName();
        this.castingSpeed = spellInstance.getCastingSpeed();
        this.executedSpell = spellInstance.getConsumedSpellTexture();
        this.missleSpell = spellInstance.getThrowingSpellTexture();
        this.texture = missleSpell.getTexture();
        this.SIZE = missleSpell.getSize();
        this.powerAttack = spellInstance.getPowerAttack();
        this.manaConsumed = spellInstance.getManaConsumed();
    }

    public void setPosition() {
        startingPosition = hero.getStartingPosition();
        setPositionX(startingPosition.getX());
        setPositionY(startingPosition.getY());
        setPositionZ(-0.1f);
    }

    @Override
    public Float getHeroPositionX() {
        return hero.getX();
    }

    @Override
    public Float getHeroPositionY() {
        return hero.getY();
    }

    @Override
    public Float getSize() {
        return SIZE;
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


    public void setPosition(Float x, Float y, Float z) {
        setPositionZ(z);
        setPositionX(x);
        setPositionY(y);
    }

    public void setImage(Float indexHeight, Float indexWidth, SpellTexture spellTexture) {
        setIndexHeight(indexHeight);
        setIndexWidth(indexWidth);
        setSIZE(spellTexture.getSize());
        setMesh(createMesh());
        setTexture(spellTexture.getTexture());
    }

    @Override
    public void spellCasting() {
        attackTrajectory.casting_spell();
    }

    public void setMesh() {
        this.mesh = createMesh();
    }
}
