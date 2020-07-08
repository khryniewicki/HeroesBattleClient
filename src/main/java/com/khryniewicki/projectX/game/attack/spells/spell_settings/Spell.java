package com.khryniewicki.projectX.game.attack.spells.spell_settings;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.heroes.character.UltraHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;

@Data
public class Spell implements UltraSpell {

    private VertexArray mesh;
    private Texture texture;
    private Vector position=new Vector();
    public Float SIZE = 1f;

    private Position target;
    private Float finalX,finalY;
    private Texture throwingSpellTexture,consumedSpellTexture;
    private Float castingSpeed;


    private Long startingTimeSpell = null;
    private Long spellDuration;
    private boolean isBasic;

    private float indexHeight = 1;
    private float indexWidth = 1;

    private String name;
    private Integer powerAttack;
    private Integer manaConsumed;

    private StackEvent stackEvent;
    private SpellInstance spellInstance;
    private UltraHero ultraHero;
    private final AttackTrajectory attackTrajectory;
    private StartingPosition startingPosition;


    public Spell() {
        this.attackTrajectory = new AttackTrajectory(this);
        createHero();
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
                0, indexHeight * 1,
                0, 0,
                indexWidth * 1, 0,
                indexWidth * 1, indexHeight * 1
        };

        createHero();
        stackEvent = StackEvent.getInstance();
        return new VertexArray(vertices, indices, tcs);
    }

    @Override
    public void createHero() {
        if (ultraHero == null) {
            HeroesInstances instance = HeroesInstances.getInstance();
            ultraHero = instance.getHero();
        }
    }


    @Override
    public void update() {
        spellCasting();
    }

    public void spellCasting() {
        attackTrajectory.spellCasting();
    }

    @Override
    public void render() {
        Shader.SPELL.enable();
        Shader.SPELL.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.SPELL.disable();
    }

    @Override
    public void setSpellInstance(SpellInstance spellInstance) {
        this.spellInstance = spellInstance;
        setSpellDetails();
    }

    public void setSpellDetails(){
        createProperties();
        setPosition();
        setMesh(createMesh());
    }

    public void createProperties() {
        name=spellInstance.getName();
        castingSpeed = spellInstance.getCastingSpeed();
        consumedSpellTexture = spellInstance.getConsumedSpellTexture();
        throwingSpellTexture = spellInstance.getThrowingSpellTexture();
        texture = throwingSpellTexture;
        powerAttack = spellInstance.getPowerAttack();
        manaConsumed = spellInstance.getManaConsumed();
    }


    public void setPosition() {
        startingPosition = ultraHero.getStartingPosition();
        setPositionX(startingPosition.getX());
        setPositionY(startingPosition.getY());
        setPositionZ(-0.1f);
    }

    @Override
    public Float getHeroPositionX() {
     return ultraHero.getX();
    }

    @Override
    public Float getHeroPositionY() {
        return ultraHero.getY() ;
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

    public void setImage(Float indexHeight, Float indexWidth, Texture texture) {
        setIndexHeight(indexHeight);
        setIndexWidth(indexWidth);
        setMesh(createMesh());
        setTexture(texture);
    }

    public void setMesh() {
        this.mesh = createMesh();
    }
}
