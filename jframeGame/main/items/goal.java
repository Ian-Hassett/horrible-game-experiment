public class goal extends item {
    public goal(){
        super();
        setImageIcon("images/flag.png");
    }
    
    public goal(int x,int y){
        super(x, y);
        setImageIcon("images/flag.png");
    }
    public goal(int x,int y,int type){
        super(x, y, type);
        setImageIcon("images/flag.png");
    }

    @Override
    public void activate(player player){
        getIM().getSoundSystem().play("sound/coin.wav");//TODO get new sound
        game.getCurrentLevel().complete();
        game.win();
    }
}
