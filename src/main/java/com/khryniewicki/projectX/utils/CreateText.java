package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.graphics.Texture;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

public class CreateText {
    private static Color defaultColor = Color.WHITE;

    public static Texture textToImageWithLine(String text, int fontSize) {
        BufferedImage image = new Texture("blankTextWindowWithLine.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, fontSize);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }

    public static Texture textToImageMenu(String text) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 24);

        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }
    public static Texture textInLoadingMenuToImage(String text, Color color) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.PLAIN, 22);
        setImage(text, font, color, image);
        return new Texture(image);
    }
    public static Texture textInControlSettingsToImage(String text, Color color) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 30);

        setImage(text, font, color, image);
        return new Texture(image);
    }
    public static Texture textInConnectionWindow(String text) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 20);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }
    private static void setImage(String text, Font font, Color color, BufferedImage image) {
        if (text.length() > 0) {

            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2d.setBackground(Color.BLACK);
            g2d.setColor(color);
            FontMetrics metrics = g2d.getFontMetrics(font);
            AttributedString attributedText = new AttributedString(text);
            attributedText.addAttribute(TextAttribute.FONT, font);
            int positionX = 30;
            int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g2d.drawString(attributedText.getIterator(), positionX, positionY);
        }
    }


}
