
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class levelButtons extends JButton{
    private int index;
    private int coinCount = 0;
    private Image image;
    private int counter;
    private level level;
        
        

        public levelButtons(String string,int index) {
            super(string);
            this.index = index; 
            setCoinCount(0);
            level = game.getLevels().get(index);
            updateImage();
            level.setlevelButton(this);
        }
    
        public int getIndex() {
            return index;
        }
    
        /**
         * @param index
         */
        public void setIndex(int index) {
            this.index = index;
        }
        
        public int getCoinCount() {
            return coinCount;
        }
    
        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
            
        }
        public void updateImage(){
            
            try {
                image = ImageIO.read(new File("images/"+coinCount+"Stars.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        @Override
        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(image, counter, 0,this.getWidth(),this.getHeight(),null );
    }
    public level getLevel() {
        return level;
    }

    public void setLevel(level level) {
        this.level = level;
    }

}
