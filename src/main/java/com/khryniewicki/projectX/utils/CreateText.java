package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.graphics.Texture;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.HashMap;

public class CreateText {
    public static final HashMap<RenderingHints.Key, Object> RenderingProperties = new HashMap<>();

    static {
        RenderingProperties.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        RenderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        RenderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    public static Texture textToImage(String Text) {
        //Derives font to new specified size, can be removed if not necessary.
        Font f = new Font("Verdana", Font.PLAIN, 96);

        FontRenderContext frc = new FontRenderContext(null, true, true);

        //Calculate size of buffered image.
        LineMetrics lm = f.getLineMetrics(Text, frc);

        Rectangle2D r2d = f.getStringBounds(Text, frc);

        BufferedImage img = new BufferedImage((int) Math.ceil(r2d.getWidth()), (int) Math.ceil(r2d.getHeight() * 2), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.WHITE);

        g2d.clearRect(0, 0, img.getWidth(), img.getHeight());

        g2d.setFont(f);

        g2d.drawString(Text, 0, lm.getAscent());

        g2d.dispose();

        return new Texture(img);
    }

    public static Texture textToImage(String text, int fontSize) {
        //Derives font to new specified size, can be removed if not necessary.
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        if (text.length() > 0 ) {
            Font font = new Font("Verdana", Font.PLAIN, fontSize);

            Graphics g = image.getGraphics();
            g.setColor(Color.WHITE);
            FontMetrics metrics = g.getFontMetrics(font);
            AttributedString attributedText = new AttributedString(text);
            attributedText.addAttribute(TextAttribute.FONT, font);
            int positionX = 30;
            int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(attributedText.getIterator(), positionX, positionY);
        }
        return new Texture(image);

    }

}
