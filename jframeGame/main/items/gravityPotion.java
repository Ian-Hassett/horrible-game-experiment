import java.awt.Rectangle;
public class gravityPotion extends item{
    
    public gravityPotion(){
    super();
    setImageIcon("images/gPotion.png");
    }
    public gravityPotion(int x,int y){
        super(x, y);
        setImageIcon("images/gPotion.png");
        setHitbox(new Rectangle(x, y, getWidth(), getHeight()));
    }
    public gravityPotion(int x,int y,int type){
        super(x, y, type);
        setImageIcon("images/gPotion.png");
        
    }
    @Override
    public void activate(player player){
        super.activate();
        player.toggleGravity();
    }
}
