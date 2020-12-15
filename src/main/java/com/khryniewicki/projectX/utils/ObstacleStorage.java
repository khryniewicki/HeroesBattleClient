package com.khryniewicki.projectX.utils;

import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.game.user_interface.board.BoardObjects;
import com.khryniewicki.projectX.game.user_interface.board.Obstacle;
import com.khryniewicki.projectX.game.user_interface.board.Terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObstacleStorage {
    public static final Texture pipe = new Texture("pipe.png");

    // bottom-left quarter
    static Obstacle palm = new Obstacle("palm", 0.11f, 1.0f, -5.2f, -3.3f);
    static Obstacle palm1 = new Obstacle("palm1", 0.11f, 1.2f, -5.35f, -2.9f);
    static Obstacle palm2 = new Obstacle("palm2", 0.11f, 0.8f, -5.4f, -5.6f);
    static Obstacle palm3 = new Obstacle("palm3", 0.11f, 1.2f, -5.0f, -5.6f);
    static Obstacle palm4 = new Obstacle("palm4", 0.11f, 0.7f, -7.05f, -0.45f);
    static Obstacle palm5A = new Obstacle("palm5A", 0.11f, 0.7f, -6.4f, -0.65f);

    static Obstacle cactus1 = new Obstacle("cactus1", 0.11f, 0.4f, -3.85f, -4.4f);
    static Obstacle cactus2 = new Obstacle("cactus2", 0.11f, 1.0f, -3.05f, -3.0f);
    static Obstacle cactus3 = new Obstacle("cactus3", 0.11f, 1.1f, -4.1f, -1.15f);
    static Obstacle cactus5 = new Obstacle("cactus5", 0.11f, 0.6f, -3.85f, -1.55f);
    static Obstacle cactus6 = new Obstacle("cactus6", 0.11f, 0.4f, -4.30f, -1.25f);
    static Obstacle cactus7 = new Obstacle("cactus7", 0.11f, 0.7f, -3.85f, -3.3f);

    static Obstacle stone1 = new Obstacle("stone1", 0.25f, 0.85f, -3.7f, -3.25f);

    // bottom-right quarter

    static Obstacle cactus8 = new Obstacle("cactus8", 0.11f, 0.9f, 2.95f, -2.9f);
    static Obstacle cactus9 = new Obstacle("cactus9", 0.11f, 0.4f, 3.75f, -2.3f);
    static Obstacle cactus10 = new Obstacle("cactus10", 0.11f, 0.7f, 3.35f, -4.05f);
    static Obstacle cactus11 = new Obstacle("cactus11", 0.11f, 0.4f, 4.1f, -4.05f);
    static Obstacle cactus12 = new Obstacle("cactus12", 0.11f, 0.4f, 4.35f, -4.0f);
    static Obstacle cactus13 = new Obstacle("cactus13", 0.11f, 0.5f, 4.55f, -4.35f);
    static Obstacle cactus14 = new Obstacle("cactus14", 0.11f, 0.4f, 5.0f, -5.6f);
    static Obstacle cactus15 = new Obstacle("cactus15", 0.11f, 1.0f, 4.75f, -5.5f);
    static Obstacle cactus16 = new Obstacle("cactus16", 0.11f, 0.4f, 4.45f, -5.6f);
    static Obstacle cactus17 = new Obstacle("cactus17", 0.11f, 1.0f, 3.75f, -5.6f);
    static Obstacle cactus18 = new Obstacle("cactus18", 0.11f, 1.0f, 6.1f, -3.25f);

    static Obstacle stone2 = new Obstacle("stone2", 0.25f, 1.05f, 3.1f, -2.8f);
    static Obstacle stone3 = new Obstacle("stone3", 0.25f, 0.7f, 3.5f, -3.95f);
    static Obstacle stone4 = new Obstacle("stone4", 0.3f, 0.5f, 4.2f, -4.5f);
    static Obstacle stone5 = new Obstacle("stone5", 0.30f, 1.0f, 3.85f, -5.45f);


    // top-left quarter
    static Obstacle palm5 = new Obstacle("palm5", 0.11f, 0.7f, -6.4f, 0f);
    static Obstacle palm6 = new Obstacle("palm6", 0.11f, 1.2f, -6.2f, 1.55f);
    static Obstacle palm7 = new Obstacle("palm7", 0.11f, 0.7f, -6.65f, 2.65f);
    static Obstacle palm8 = new Obstacle("palm8", 0.11f, 0.8f, -8.65f, 1.40f);
    static Obstacle palm9 = new Obstacle("palm9", 0.11f, 0.8f, -5.5f, 4.2f);
    static Obstacle palm10 = new Obstacle("palm10", 0.11f, 1.2f, -4.6f, 3.6f);
    static Obstacle palm11 = new Obstacle("palm11", 0.11f, 0.7f, -3.1f, 3.45f);
    static Obstacle palm12 = new Obstacle("palm12", 0.11f, 0.7f, -1.55f, 4.1f);

    static Obstacle cactus4 = new Obstacle("cactus4", 0.11f, 0.85f, -2.22f, 0.0f);

    static Obstacle stone6 = new Obstacle("stone6", 0.3f, 0.8f, -2.89f, 3.7f);
    static Obstacle stone7 = new Obstacle("stone7", 0.25f, 0.35f, -2.4f, 5.15f);
    static Obstacle stone8 = new Obstacle("stone8", 0.1f, 0.3f, -1.8f, 5.2f);


    // top-right quarter
    static Obstacle cactus19 = new Obstacle("cactus19", 0.11f, 0.4f, 2.9f, 3.45f);
    static Obstacle cactus20 = new Obstacle("cactus20", 0.11f, 0.45f, 3.1f, 3.55f);
    static Obstacle cactus21 = new Obstacle("cactus21", 0.11f, 0.4f, 3.35f, 3.25f);
    static Obstacle cactus22 = new Obstacle("cactus22", 0.11f, 0.85f, 4.15f, 1.75f);
    static Obstacle cactus23 = new Obstacle("cactus23", 0.11f, 0.35f, 4.55f, 1.25f);

    static Obstacle palm13 = new Obstacle("palm13", 0.11f, 0.8f, 6.4f, 2.5f);
    static Obstacle palm14 = new Obstacle("palm14", 0.11f, 0.7f, 6.25f, 3.05f);

    static Obstacle stone9 = new Obstacle("stone9", 0.3f, 0.45f, 3.0f, 2.9f);

    static Obstacle wall1 = new Obstacle("wall1", 0.75f, 0.5f, 3.7f, 2.4f);
    static Obstacle wall2 = new Obstacle("wall2", 0.3f, 0.6f, 4.45f, 1.85f);

    static Obstacle wall3 = new Obstacle("wall3", 0.3f, 0.6f, 4.75f, 1.85f);
    static Obstacle wall4 = new Obstacle("wall4", 0.65f, 0.5f, 5.75f, 5.1f);
    static Obstacle wall5 = new Obstacle("wall5", 1.0f, 0.6f, 6.45f, 3.4f);
    static Obstacle wall6 = new Obstacle("wall6", 0.75f, 0.6f, 8.85f, 3.4f);
    static Obstacle wall7 = new Obstacle("wall7", 0.4f, 0.4f, 9.6f, 4.0f);

    public static Obstacle[] obstacles_BL = new Obstacle[]{
            palm, palm1, palm2, palm3, palm4, palm5A,
            cactus1, cactus2, cactus3, cactus5, cactus6, cactus7,
            stone1};

    public static Obstacle[] obstacles_BR = new Obstacle[]{
            cactus8, cactus9, cactus10, cactus11, cactus12, cactus13, cactus14,
            cactus15, cactus16, cactus17, cactus18,
            stone2, stone3, stone4, stone5};

    public static Obstacle[] obstacles_TL = new Obstacle[]{
            palm5, palm6, palm7, palm8, palm9, palm10, palm11, palm12,
            stone6, stone7, stone8,
            cactus4
    };

    public static Obstacle[] obstacles_TR = new Obstacle[]{
            cactus19, cactus20, cactus21, cactus22, cactus23,
            palm13, palm14,
            stone9,
            wall1,
            wall2, wall3, wall4, wall5, wall6, wall7
    };


    static Terrain mountain_line_1 = new Terrain("mountain_line_1", -1.1f, -2.45f, 0.3f, -0.55f);
    static Terrain mountain_line_2 = new Terrain("mountain_line_2", -1.1f, -2.95f, 0.7f);
    static Terrain mountain_line_2A = new Terrain("mountain_line_2A", -1.05f, -3.14f, -0.75f, -3.65f);

    static Terrain mountain_line_3 = new Terrain("mountain_line_3", -0.75f, -3.65f, -0.05f, -3.8f);
    static Terrain mountain_line_4 = new Terrain("mountain_line_4", -0.05f, -3.8f, 0.55f, -2.8f);
    static Terrain mountain_line_5 = new Terrain("mountain_line_5", 0.55f, -2.8f, 0.7f);
    static Terrain mountain_line_6 = new Terrain("mountain_line_6", 0.5f, -1.9f, 0.2f);
    static Terrain mountain_line_7 = new Terrain("mountain_line_7", 1.3f, -2.9f, 1.0f);
    static Terrain mountain_line_8 = new Terrain("mountain_line_8", 2.4f, -2.9f, 0.7f);

    static Terrain mountain_line_9 = new Terrain("mountain_line_9", 2.45f, -2.2f, 0.2f );
    static Terrain mountain_line_10 = new Terrain("mountain_line_10", 3.25f, -1.7f, 0.2f);
    static Terrain mountain_line_10A = new Terrain("mountain_line_10A", 3.35f, -1.5f, 0.2f);
    static Terrain mountain_line_10B = new Terrain("mountain_line_10B", 3.55f, -1.3f, 0.2f);
    static Terrain mountain_line_10C = new Terrain("mountain_line_10C", 3.75f, -1.1f, 0.2f);
    static Terrain mountain_line_10D = new Terrain("mountain_line_10D", 3.95f, -0.9f, 0.2f);
    static Terrain mountain_line_10E = new Terrain("mountain_line_10E", 3.90f, -0.7f, 0.6f);
    static Terrain mountain_line_11 = new Terrain("mountain_line_11", 3.8f, -0.2f, 0.4f);
    static Terrain mountain_line_12 = new Terrain("mountain_line_12", 3.45f, 0.3f, 0.2f);
    static Terrain mountain_line_13 = new Terrain("mountain_line_13", 3.15f, 0.4f, 0.2f);
    static Terrain mountain_line_13A = new Terrain("mountain_line_13A", 2.95f, 0.4f, 0.2f);

    static Terrain mountain_line_14 = new Terrain("mountain_line_14", 2.65f, 0.45f, 1.15f);
    static Terrain mountain_line_15 = new Terrain("mountain_line_15", 0.8f, -1.95f, 0.2f );

    static Terrain mountain_line_16 = new Terrain("mountain_line_16", 1.8f, 1.55f, 0.2f);
    static Terrain mountain_line_17 = new Terrain("mountain_line_17", 1.55f, 1.15f, 0.2f);
    static Terrain mountain_line_18 = new Terrain("mountain_line_18", 0.9f, -0.5f, 1.6f);
    static Terrain mountain_line_19 = new Terrain("mountain_line_19", 0.9f, -0.2f, 0.2f);
    static Terrain mountain_line_20 = new Terrain("mountain_line_20", 0.5f, -0.55f, 0.2f);
    static Terrain mountain_line_21 = new Terrain("mountain_line_21", 3.6f, 0.15f, 0.2f);
    static Terrain mountain_line_22 = new Terrain("mountain_line_22", 2.05f, 1.55f, 0.2f);
    static Terrain mountain_line_23 = new Terrain("mountain_line_23", 2.35f, 1.55f, 0.2f);
    static Terrain mountain_line_24 = new Terrain("mountain_line_24", 1.3f, 1.0f, 0.2f);
    static Terrain mountain_line_25 = new Terrain("mountain_line_25", 1.7f, 1.45f, 0.2f);
    static Terrain mountain_line_26 = new Terrain("mountain_line_26", -0.5f, -3.8f, 0.4f);


    static Terrain mountain_line2_1 = new Terrain("mountain_line2_1", 9.45f, 2.55f, 0.2f);
    static Terrain mountain_line2_1A = new Terrain("mountain_line2_1A", 9.75f, 2.55f, 0.2f);
    static Terrain mountain_line2_2 = new Terrain("mountain_line2_2", 8.15f, 1.05f, 9.45f, 2.4f);
    static Terrain mountain_line2_3 = new Terrain("mountain_line2_3", 8.0f, 0.4f, 0.3f);
    static Terrain mountain_line2_4 = new Terrain("mountain_line2_4", 7.5f, -0.1f, 0.2f);
    static Terrain mountain_line2_4A = new Terrain("mountain_line2_4A", 7.8f, -0.1f, 0.2f);
    static Terrain mountain_line2_5A = new Terrain("mountain_line2_5A", 6.2f, -1.8f, 0.2f);
    static Terrain mountain_line2_5B = new Terrain("mountain_line2_5B", 6.4f, -1.6f, 0.2f);
    static Terrain mountain_line2_5C = new Terrain("mountain_line2_5C", 6.5f, -1.3f, 0.2f);
    static Terrain mountain_line2_5D = new Terrain("mountain_line2_5D", 6.7f, -1.0f, 0.2f);
    static Terrain mountain_line2_5E = new Terrain("mountain_line2_5E", 7.0f, -0.55f, 0.2f);
    static Terrain mountain_line2_5F = new Terrain("mountain_line2_5F", 7.2f, 0.1f, 0.0f);
    static Terrain mountain_line2_5G = new Terrain("mountain_line2_5G", 7.25f, -0.35f, 0.2f);

    static Terrain mountain_line2_6 = new Terrain("mountain_line2_6", 6.15f, -2.25f, 0.55f);
    static Terrain mountain_line2_7 = new Terrain("mountain_line2_7", 6.7f, -2.85f, 0.2f);
    static Terrain mountain_line2_7A = new Terrain("mountain_line2_7A", 6.5f, -2.85f, 0.2f);
    static Terrain mountain_line2_7B = new Terrain("mountain_line2_7B", 6.2f, -2.35f, 0.2f);

    static Terrain mountain_line2_8 = new Terrain("mountain_line2_8", 6.6f, -3.3f, 0.6f);
    static Terrain mountain_line2_8A = new Terrain("mountain_line2_8A", 6.9f, -3.0f, 0.2f);
    static Terrain mountain_line2_9 = new Terrain("mountain_line2_9", 7.05f, -3.75f, 0.8f);
    static Terrain mountain_line2_10 = new Terrain("mountain_line2_10", 8.05f, -3.1f, 0.2f);
    static Terrain mountain_line2_11 = new Terrain("mountain_line2_11", 8.05f, -3.75f, 0.8f);
    static Terrain mountain_line2_12 = new Terrain("mountain_line2_12", 8.05f, -3.3f, 0.2f);
    static Terrain mountain_line2_12A = new Terrain("mountain_line2_12A", 8.2f, -3.1f, 0.2f);
    static Terrain mountain_line2_12B = new Terrain("mountain_line2_12B", 8.6f, -3.1f, 0.2f);
    static Terrain mountain_line2_13 = new Terrain("mountain_line2_13", 8.9f, -4.35f, 1.45f);
    static Terrain mountain_line2_14 = new Terrain("mountain_line2_14", 8.9f, -4.35f, 9.4f, -4.95f);
    static Terrain mountain_line2_15 = new Terrain("mountain_line2_15", 9.4f, -4.95f, 0.5);

    static Terrain mountain_line3_1 = new Terrain("mountain_line3_1", 9.25f, -2f, 0.2f);
    static Terrain mountain_line3_1A = new Terrain("mountain_line3_1A", 9.45f, -1.7f, 0.2f);
    static Terrain mountain_line3_1B = new Terrain("mountain_line3_1B", 9.65f, -1.45f, 0.2f);

    static Terrain mountain_line3_2 = new Terrain("mountain_line3_2", 8.65f, -2.0f, 0.2f);
    static Terrain mountain_line3_2A = new Terrain("mountain_line3_2A", 8.45f, -1.7f, 0.2f);

    static Terrain mountain_line3_2B = new Terrain("mountain_line3_2B", 8.25f, -1.4f, 0.2f);

    static Terrain mountain_line3_3 = new Terrain("mountain_line3_3 ", 8.2f, -1.1f, 0.9f);
    static Terrain mountain_line3_4 = new Terrain("mountain_line3_4 ", 8.45f, -0.25f, 9.6f, 1.45f);
    static Terrain mountain_line3_5 = new Terrain("mountain_line3_5 ", 9.5f, 1.3f, 0.2f);
    static Terrain mountain_line3_5A = new Terrain("mountain_line3_5A", 9.7f, 1.3f, 0.2f);
    static Terrain mountain_line3_6 = new Terrain("mountain_line3_6 ", 8.4f, 0.45f, 0.4f);
    static Terrain mountain_line3_7 = new Terrain("mountain_line3_7 ", 7.95f, -0.2f, 0.3f);

    static Terrain mountain_line4_1 = new Terrain("mountain_line4_1", -3.75f, -0.7f, 0.2f);
    static Terrain mountain_line4_2 = new Terrain("mountain_line4_2", -3.85f, -0.25f, 1.2f);
    static Terrain mountain_line4_3 = new Terrain("mountain_line4_3", -3.8f, 1.0f, -2.95f, 2.3f);
    static Terrain mountain_line4_4 = new Terrain("mountain_line4_4", -2.95f, 2.1f, 0.2f);
    static Terrain mountain_line4_4A = new Terrain("mountain_line4_4A", -2.60f, 2.1f, 0.2f);
    static Terrain mountain_line4_5 = new Terrain("mountain_line4_5", -2.4f, 2.15f, 0.55f);
    static Terrain mountain_line4_6 = new Terrain("mountain_line4_6", -2.05f, 2.55f, 0.2f);
    static Terrain mountain_line4_6A = new Terrain("mountain_line4_6", -1.85f, 2.55f, 0.2f);
    static Terrain mountain_line4_6B = new Terrain("mountain_line4_6", -1.65f, 2.55f, 0.2f);

    static Terrain mountain_line4_7 = new Terrain("mountain_line4_7", -1.25f, 1.4f, 1.0f);
    static Terrain mountain_line4_9 = new Terrain("mountain_line4_9", -1.75f, 0.75f, -1.25f, 1.5f);
    static Terrain mountain_line4_10 = new Terrain("mountain_line4_10", -1.75f, 1.2f, 0.2f);
    static Terrain mountain_line4_11 = new Terrain("mountain_line4_11", -3.0f, -0.3f, 1.5f);
    static Terrain mountain_line4_12 = new Terrain("mountain_line4_12", -3.15f, -0.5f, 0.2f);
    static Terrain mountain_line4_13 = new Terrain("mountain_line4_13", -3.45f, -0.5f, 0.2f);
    static Terrain mountain_line4_14 = new Terrain("mountain_line4_14", -1.8f, 0.75f, 0.2f);
    static Terrain mountain_line4_15 = new Terrain("mountain_line4_15", -2.5f, 0.75f, 0.2f);

    static Terrain mountain_line5_1 = new Terrain("mountain_line5_1", -10f, 3.45f, -9.05f, 5.1f);
    static Terrain mountain_line5_2 = new Terrain("mountain_line5_2", -9.05f, 5.1f, 0.5f);

    static Terrain mountain_line6_1 = new Terrain("mountain_line6_1", -7.9f, 5.3f, 0.7);

    static Terrain lake_line1 = new Terrain("lake_line1", -8.7f, 0.1f, 0.9f);
    static Terrain lake_line2 = new Terrain("lake_line2", -8.6f, 0.9f, -7.15f, 2.2f);
    static Terrain lake_line3 = new Terrain("lake_line3", -7.15f, 2.05f, 0.2f);
    static Terrain lake_line4 = new Terrain("lake_line4", -6.9f, 2.05f, 0.2f);
    static Terrain lake_line5 = new Terrain("lake_line5", -6.7f, 2.05f, 0.2f);

    static Terrain lake_line6 = new Terrain("lake_line6", -6.55f, 1.4f, 1f);
    static Terrain lake_line7 = new Terrain("lake_line7", -6.8f, -0.65f, 0.2f);
    static Terrain lake_line7A = new Terrain("lake_line7A", -6.5f, 0.8f, 0.6f);
    static Terrain lake_line7B = new Terrain("lake_line7B", -6.65f, -0.2f, 1f);

    static Terrain lake_line8 = new Terrain("lake_line8", -7.15f, -0.60f, 0.2);
    static Terrain lake_line9 = new Terrain("lake_line9", -7.55f, -0.60f, 0.2);
    static Terrain lake_line10 = new Terrain("lake_line10", -7.8f, -0.40f, 0.2f);
    static Terrain lake_line11 = new Terrain("lake_line11", -8.1f, -0.2f, 0.2f);
    static Terrain lake_line12 = new Terrain("lake_line12", -8.4f, 0.0f, 0.2f);


    public static Terrain[] terrains = new Terrain[]{
            mountain_line_1, mountain_line2_1A, mountain_line_2,
            mountain_line_2A, mountain_line_3, mountain_line_4,
            mountain_line_5, mountain_line_6, mountain_line_7, mountain_line2_7A, mountain_line2_7B, mountain_line_8,
            mountain_line_9, mountain_line_10, mountain_line_10A, mountain_line_10B, mountain_line_10C,
            mountain_line_10D, mountain_line_10E, mountain_line_11, mountain_line_12,
            mountain_line_13, mountain_line_13A, mountain_line_14, mountain_line_15, mountain_line_16,
            mountain_line_17, mountain_line_18, mountain_line_19, mountain_line_20, mountain_line_21,
            mountain_line_22, mountain_line_23, mountain_line_24, mountain_line_25, mountain_line_26,

            mountain_line2_1, mountain_line2_2, mountain_line2_3, mountain_line2_4, mountain_line2_4A,
            mountain_line2_5A, mountain_line2_5B, mountain_line2_5C, mountain_line2_5D, mountain_line2_5E, mountain_line2_5F,
            mountain_line2_5G, mountain_line2_6, mountain_line2_7, mountain_line2_8, mountain_line2_8A,
            mountain_line2_9, mountain_line2_10, mountain_line2_11, mountain_line2_12,
            mountain_line2_13, mountain_line2_14, mountain_line2_15,mountain_line2_12A,
            mountain_line2_12B,

            mountain_line3_1, mountain_line3_1A, mountain_line3_1B, mountain_line3_2, mountain_line3_2A, mountain_line3_2B, mountain_line3_3, mountain_line3_4, mountain_line3_5,
            mountain_line3_5A, mountain_line3_6, mountain_line3_7,
            mountain_line4_4A,
            mountain_line4_1, mountain_line4_2, mountain_line4_3, mountain_line4_4, mountain_line4_5,
            mountain_line4_6,mountain_line4_6A,mountain_line4_6B, mountain_line4_7, mountain_line4_9, mountain_line4_10,
            mountain_line4_11, mountain_line4_12, mountain_line4_13, mountain_line4_14, mountain_line4_15,

            mountain_line5_1, mountain_line5_2,
            mountain_line6_1,

            lake_line1, lake_line2, lake_line3, lake_line4, lake_line5, lake_line6, lake_line7, lake_line7A, lake_line7B,
            lake_line8, lake_line9, lake_line10, lake_line11, lake_line12

    };

    public static List<BoardObjects> getObstacleList_BL() {
        return new ArrayList<>(Arrays.asList(obstacles_BL));
    }

    public static List<BoardObjects> getObstacleList_BR() {
        return new ArrayList<>(Arrays.asList(obstacles_BR));
    }

    public static List<BoardObjects> getObstacleList_TL() {
        return new ArrayList<>(Arrays.asList(obstacles_TL));
    }

    public static List<BoardObjects> getObstacleList_TR() {
        return new ArrayList<>(Arrays.asList(obstacles_TR));
    }

    public static List<BoardObjects> getObstacle() {
        List<BoardObjects> obstacleList = new ArrayList<>();
        obstacleList.addAll(Arrays.asList(obstacles_TR));
        obstacleList.addAll(Arrays.asList(obstacles_TL));
        obstacleList.addAll(Arrays.asList(obstacles_BL));
        obstacleList.addAll(Arrays.asList(obstacles_BR));
        return obstacleList;
    }

    public static List<BoardObjects> getTerrainList() {
        return new ArrayList<>(Arrays.asList(terrains));
    }
}