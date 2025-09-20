import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
public class game implements Runnable{
    private static myUi ui;
    private static Thread gamethread;
    private keyinputs keyinputs; 
    private static player player;
    private static tileManager tm;
    

    private static itemManager im;
    private static soundSystem soundSystem;
    private static Point mousePoint;
    

    private static ArrayList<level> levels;
    private static boolean levelSelected;
    private static level currentLevel;
    private static game thegame;
    public game(){
        thegame = this;
        intiulaizeSystems();
        ui.createLevelSelectPanel();
        while (!levelSelected){
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        startTread();
    }
    
    public static level getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(level currentLevel) {
        game.currentLevel = currentLevel;
    }
    
    public static player getPlayer() {
        return player;
    }

    public void setPlayer(player player) {
        this.player = player;
    }

    static void levelSelect(int index) {
        currentLevel = levels.get(index);
        levelSelected = true;
    }

    //creats an object of the systems
    private void intiulaizeSystems() {
        
        soundSystem = new soundSystem();
        tm = new tileManager();
        keyinputs = new keyinputs(tm);
        player = new player(keyinputs,this);
        im = new itemManager(player,soundSystem);
        generateLevels();
        ui= new myUi(this);
        generatePanelListeners();
    }
    private void generatePanelListeners() {
        ui.getPanel().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isShiftDown()){
                    //mapEditor.changeItem(tileManager.tileFromPoint(getMousePoint()),0);
                    mapEditor.changeItemFromClick();
                }else{
                    mapEditor.changeTileFromClick();
                }
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        } );
        ui.getPanel().addKeyListener(keyinputs);
    }

    private void generateLevels() {
        levels = new ArrayList<level>();
        for (int i = 0; i < constants.levelCount; i++) {
            level level = new level(new map[]{tileManager.getMaps().get(i*2),tileManager.getMaps().get(i*2+1)});
            levels.add(level);
        }
    }

    private void startTread() {
        tm.ChangeMap(0);
        gamethread = new Thread(this);
        ui.startGame();
        gamethread.start();
        if(constants.songOn){
            soundSystem.loop("sound/blue_skies.wav", constants.repititions);
        }
        
    }
        @Override
    public void run() {
        long next = System.nanoTime()+(1000000000/constants.fps);
        //System.out.println(mapEditor.insertLineBreaks(tm.mapString));
        while(gamethread!=null){

            update();
            displayValues();
            draw();
            try {
                long timeLeft = next-System.nanoTime();
                if (timeLeft<=0){
                    timeLeft = 0;
                }

                Thread.sleep(timeLeft/1000000);
                next +=(1000000000/constants.fps);
                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
    
    private void draw() {
        ui.fillScreen();
    }

    private void update() {
        //player updates 
        player.update();
        im.checkItems();
        im.checkItems(tileManager.getMap().getItems());
        //other updates
        mousePoint = ui.getPanel().getMousePosition();
        if(constants.develupmentMode){
            
        }
        
    }
    //displays values every update
    private void displayValues() {
        try {
            System.out.println(levelSelected);
        } catch (Exception e) {
           System.out.println("problem in diplay values");
        }
         

    }
    public keyinputs getkeyInputs() {
        return keyinputs;
    }
    public static tileManager getTileManager() {
        return tm;
    }
    public static itemManager getItemMandager() {
        return im;
    }
    public static Point getMousePoint() {
        return mousePoint;
    }

    public static void setMousePoint(Point mousePoint) {
        game.mousePoint = mousePoint;
    }

    public static void win() {
        game.levelSelected = false;
        currentLevel.getButton().setCoinCount(currentLevel.getCoins());
        ui.Levelselect();
        while (!levelSelected){
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        thegame.startTread();
        
        // ui.getWindow().dispose();
        // System.out.println("you win");
        // gamethread.interrupt();
        // turnOff();
    }

    private static void turnOff() {
        ui.closeApplication();
    }
    
    public static tileManager getTM() {
        return tm;
    }

    public static void setTm(tileManager tm) {
        game.tm = tm;
    }

    public static ArrayList<level> getLevels() {
       return levels;
    }
}
