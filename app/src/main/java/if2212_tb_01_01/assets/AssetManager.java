package if2212_tb_01_01.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.imageio.ImageIO;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.utils.UtilityTool;

public class AssetManager implements Serializable{
    GamePanel gp;

    public AssetManager(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return UtilityTool.scaleImage(image, width, height);
    }
}
