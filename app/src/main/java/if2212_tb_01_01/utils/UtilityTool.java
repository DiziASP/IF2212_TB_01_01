package if2212_tb_01_01.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static if2212_tb_01_01.utils.Constant.*;

public class UtilityTool {
    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(original, 0, 0, width, height, null);
        graphics2D.dispose();

        return scaledImage;
    }

    public static int getXforCenteredText(Graphics2D g2, String text) {
        int len = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = screenWidth / 2 - len / 2;
        return x;
    }
}
