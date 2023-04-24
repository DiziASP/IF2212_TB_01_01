package utils;

public interface Constant {
    /*
     Window title
     * */
    /** Window's Main Title */ public static final String WINDOW_TITLE = "Sim-plicity";
    /** FPS */ public static final Double FPS = 60.0;
    /** Object Speed */ public static final Integer objSpeed = 5;

    /*
    Object and Entities size
    */
    /** Tile's Original */ public static final int initialTileSize = 16; /* 16px */
    /** Screen Scale */ public static final int scale = 3; /* 3x */
    /** Tile's Size */ public static final int tileSize = initialTileSize * scale; /* 48px */
    /** Maximum Screen Column */ public static final int maxScreenColumn = 16; /* 4:3 */
    /** Maximum Screen Row */ public static final int maxScreenRow = 12; /* 4:3 */
    /** Maximum World Column */ public static final int maxWorldColumn = 64; /* 4:3 */
    /** Maximum World Row */ public static final int maxWorldRow = 64; /* 4:3 */
    /** World's Width */ public static final int WORLD_WIDTH = tileSize * maxWorldColumn;
    /** World's Height */ public static final int WORLD_HEIGHT = tileSize * maxWorldRow;
    /** Window's Width */ public static final int WINDOW_WIDTH = tileSize * maxScreenColumn; /* 768px */
    /** Window's Height */ public static final int WINDOW_HEIGHT = tileSize * maxScreenRow; /* 576px */
}
