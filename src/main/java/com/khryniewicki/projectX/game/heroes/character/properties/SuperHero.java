package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.settings.MoveSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class SuperHero implements UltraHero {

    private boolean isMovingLeft;
    private VertexArray mesh;
    private Texture texture, heroUp, heroDown, heroLeft, heroRight, heroIdle,heroAttack;
    private Vector position;

    private String name;
    private Integer life;
    private Integer mana;

    public static float hero_positionX0;
    public static float hero_positionY0;
    private float hero_left_offset,hero_right_offset,hero_bottom_offset;
    private float hero_top_offset;
    public float SIZE=1f;

    private LifeBar lifeBar;
    private ManaBar manaBar;
    private MoveSettings moveSettings;
    private StartingPosition startingPosition;

    private SpellInstance spellInstance;
    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;
    private BasicSpellInstance basicSpellInstance;
    private UltimateSpellInstance ultimateSpellInstance;


    public VertexArray createHero() {
        int i = isMovingLeft ? -1 : 1;

        float[] vertices = new float[]{
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 0.8f,
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 0.8f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 0.8f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 0.8f
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                i, 0,
                i, 1
        };

        return new VertexArray(vertices, indices, tcs);
    }


    public void update() {
        manaBar.renegerateMana();
    }


    public void render() {
        Shader.HERO.enable();
        Shader.HERO.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.HERO.disable();
        lifeBar.render();
        manaBar.render();
    }


    public synchronized Float getX() {
        return position.x;
    }

    public synchronized Float getY() {
        return position.y;
    }

    @Override
    public void setPositionX(Float positionX) {
        this.position.x = positionX;
    }

    @Override
    public void setPositionY(Float positionY) {
        this.position.y = positionY;
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }

    @Override
    public void setPosition() {
        setPosition(new Vector());
    }

    @Override
    public void setHeroIdle() {
        setTexture(heroIdle);
    }

    @Override
    public void setHeroRun() {
        setTexture(heroRight);
    }

    @Override
    public void setHeroAttack() {
        setTexture(heroAttack);
    }
}
