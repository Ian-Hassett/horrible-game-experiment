import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class player extends entity{
    private int maxSpeed = constants.maxSpeed;
    private keyinputs keyinputs;
    private BufferedImage image = null;
    private int imageIndex;
    private File defaultFile,left1File,left2File,right1File,right2File,back1File,back2File,forword1File,forword2File;
    

    private int movingFrame;
    private boolean moving;
    private int coins;
    

    private boolean speedBoots;


    public player(keyinputs keyinputs,game game){
        setHitboxIndentionY(4);
        setHitboxIndentionX(3);
        setHitbox(new Rectangle((int)this.getX(), (int)this.getY(), constants.tileSize-(2*getHitboxIndentionX()),constants.tileSize-getHitboxIndentionY()));
        setCD(new colitionDetector(this));
        this.setX(constants.tileSize*constants.startingXPos);
        this.setY(constants.tileSize*constants.startingYPos);
        this.keyinputs = keyinputs;
        imageIndex =1;
        defineFiles();
        setXspeed(0);
        setYspeed(0);
        setGravityAffected(true);
    }

    //defines the files for the images
    private void defineFiles() {
        defaultFile = new File("images/forward.png");
        left1File = new File("images/left1.png");
        left2File = new File("images/left2.png");
        right1File = new File("images/right1.png");
        right2File = new File("images/right2.png");
        back1File = new File("images/back1.png");
        back2File = new File("images/back2.png");
        forword1File = new File("images/forward1.png");
        forword2File = new File("images/forward2.png");
    }
    
    public void setKeyInputs(keyinputs keyinputs){
        this.keyinputs = keyinputs;
    }


    @Override
    public void update() {
        super.update();
        getInputs();
        setDerection();
        updateMoving();
        friction();
        move();
    }
    
    //reacts to the imputs
    private void getInputs() {
        if(keyinputs.wPressed&&!getGravityAffected()){
           if(getYspeed()>=-maxSpeed){
                this.setYspeed(getYspeed()-constants.movementSpeed);
            } 
        }
        if(keyinputs.sPressed){
            if(getYspeed()<=maxSpeed){
                this.setYspeed(getYspeed()+constants.movementSpeed);
            }
            
        }
        if(keyinputs.aPressed){
            if(getXspeed()>=-maxSpeed){
                this.setXspeed(getXspeed()-constants.movementSpeed);
            }
            
        }
        
        if(keyinputs.dPressed){
            if(getXspeed()<=maxSpeed){
                this.setXspeed(this.getXspeed()+constants.movementSpeed);
            }
        }
        if(keyinputs.spacePressed){
            jump();
        }else{
            setStillJumping(false);
        }
    }
    private void move() {
        
        int canmove = canmove(this,getDerection());
        if (canmove==0){
            this.setY((int) (this.getHitbox().y+this.getYspeed()-getHitboxIndentionY()));
            this.setX((int) (this.getHitbox().x+this.getXspeed()-getHitboxIndentionX()));
            
        }else if(canmove==1){
            this.setY((int) (this.getHitbox().y+this.getYspeed()-getHitboxIndentionY()));
            
        }else if(canmove==2){
            this.setX((int) (this.getHitbox().x+this.getXspeed()-getHitboxIndentionX()));
            
        }else{
        }
    }
    //updates the derection int so that it is able to run the animation and check colition.
    private void setDerection(){
        setDerection(3);
        if(this.getYspeed() <=0){
            setDerection(getDerection() - 1);
        }
        if (this.getYspeed()<0){
            setDerection(getDerection() - 1);
        }
        if (this.getXspeed() ==0){
            if (this.getYspeed()<=0){
                setDerection(0);
            }else{
                setDerection(4);
            }
        }
        if (this.getXspeed()<0){
            setDerection(-getDerection());
        }
        setDerection(getDerection() + 4);
        setDerection(getDerection()%8);
        
        
    }
    //updates the moving boolean to tell when the player is in motion
    private void updateMoving() {
        int canmove = canmove(this,getDerection());
        moving = false;
        if (canmove==0&&(this.getXspeed()!=0||this.getYspeed()!=0)){
            moving=true;
        }
        if(canmove==1&&this.getYspeed()!=0){
            moving=true;
        }
        if(canmove==2&&this.getXspeed()!=0){
            moving=true;
        }

    }
    //prints the player on to the screen over the background 
    public void draw(Graphics2D g) {
        getImage();
        Graphics2D g2 = (Graphics2D)g;
        if(constants.develupmentMode){
            g2.drawRect(this.getHitbox().x, this.getHitbox().y, this.getHitbox().width, this.getHitbox().height);
        }else{
            g2.drawImage(image, (int)this.getX(), (int)this.getY(),constants.tileSize,constants.tileSize,null);
        }

        if(moving){
            movingFrame++;
        }
        
        if (movingFrame%10==0){
            imageIndex++;
        }
    }
    //finds witch image to use to display player
    private void getImage() {
        try {
            if(!moving){
                image= ImageIO.read(defaultFile);
            }else if (getDerection() ==4){
                if(imageIndex%2==0){
                    image= ImageIO.read(back1File);
                }else{
                    image= ImageIO.read(back2File);
                }
            }else if(getDerection() <= 0){
                if(imageIndex%2==0){
                    image= ImageIO.read(forword1File);
                }else{
                    image= ImageIO.read(forword2File);
                }
            }else if(getDerection() >4){
                if(imageIndex%2==0){
                    image= ImageIO.read(right1File);
                }else{
                    image= ImageIO.read(right2File);
                }
            }else if(getDerection() <4){
                if(imageIndex%2==0){
                    image= ImageIO.read(left1File);
                }else{
                    image= ImageIO.read(left2File);
                }
            }else{
                image= ImageIO.read(defaultFile);
            }
            
            
        } catch (Exception e) {
        System.out.println("error");
        e.printStackTrace();
        }
    }
    public void toggleGravity(){
        this.setGravityAffected(!this.getGravityAffected());
    }

    public void toogleSpeedBoots() {
        if(speedBoots){
            maxSpeed/=2;
            speedBoots = false;
        }else{
            maxSpeed = maxSpeed*=2;
            speedBoots = true;
        }

    }
    
    public File getDefaultFile() {
        return defaultFile;
    }

    public void setDefaultFile(File defaultFile) {
        this.defaultFile = defaultFile;
    }

    public File getLeft1File() {
        return left1File;
    }

    public void setLeft1File(File left1File) {
        this.left1File = left1File;
    }

    public File getLeft2File() {
        return left2File;
    }

    public void setLeft2File(File left2File) {
        this.left2File = left2File;
    }

    public File getRight1File() {
        return right1File;
    }

    public void setRight1File(File right1File) {
        this.right1File = right1File;
    }

    public File getRight2File() {
        return right2File;
    }

    public void setRight2File(File right2File) {
        this.right2File = right2File;
    }

    public File getBack1File() {
        return back1File;
    }

    public void setBack1File(File back1File) {
        this.back1File = back1File;
    }

    public File getBack2File() {
        return back2File;
    }

    public void setBack2File(File back2File) {
        this.back2File = back2File;
    }

    public File getForword1File() {
        return forword1File;
    }

    public void setForword1File(File forword1File) {
        this.forword1File = forword1File;
    }

    public File getForword2File() {
        return forword2File;
    }

    public void setForword2File(File forword2File) {
        this.forword2File = forword2File;
    }
    
    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void collectCoin(){
        coins++;
    }
}
