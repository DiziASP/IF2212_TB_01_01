package if2212_tb_01_01.utils;

import static if2212_tb_01_01.utils.Constant.*;
import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.sim.Sim;

public class CollisionHandler {
    GamePanel gp;

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkTileCollision(Sim sim) {
        boolean isCollision = false;
        int width = screenWidth;
        int height = screenHeight;


        int roomX = (width - tileSize * 14) / 2;
        int roomY = (height - tileSize * 11) / 2;

        int worldColumn = (sim.getSolidArea().x - roomX)/tileSize;
        int worldRow = (sim.getSolidArea().y - roomY)/tileSize;

        int tileNum = gp.room.getTileManager().getMapTileNumbers()[gp.room.getRoomIndex()][worldRow][worldColumn];

        switch (sim.getDirection()) {
            case "up":
                if (gp.room.getTileManager().getTiles()[tileNum].isCollision()) {
                    isCollision = true;
                }
                break;
            case "down":
                if (gp.room.getTileManager().getTiles()[tileNum].isCollision()) {
                    isCollision = true;
                }
                break;
            case "left":
                if (gp.room.getTileManager().getTiles()[tileNum].isCollision()) {
                    isCollision = true;
                }
                break;
            case "right":
                if (gp.room.getTileManager().getTiles()[tileNum].isCollision()) {
                    isCollision = true;
                }
                break;
            default:
                break;
        }

        return isCollision;
    }

    public boolean checkObjectCollision(Sim sim) {
        boolean interactableObj = false;
        int width = screenWidth;
        int height = screenHeight;


        int roomX = (width - tileSize * 14) / 2;
        int roomY = (height - tileSize * 11) / 2;

        int worldColumn = (sim.getSolidArea().x - roomX) / tileSize;
        int worldRow = (sim.getSolidArea().y - roomY) / tileSize;

        if (worldColumn <= 0 || worldColumn >= 6 || worldRow <= 0 || worldRow >= 6) {
            System.out.println("Out of bound");
            return false;
        } else if (gp.room.getMapRuangan()[worldRow - 1][worldColumn - 1] != null) {
            Integer collidingFurnitur = gp.room.getMapRuangan()[worldRow - 1][worldColumn - 1];
            if (collidingFurnitur != -1) {
                        interactableObj = true;
            }
            return interactableObj;
        }
        else return false;
    }
}