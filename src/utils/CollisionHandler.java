package utils;

import entities.Entity;
import entities.Sim;
import object.SuperObject;

import static utils.Constant.*;
public class CollisionHandler {
    GamePanel gp;
    public CollisionHandler(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity mySim){

        int simLeftX = mySim.position.x + mySim.solidArea.x;
        int simRightX = mySim.position.x + mySim.solidArea.x + mySim.solidArea.width;
        int simTopY = mySim.position.y + mySim.solidArea.y;
        int simBottomY = mySim.position.y + mySim.solidArea.y + mySim.solidArea.height;

        int tileLeftCol = simLeftX / tileSize;
        int tileRightCol = simRightX / tileSize;
        int tileTopRow = simTopY / tileSize;
        int tileBottomRow = simBottomY / tileSize;

        int tileNum1, tileNum2;

        switch(mySim.direction){
            case "up":
                tileTopRow = (simTopY - objSpeed ) / tileSize;
                tileNum1 = gp.tilesManager.tilesMapper[tileLeftCol][tileTopRow];
                tileNum2 = gp.tilesManager.tilesMapper[tileRightCol][tileTopRow];
                if(gp.tilesManager.tile[tileNum1].collision || gp.tilesManager.tile[tileNum2].collision ){
                    mySim.collisionOn = true;
                }
                break;
            case"down":
                tileBottomRow = (simBottomY + objSpeed ) / tileSize;
                    tileNum1 = gp.tilesManager.tilesMapper[tileLeftCol][tileBottomRow];
                tileNum2 = gp.tilesManager.tilesMapper[tileRightCol][tileBottomRow];
                if(gp.tilesManager.tile[tileNum1].collision || gp.tilesManager.tile[tileNum2].collision ){
                    mySim.collisionOn = true;
                }
                break;
            case "left":
                tileLeftCol = (simLeftX - objSpeed ) / tileSize;
                tileNum1 = gp.tilesManager.tilesMapper[tileLeftCol][tileTopRow];
                tileNum2 = gp.tilesManager.tilesMapper[tileRightCol][tileBottomRow];
                if(gp.tilesManager.tile[tileNum1].collision || gp.tilesManager.tile[tileNum2].collision ){
                    mySim.collisionOn = true;
                }
                break;
            case "right":
                tileRightCol = (simRightX + objSpeed ) / tileSize;
                tileNum1 = gp.tilesManager.tilesMapper[tileLeftCol][tileTopRow];
                tileNum2 = gp.tilesManager.tilesMapper[tileRightCol][tileBottomRow];
                if(gp.tilesManager.tile[tileNum1].collision || gp.tilesManager.tile[tileNum2].collision ){
                    mySim.collisionOn = true;
                }
                break;
        }


    }

    public void checkObj(Entity entity, boolean isSim) {

        for(int i = 0; i < gp.superObject.length; i++){
            if(gp.superObject[i] != null) {
                entity.solidArea.x = entity.position.x + entity.solidArea.x;
                entity.solidArea.y = entity.position.y + entity.solidArea.y;

                gp.superObject[i].solidArea.x = gp.superObject[i].position.x + gp.superObject[i].solidArea.x;
                gp.superObject[i].solidArea.y = gp.superObject[i].position.y + gp.superObject[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= objSpeed;
                        if (entity.solidArea.intersects(gp.superObject[i].solidArea)) {
                            if(gp.superObject[i].collision){
                                entity.collisionOn = true;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += objSpeed;
                        if (entity.solidArea.intersects(gp.superObject[i].solidArea)) {
                               if(gp.superObject[i].collision){
                                    entity.collisionOn = true;
                               }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= objSpeed;
                        if (entity.solidArea.intersects(gp.superObject[i].solidArea)) {
                            if(gp.superObject[i].collision){
                                entity.collisionOn = true;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += objSpeed;
                        if (entity.solidArea.intersects(gp.superObject[i].solidArea)) {
                            if(gp.superObject[i].collision){
                                entity.collisionOn = true;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.superObject[i].solidArea.x = gp.superObject[i].solidAreaDefaultX;
                gp.superObject[i].solidArea.y = gp.superObject[i].solidAreaDefaultY;
            }
        }
    }
}
