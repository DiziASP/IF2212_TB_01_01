package if2212_tb_01_01.utils;

import java.awt.Color;

public class Constant {
    /* Screen Settings */
    /** Original Tile Size */
    public static final int originalTileSize = 16; // 16x16 tile
    /** Object Scaling */
    public static final int scale = 3;

    /** Main Tile Size */
    public static final int tileSize = originalTileSize * scale; // 48x48 tile
    /** Max Screen Column Width */
    public static final int maxScreenColumns = 16;
    /** Max Screen Row Height */
    public static final int maxScreenRows = 12;

    /* Window Settings */
    /** Max Screen Width */
    public static final int screenWidth = tileSize * maxScreenColumns; // 768 px
    /** Max Screen Height */
    public static final int screenHeight = tileSize * maxScreenRows; // 576 px

    /** Game FPS */
    public static final int FPS = 60;

    /* World Settings */
    public static final int maxWorldColumns = 64;
    public static final int maxWorldRows = 64;
    public static final int worldWidth = tileSize * maxWorldColumns;
    public static final int worldHeight = tileSize * maxWorldRows;
    public static final int maxMaps = 64; /* Kira-kira aja */

    /* Game State */
    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int pauseState = 2;
    public static final int optionState = 3;
    public static final int inventoryState = 4;
    public static final int mapState = 5;

    /* Colors */
    public static Color c1 = new Color(0x472A08);
    public static Color c2 = new Color(0x5E87CA);
    public static Color c3 = new Color(0xBC4B48);
    public static Color c4 = new Color(0xF6E27F);
    public static Color c5 = new Color(0xEAB8D9);
    public static Color c6 = new Color(0xFDFFFF);
    public static Color c7 = new Color(0x939393);
    public static Color c8 = new Color(0x747474);
}
