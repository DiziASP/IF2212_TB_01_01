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
            
        } else if (worldRow<=0 && worldColumn>2 && worldColumn<5){
        } else if (worldColumn >= 6 && worldRow>2 && worldRow<5){
        } else if (worldRow>=6 && worldColumn>2 && worldColumn<5){
        } else if (worldRow<=0 || worldRow >=6 || worldColumn <=0 || worldColumn >=6){
        }   else if (gp.getRoom().getMapRuangan()[worldRow - 1][worldColumn - 1] != null) {
            Integer collidingFurnitur = gp.getRoom().getMapRuangan()[worldRow - 1][worldColumn - 1];
            if (collidingFurnitur != -1) {
                interactableObj = collidingFurnitur;
            }
            return interactableObj;
        }
        if (worldColumn <= 0 && worldRow>2 && worldRow<5){
            if (gp.getRoom().getRoomLeft()!=null){
                return -5;
            }
            return -1;
        } else if (worldRow<=0 && worldColumn>2 && worldColumn<5){
            if (gp.getRoom().getRoomAbove()!=null){
                return -4;
            }
            return -1;
        } else if (worldColumn >= 6 && worldRow>2 && worldRow<5){
            if (gp.getRoom().getRoomRight()!=null){
                return -3;
            }
            return -1;
        } else if (worldRow>=6 && worldColumn>2 && worldColumn<5){
            if (gp.getRoom().getRoomBelow()!=null){
                return -2;
            }
            return -1;
        }
        return -1;
    }
}
