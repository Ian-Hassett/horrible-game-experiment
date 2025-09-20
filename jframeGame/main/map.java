import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.lang.Character;
import java.util.ArrayList;
public class map {
    /**
     *
     */
    private File mapFile;
    private int width =17;
    
    private int height = 14;
    private int index;
    private ArrayList<item> items = new ArrayList<item>();

    public ArrayList<item> getItems() {
        return items;
    }


    public void setItems(ArrayList<item> items) {
        this.items = items;
    }


    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }
    

    public void setMapFile(File mapFile) {
        this.mapFile = mapFile;
    }

    
    public map(String string) {
        mapFile = new File(string);
    }
    public map(String string,int index) {
        mapFile = new File(string);
        this.index = index;
    }
    public File getMapFile() {
        return mapFile;
    }
    public void additem(item item){
        items.add(items.size(), item);
    }

    //uninplemented methiods
    // public void setTile(){
    // }
    
    // public void removeItem(){     
    // }


    // create list of the items from a mapString
    public void gatherItems() {
        String mapstring = getMapsString();
        int commas = 0;
        int pastComma =0;
        items.clear();
        for (int charictor =0; charictor<= mapstring.length()-1; charictor++) {
            if (mapstring.charAt(charictor)==','){
                commas++;
                pastComma = 0;
            }else{
                
                if(pastComma>=1){
                    createItem(mapstring.charAt(charictor), commas);
                    System.out.println("added item of type:" + mapstring.charAt(charictor));
                }
                pastComma++;
            }
        } 
    }

    //assignes items to symbles
    private void createItem(char itemType,int location) {
        Point tileLocation = getPositionFromTile(location);
        
        if(Integer.parseInt(Character.toString(itemType))==0){
            items.add(items.size(), new coin((int)tileLocation.getX(), (int)tileLocation.getY(),1));
        }else if (Integer.parseInt(Character.toString(itemType))==1){
            items.add(items.size(), new gravityPotion((int)tileLocation.getX(), (int)tileLocation.getY(),1));
        }else if (Integer.parseInt(Character.toString(itemType))==2){
            items.add(items.size(), new speedBoots((int)tileLocation.getX(), (int)tileLocation.getY(),1));
        }else if (Integer.parseInt(Character.toString(itemType))==3){
            items.add(items.size(), new goal((int)tileLocation.getX(), (int)tileLocation.getY(),1));
        }
    }
    public Point getPositionFromTile(int location) {
        System.out.print(location);
        Point Position = new Point(location%width*constants.tileSize,location/width*constants.tileSize); 

        return Position;
    }
    public static Point getPositionFromTile(int location,int width) {
        System.out.println(location);
        Point Position = new Point(location%width*constants.tileSize,location/width*constants.tileSize); 
        return Position;
    }
    public String getMapsString() {
        Scanner myReader;

        String data = "";
        try {
            myReader = new Scanner(getMapFile());
            while (myReader.hasNextLine()) {
                data = data +myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return(data);
    }
}
