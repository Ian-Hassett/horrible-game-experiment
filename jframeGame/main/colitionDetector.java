import java.awt.Rectangle;

public class colitionDetector {
    private entity entity;
    private Rectangle hitbox;
    private int tileSize = constants.tileSize;
    public colitionDetector(entity entity){
        this.entity = entity;
        hitbox = entity.getHitbox();
        
    }
    //returns true if can move there 
    public boolean checkX(int derection){
        double xspeed = entity.getXspeed();

        double x = hitbox.x;
        double y = hitbox.y;
        double width = hitbox.getWidth();
        double height = hitbox.getHeight();
        if(derection>0&&derection<4){
            return (!tileManager.hasColition((int)(((x+xspeed)/tileSize)+(int)(y/tileSize)*constants.width))&&!tileManager.hasColition(((int)(x+xspeed)/tileSize)+(((int)(y+height)/tileSize)*constants.width)));
        }else if(derection>4&&derection<8){
            return (!tileManager.hasColition((int)(((x+width+xspeed)/tileSize)+(int)(y/tileSize)*constants.width))&&!tileManager.hasColition((int)((x+width+xspeed)/tileSize)+(((int)(y+height)/tileSize)*constants.width)));
        }else{
            return true;
        }
    }
    public boolean checkY(int derection){
        double yspeed = entity.getYspeed();
        double x = hitbox.x;
        double y = hitbox.y;
        double width = hitbox.getWidth();
        double height = hitbox.getHeight();
        if(derection>2&&derection<6){
           return (!tileManager.hasColition((((int)(y+yspeed)/tileSize))*constants.width+(((int)x/tileSize)))&&!tileManager.hasColition(((int)(y+yspeed)/tileSize)*constants.width+((int)(x+width)/tileSize)));
        }else if(derection>6||derection<2){
            return (!tileManager.hasColition((((int)(y+height+yspeed)/tileSize))*constants.width+(((int)x/tileSize)))&&!tileManager.hasColition((((int)(y+height+yspeed)/tileSize))*constants.width+((int)(x+width)/tileSize)));
        }else{
            return true;
        }
    }
}
