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

    public Integer checkOnInteractionRange(Sim sim) {
        int interactableObj = -1;
        int width = screenWidth;
        int height = screenHeight;


        int roomX = (width - tileSize * 14) / 2;
        int roomY = (height - tileSize * 11) / 2;

        int worldColumn = (sim.getInteractableArea().x+24 - roomX) / tileSize;
        int worldRow = (sim.getInteractableArea().y+24 - roomY) / tileSize;

        if (worldColumn <= 0 && worldRow>2 && worldRow<5){
            System.out.println("Out of bound");
            if (gp.room.getRoomLeft()!=null){
                return -5;
            }
            return -1;
        } else if (worldRow<=0 && worldColumn>2 && worldColumn<5){
            System.out.println("Out of bound");
            if (gp.room.getRoomAbove()!=null){
                return -4;
            }
            return -1;
        } else if (worldColumn >= 6 && worldRow>2 && worldRow<5){
            System.out.println("Out of bound");
            if (gp.room.getRoomRight()!=null){
                return -3;
            }
            return -1;
        } else if (worldRow>=6 && worldColumn>2 && worldColumn<5){
            System.out.println("Out of bound");
            if (gp.room.getRoomBelow()!=null){
                return -2;
            }
            return -1;
        } else if (worldRow<=0 || worldRow >=6 || worldColumn <=0 || worldColumn >=6){
            return -1;
        }   else if (gp.room.getMapRuangan()[worldRow - 1][worldColumn - 1] != null) {
            Integer collidingFurnitur = gp.room.getMapRuangan()[worldRow - 1][worldColumn - 1];
            if (collidingFurnitur != -1) {
                interactableObj = collidingFurnitur;
            }
            return interactableObj;
        }
        else return -1;
    }
}
