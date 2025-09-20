import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class item {
    private int x;
    private int y;
    private Rectangle hitbox;
    
    private int width = constants.tileSize; 
    
    private BufferedImage imageIcon;
    private static itemManager im;
    
    public item(){

    }
    public item(int x, int y){
        this.x = constants.tileSize*x;
        this.y = constants.tileSize*((constants.height-1)-y);
        hitbox = new Rectangle(this.x, this.y, width, height);
    }
    public item(int x, int y, int type){
        if(type==0){
            this.x = constants.tileSize*x;this.y = constants.tileSize*((constants.height-1)-y);
        }else if(type == 1) {
            this.x = x;this.y = y;
        }else{
            this.x = constants.tileSize*x;this.y = constants.tileSize*((constants.height-1)-y);System.out.println("default error" + type+';');
        }
        hitbox = new Rectangle(this.x, this.y, width, height);
    }
    public void setImageIcon(String pathName){
        try {
            this.imageIcon = ImageIO.read(new File(pathName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void draw(Graphics g){
        if(constants.develupmentMode){
            g.drawRect(hitbox.x,hitbox.y,constants.tileSize,constants.tileSize);
        }else{
            g.drawImage(imageIcon, this.x, this.y,constants.tileSize,constants.tileSize,null);
        }
    }
    public boolean checkPlayerColition(Rectangle playerHitbox) {

        if(hitbox.intersects(playerHitbox)){
            return true;
        }
        return false;
    }
    public void activate() {
    }
    public void activate(player player) {
    }
    public static void setIM(itemManager IM) {
        im = IM;
    }
    public int getLocation() {
        return y/constants.tileSize*constants.width+x/constants.tileSize;
    }
    public static itemManager getIM() {
        return im;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
    
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    private int height = constants.tileSize; 
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
