public class coin extends item{
    
    public coin(){
        super();
        setImageIcon("images/coin.png");
    }
    
    public coin(int x,int y){
        super(x, y);
        setImageIcon("images/coin.png");
    }
    public coin(int x,int y,int type){
        super(x, y, type);
        setImageIcon("images/coin.png");
    }

    @Override
    public void activate(player player){
        getIM().getSoundSystem().play("sound/coin.wav");
        player.collectCoin();;
        super.activate();
        System.out.println("played");
        game.getCurrentLevel().collectCoin();
    }
    
}
