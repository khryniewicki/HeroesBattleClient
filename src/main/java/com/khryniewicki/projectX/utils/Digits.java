package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.graphics.Texture;

import java.util.*;

public class Digits {

    public final static Texture blank = new Texture("blank.png");
    public final static Texture d0 = new Texture("0.png");
    public final static Texture d1 = new Texture("1.png");
    public final static Texture d2 = new Texture("2.png");
    public final static Texture d3 = new Texture("3.png");
    public final static Texture d4 = new Texture("4.png");
    public final static Texture d5 = new Texture("5.png");
    public final static Texture d6 = new Texture("6.png");
    public final static Texture d7 = new Texture("7.png");
    public final static Texture d8 = new Texture("8.png");
    public final static Texture d9 = new Texture("9.png");
    public final static Texture d10 = new Texture("10.png");
    public static Map<Integer, Texture> digitsRegistry;


    public static void fillDigitsRegistry() {

        digitsRegistry = new HashMap<>();

        List<Texture> textures = new ArrayList<>(Arrays.asList(d0, d1, d2, d3, d4, d5, d6, d7, d8, d9));

        for (int i = 0; i < textures.size(); i++) {
            digitsRegistry.put(i, textures.get(i));
        }

    }

    public static Texture getHundredDigitTexture(int receivedNumber) {
        if (receivedNumber < 0 || receivedNumber > 100) {
            return Digits.d0;
        }
        if (receivedNumber < 100) {
            return Digits.blank;
        }
        return digitsRegistry.get(receivedNumber / 100);
    }

    public static Texture getDozenDigitTexture(int receivedNumber) {
        if (receivedNumber < 0 || receivedNumber >= 100) {
            return Digits.d0;
        }
        if (receivedNumber < 10) {
            return Digits.blank;
        }

        return digitsRegistry.get(receivedNumber / 10);
    }

    public static Texture getUnitDigitTexture(int receivedNumber) {

        if (receivedNumber < 0 || receivedNumber >= 100) {
            return Digits.d0;
        }

        int test = receivedNumber % 10;

        return digitsRegistry.get(test);
    }
}
