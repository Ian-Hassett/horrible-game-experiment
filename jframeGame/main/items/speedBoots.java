import java.awt.Rectangle;

public class speedBoots extends item{
    public speedBoots(){
        super();
        setImageIcon("");//TODO add image 
        }
        public speedBoots(int x,int y){
            super(x, y);
            setImageIcon("");//TODO add image 
            setHitbox(new Rectangle(x, y, getWidth(), getHeight()));
        }
        public speedBoots(int x,int y,int type){
            super(x, y, type);
            setImageIcon("images/0Stars.png");//TODO add image 
            
        }
        @Override
        public void activate(player player){
            super.activate();
            player.toogleSpeedBoots();
        }
}
