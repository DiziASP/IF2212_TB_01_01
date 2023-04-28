package if2212_tb_01_01.utils;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.items.furnitur.Furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class InteractionHandler {
    GamePanel gp;

    public InteractionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkOnInteractionRange(Sim sim) {
        boolean isInteractable = false;
        int width = screenWidth;
        int height = screenHeight;


        int roomX = (width - tileSize * 14) / 2;
        int roomY = (height - tileSize * 11) / 2;

        int worldColumn = (sim.getInteractableArea().x - roomX) / tileSize;
        int worldRow = (sim.getInteractableArea().y - roomY) / tileSize;

        if (worldColumn <= 0 || worldColumn >= 6 || worldRow <= 0 || worldRow >= 6) {
            System.out.println("Out of bound");
            return false;
        } else if (gp.room.getMapRuangan()[worldRow - 1][worldColumn - 1] != null) {
            Furnitur collidingFurnitur = gp.room.getMapRuangan()[worldRow - 1][worldColumn - 1];
            switch (sim.getDirection()) {
                case "up":
                    if (sim.getInteractableArea().intersects(collidingFurnitur.getInteractionArea())) {
                        isInteractable = true;
                    }
                    break;
                case "down":
                    if (sim.getInteractableArea().intersects(collidingFurnitur.getInteractionArea())) {
                        isInteractable = true;
                    }
                    break;
                case "left":
                    if (sim.getInteractableArea().intersects(collidingFurnitur.getInteractionArea())) {
                        isInteractable = true;
                    }
                    break;
                case "right":
                    if (sim.getInteractableArea().intersects(collidingFurnitur.getInteractionArea())) {
                        isInteractable = true;
                    }
                    break;
            }
            return isInteractable;
        }
        else return false;
    }
}
