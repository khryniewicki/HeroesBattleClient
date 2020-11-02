package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
@Slf4j
public class CreateTable {


    public static Texture tableImage(String heroName) {
        HeroFactory heroFactory=HeroFactory.getInstance();
        SuperHero superHero = heroFactory.create(heroName);
        BufferedImage image = new Texture("blankTableWindow.png").getImage();
        createTableImage(image,superHero);
        return new Texture(image);
    }

    public static void createTableImage(BufferedImage image, SuperHero superHero) {
        BasicSpellInstance basicSpellInstance = superHero.getBasicSpellInstance();
        UltimateSpellInstance ultimateSpellInstance = superHero.getUltimateSpellInstance();
        Font font = new Font("Open Sans", Font.BOLD, 20);

        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setColor(Color.BLUE);

        String[] heroHeader = {"Life", "Mana", "Speed", "Basic Attack", "Ultimate Attack"};
        String[] heroData = {superHero.getLife().toString(), superHero.getMana().toString(), "20", "AAA", "BBB"};

//        String[] heroData = {superHero.getLife().toString(), superHero.getMana().toString(), "20", basicSpellInstance.getName(), ultimateSpellInstance.getName()};
        String[] spellsHeader={"Spell", "Damage", "Mana Cost", "Speed", "Casting speed"};
        String[] spelldata1={"AAA", "15", "10", "20", "4 sec."};
        String[] spelldata2={"Fire Bomb", "25", "20", "20", "8 sec."};

        for (int i = 0; i < heroHeader.length; i++) {
            int y = 20 + i * 40;
            int x = 170;
            int x2=360;
            int x3=500;
            int positionX = 10;
            int positionY = y + 30;
           g2d.setColor(new Color(140, 255, 251));
            if (i == 0) {
                g2d.draw(new Line2D.Float((float) x, 30f, (float) x, 215f));
                g2d.draw(new Line2D.Float((float) x+x2, 30f, (float) x+x2, 215f));
                g2d.draw(new Line2D.Float((float) x+x3, 30f, (float) x+x3, 215f));

            } else {
                g2d.draw(new Line2D.Float(0f, (float) y, 300, (float) y));
                g2d.draw(new Line2D.Float(x2, (float) y, x3+300, (float) y));
            }

            AttributedString header = new AttributedString(heroHeader[i]);
            header.addAttribute(TextAttribute.FONT, font);
            g2d.drawString(header.getIterator(), positionX, positionY);

            AttributedString spellHeader = new AttributedString(spellsHeader[i]);
            spellHeader.addAttribute(TextAttribute.FONT, font);
            g2d.drawString(spellHeader.getIterator(), x2+positionX, positionY);

            g2d.setColor(new Color(196, 255, 14));

            AttributedString content = new AttributedString(heroData[i]);
            content.addAttribute(TextAttribute.FONT, font);
            g2d.drawString(content.getIterator(), x+positionX, positionY);



            AttributedString spellresponse1 = new AttributedString(spelldata1[i]);
            spellresponse1.addAttribute(TextAttribute.FONT, font);
            g2d.drawString(spellresponse1.getIterator(), x2+x+positionX, positionY);
            AttributedString spellresponse2 = new AttributedString(spelldata2[i]);
            spellresponse2.addAttribute(TextAttribute.FONT, font);
            g2d.drawString(spellresponse2.getIterator(), x3+x+positionX, positionY);
        }

    }
}

