import java.awt.Rectangle;
public class entity {
    private int x, y;
    

    

    private int hitboxIndentionX = 0;
    
    private int hitboxIndentionY = 0;
    
    private double xspeed, yspeed;
    

    private Rectangle hitbox;
    
    private int derection;
    

    private boolean gravityAffected;
    private colitionDetector cd;
    
    private int jumpSpeed =5;
    private double minJumpTime =.1;
    private double maxJumpTime =.25;
    private int jumpCount =0;
    private boolean jumping = false;
    private boolean stillJumping;
    
    
    

    public void update(){
        hitbox.x = x+hitboxIndentionX;
        hitbox.y = y+hitboxIndentionY;
        checkJump();
        if(gravityAffected&&constants.gravity&&!jumping&&cd.checkY(0)){
            gravity();
        } 
    }
    
    private void checkJump() {
        if (jumping&&jumpCount<(constants.fps*minJumpTime)||(jumping&&jumpCount<constants.fps*maxJumpTime&&stillJumping)){
            jumpCount++;    
            yspeed = -jumpSpeed;
        }else{
            jumping = false;
            jumpCount =0;
        }
    }

    public void  jump(){
        if(!cd.checkY(0)&&!jumping){
            
            jumping = true;
            stillJumping = true;
            jumpCount = 0;
        }
        
        //TODO add entity jump methoid
    }
    //return true if nothing is in the way
    public boolean canmoveX(entity entity, int derection) {
        if(!cd.checkX(this.derection)){
            return false;
        }
        //check walls
        
        if(derection>0&&derection<4&&this.hitbox.x<=0){
            return  false;
        }
        
        if(derection>4&&derection<8&&!(this.hitbox.x+this.hitbox.width<constants.width*constants.tileSize)){
            return false;
        }
        return true;
    }
    
    public boolean canmoveY(entity entity) {
        if(cd.checkY(this.derection)){
            return true;
        }
        return false;
    }
    //return true if nothing is in the way
    public int canmove(entity entity,int derection){
        int output = 0;
        if(!this.canmoveX(entity, derection)){
            output = 1;
        }
        if(!this.canmoveY(entity)){
            output = 2;
        }
        if (!(this.canmoveX(entity, derection)||this.canmoveY(entity))){
            output = 4;
        }
        return output;
    }
    //gets the tile number of the top left corner
    public int getPos(){
        return this.y/constants.tileSize*constants.width+x/ constants.tileSize;
    }
    //reduces speed over time 
    public void friction() {
        if (this.yspeed>0){
            this.yspeed-=constants.friction;
        }
        if(this.yspeed<0){
            this.yspeed+=constants.friction;
        }
        if (this.xspeed>0){
            this.xspeed-=constants.friction;
        }
        if(this.xspeed<0){
            this.xspeed+=constants.friction;
        }
    }
    //implements gravity to the entity
    public void gravity() {
        if(canmoveY(this)){
            yspeed+=constants.gravityValue;   
        }
            
    }
    public void setGravityAffected(boolean gravityAffected) {
        this.gravityAffected = gravityAffected;
    }
    public boolean getGravityAffected(){
        return gravityAffected;
    }
    public void setHitboxIndentionX(int indention){
        this.hitboxIndentionX = indention;
    }
    public void setHitboxIndentionY(int indention){
        this.hitboxIndentionY = indention;
    }
    public int getHitboxIndentionX() {
        return hitboxIndentionX;
    }
    public int getHitboxIndentionY() {
        return hitboxIndentionY;
    }
    
    public colitionDetector getCD() {
        return cd;
    }

    public void setCD(colitionDetector cd) {
        this.cd = cd;
    }
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double getXspeed() {
        return xspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getDerection() {
        return derection;
    }

    public void setDerection(int derection) {
        this.derection = derection;
    }
    
    public boolean isStillJumping() {
        return stillJumping;
    }

    public void setStillJumping(boolean stillJumping) {
        this.stillJumping = stillJumping;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
