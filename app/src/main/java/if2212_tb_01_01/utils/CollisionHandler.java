package if2212_tb_01_01.utils;

import if2212_tb_01_01.entities.Entity;

import java.math.RoundingMode;
import java.util.Map;

import static if2212_tb_01_01.utils.Constant.*;

public class CollisionHandler {
    /* Import Game Panel */GamePanel gp;


    /**
     * Constructor for Collision Handler
     * @param gp Game Panel
     */
    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public void checkTileCollision(Entity mySim){
        int simLeftX = mySim.position.x + mySim.solidArea.x;
        int simRightX = mySim.position.x + mySim.solidArea.x + mySim.solidArea.width;
        int simTopY = mySim.position.y + mySim.solidArea.y;
        int simBottomY = mySim.position.y + mySim.solidArea.y + mySim.solidArea.height;

        int tileLeftCol = simLeftX / tileSize;
        int tileRightCol = simRightX / tileSize;
        int tileTopRow = simTopY / tileSize;
        int tileBottomRow = simBottomY / tileSize;

        int tileNum1, tileNum2;
        try {

            switch (mySim.direction) {
                case "up":
                    tileTopRow = (simTopY - objSpeed)/ tileSize;
                    if(tileTopRow <= 0
                    ) throw new MapOutOfBoundsException();
                    tileNum1 = gp.world.worldTilesManager.tilesMapper[tileLeftCol][tileTopRow];
                    tileNum2 = gp.world.worldTilesManager.tilesMapper[tileRightCol][tileTopRow];
                    if (gp.world.worldTilesManager.tiles[tileNum1].collision || gp.world.worldTilesManager.tiles[tileNum2].collision) {
                        mySim.collisionOn = true;
                    }
                    break;
                case "down":
                    tileBottomRow = (simBottomY + objSpeed)/ tileSize;
                    if(
                        tileBottomRow > maxWorldRow
                    ) throw new MapOutOfBoundsException();
                    tileNum1 = gp.world.worldTilesManager.tilesMapper[tileLeftCol][tileBottomRow];
                    tileNum2 = gp.world.worldTilesManager.tilesMapper[tileRightCol][tileBottomRow];
                    if (gp.world.worldTilesManager.tiles[tileNum1].collision || gp.world.worldTilesManager.tiles[tileNum2].collision) {
                        mySim.collisionOn = true;
                    }
                    break;
                case "left":
                    tileLeftCol = (simLeftX - objSpeed)/ tileSize;
                    if(tileLeftCol <= 0
                    ) throw new MapOutOfBoundsException();
                    tileNum1 = gp.world.worldTilesManager.tilesMapper[tileLeftCol][tileTopRow];
                    tileNum2 = gp.world.worldTilesManager.tilesMapper[tileRightCol][tileBottomRow];
                    if (gp.world.worldTilesManager.tiles[tileNum1].collision || gp.world.worldTilesManager.tiles[tileNum2].collision) {
                        mySim.collisionOn = true;
                    }
                    break;
                case "right":
                    tileRightCol = (simRightX + objSpeed)/ tileSize;
                    if(tileRightCol > maxWorldColumn
                    ) throw new MapOutOfBoundsException();
                    tileNum1 = gp.world.worldTilesManager.tilesMapper[tileLeftCol][tileTopRow];
                    tileNum2 = gp.world.worldTilesManager.tilesMapper[tileRightCol][tileBottomRow];
                    if (gp.world.worldTilesManager.tiles[tileNum1].collision || gp.world.worldTilesManager.tiles[tileNum2].collision) {
                        mySim.collisionOn = true;
                    }
                    break;
            }
        }
        catch (IndexOutOfBoundsException e){
            mySim.collisionOn = true;
            return;
        }
        catch(MapOutOfBoundsException e){
            mySim.collisionOn = true;
            return;
        }

    }

    public void checkHouseCollision(Entity mySim){
        int simLeftX = mySim.position.x + mySim.solidArea.x;
        int simRightX = mySim.position.x + mySim.solidArea.x + mySim.solidArea.width;
        int simTopY = mySim.position.y + mySim.solidArea.y;
        int simBottomY = mySim.position.y + mySim.solidArea.y + mySim.solidArea.height;

        int tileLeftCol = simLeftX / tileSize;
        int tileRightCol = simRightX / tileSize;
        int tileTopRow = simTopY / tileSize;
        int tileBottomRow = simBottomY / tileSize;
    }
    class MapOutOfBoundsException extends Exception{
        public MapOutOfBoundsException(){
            super("You're out of the map. Please go back or else NO BO DY WILL COME TO SAVE YOU!");
        }
    }
}
