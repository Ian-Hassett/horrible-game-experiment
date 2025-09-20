import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class tile {
    private int index;
    private char symble;
    private boolean colition;
    
    private BufferedImage image;
    public tile(String fileName){
        colition = false;
        try {
            this.image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public tile(String pathName,boolean colition){
        this.colition = colition;
        try {
            this.image = ImageIO.read(new File(pathName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public boolean getColition() {
        return colition;
    }
    public void setColition(boolean colition) {
        this.colition = colition;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
