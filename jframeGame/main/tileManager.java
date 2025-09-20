import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Character;
import java.util.ArrayList;

// tileManager class: Manages maps, tiles, and interactions related to game maps.
public class tileManager {
    
    // List to store multiple maps.
    private static ArrayList<map> maps = new ArrayList<map>();

    // The current map object.
    private static map map;

    // Getter for the current map.
    public static map getMap() {
        return map;
    }

    // Setter for the current map.
    public static void setMap(map map) {
        tileManager.map = map;
    }

    // Array to hold tile types.
    private static tile[] tiles;
    
    // Getter for the tiles array.
    public static tile[] getTiles() {
        return tiles;
    }

    // Variable to track the current map number.
    private static int mapnum = 1;

    // Getter for the map number.
    public static int getMapnum() {
        return mapnum;
    }

    // Setter for the map number.
    public static void setMapnum(int mapnum) {
        tileManager.mapnum = mapnum;
    }

    // Variable to store the map file.
    private static File mapFile;

    // Getter for the map file.
    public static File getMapFile() {
        return mapFile;
    }

    // Setter for the map file.
    public static void setMapFile(File mapFile) {
        tileManager.mapFile = mapFile;
    }

    // String to store the map data.
    static String mapString;

    // Getter for the map data string.
    public static String getMapString() {
        return mapString;
    }

    // Setter for the map data string.
    public static void setMapString(String mapString) {
        tileManager.mapString = mapString;
    }

    // An instance of itemManager to manage items in the game.
    static itemManager im;
    
    // Getter for itemManager instance.
    public static itemManager getIm() {
        return im;
    }

    // Setter for itemManager instance.
    public static void setIm(itemManager im) {
        tileManager.im = im;
    }

    /**
     * Constructor for tileManager class.
     * Initializes maps, tiles, and sets the initial map.
     */
    public tileManager(){
        createMaps();  // Create the available maps.
        mapFile = new File("maps/map"+mapnum+".txt");  // Set the initial map file.
        map = new map("maps/map"+mapnum+".txt");  // Create a map object with the file.
        
        // Create tile types with corresponding images and collision properties.
        tiles = new tile[12];
        tiles[0]= new tile("images/grass.png", true);  // Grass tile with collision.
        tiles[1]= new tile("images/barrier.png", true);  // Barrier tile with collision.
        tiles[2]= new tile("images/bricks.png", false);  // Bricks tile without collision.
        tiles[3]= new tile("images/redbrick2.png", true);  // Red brick tile with collision.
        tiles[4]= new tile("images/bricks.png", true);  // Bricks tile with collision.
        tiles[5]= new tile("images/coin.png", false);  // Coin tile without collision.
        tiles[9]= new tile("images/blank.png", false);  // Blank tile without collision.

        // Change to the initial map.
        ChangeMap(mapnum);
    }

    /**
     * Creates and initializes maps based on the number of maps defined in constants.
     */
    private void createMaps() {
        for (int i = 0; i <= constants.numberOfMaps; i++){
            maps.add(maps.size(), new map("maps/map"+i+".txt", i));  // Create and add new map objects.
            maps.get(i).gatherItems();  // Gather items for each map.
        }
    }

    /**
     * Draws the map on the screen using the given Graphics2D object.
     * Loops through the map and renders each tile.
     */
    public void draw(Graphics2D g){
        int tileSize = constants.tileSize;  // Tile size from constants.
        int x, y, tileNum;  // Coordinates and tile number.
        y = 0; 
        x = 0;
        
        // Iterate over all the tiles in the map.
        while (y <= map.getHeight() - 1) {
            tileNum = simbleToInt(getTileSimble(y * (map.getWidth()) + x));  // Get tile number from symbol.
            try {
                g.drawImage(tiles[tileNum].getImage(), x * tileSize, tileSize * y, tileSize, tileSize, null);  // Draw the tile image.
            } catch (Exception e) {
                // Handle any exceptions related to drawing.
            }
            
            // Move to the next position.
            if (x >= map.getWidth() - 1){
                x = 0;
                y++;
            } else {
                x++;
            }
        }
    }

    /**
     * Reads the map file and returns an ArrayList of strings representing map data.
     * Each string represents a line in the map file.
     */
    public ArrayList<String> getMapsArray() {
        Scanner myReader;
        ArrayList<String> arr = new ArrayList<>();
        try {
            myReader = new Scanner(mapFile);
            while (myReader.hasNextLine()) {
                arr.add(myReader.nextLine());  // Add each line of the map file to the ArrayList.
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  // Handle file not found exceptions.
        }
        return arr;
    }

    /**
     * Returns the entire map as a single string by reading the map file.
     */
    public static String getmapsString() {
        Scanner myReader;
        String data = "";
        try() {
            myReader = new Scanner(map.getMapFile());
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();  // Append each line to the data string.
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  // Handle file not found exceptions.
        }
        return data;
    }

    /**
     * Returns the tile type (as an integer) located at the specified position.
     */
    public static int getTileType(double d) {
        try {
            return Integer.parseInt(Character.toString(mapString.charAt((int) d)));  // Get the integer representation of the tile type.
        } catch (Exception e) {
            System.out.println("zero error");  // Handle any parsing exceptions.
            return 0;  // Return a default value in case of an error.
        }
    }

    /**
     * Getter for the list of maps.
     */
    public static ArrayList<map> getMaps() {
        return maps;
    }

    /**
     * Setter for the list of maps.
     */
    public static void setMaps(ArrayList<map> maps) {
        tileManager.maps = maps;
    }

    /**
     * Returns the symbol (character) of the tile at the specified position.
     */
    public static char getTileSimble(double d) {
        try {
            int commas = 0;
            for (int i = 0; i <= mapString.length(); i++) {
                if (mapString.charAt(i) == ',') {
                    commas++;
                    if (commas >= d) {
                        return mapString.charAt(i + 1);  // Return the symbol after the d-th comma.
                    }
                }
            }
        } catch (Exception e) {
            // Handle exceptions related to symbol retrieval.
        }
        return '!';  // Return a default symbol if an error occurs.
    }

    /**
     * Checks if the specified tile type has a collision.
     */
    public static boolean hasColition(int tile) {
        try {
            return tiles[tileManager.simbleToInt(tileManager.getTileSimble(tile))].getColition();  // Check if the tile has a collision.
        } catch (NullPointerException e) {
            System.out.println("tile was missing");
            return true;  // Return true if the tile was not found.
        }
    }

    /**
     * Updates the map data by reading the latest map string from the file.
     */
    public static void updateMap() {
        mapString = getmapsString();  // Update the map string with the current map data.
    }

    /**
     * Changes the current map to the one specified by the map number.
     */
    public void ChangeMap(int mapnum) {
        try {
            map = game.getCurrentLevel().getMaps().get(mapnum);  // Set the current map based on the map number.
            mapEditor.changeMap();  // Change the map in the editor.
            updateMap();  // Update the map data.
        } catch (Exception e) {
            // Handle any exceptions related to changing the map.
        }
    }

    /**
     * Converts a single character symbol to an integer value.
     */
    public static int simbleToInt(char simble) {
        try {
            return Integer.parseInt(Character.toString(simble));  // Parse the symbol to an integer.
        } catch (Exception e) {
            switch (simble) {
                case '@': return 10;  // Special case for '@' symbol.
            }
        }
        return 9;  // Return default value in case of an error.
    }

    /**
     * Converts an integer tile type to its corresponding symbol.
     */
    public static char intToSymble(int type) {
        switch (type) {
            case 10:
                return '@';  // Special case for tile type 10.
            default:
                return Integer.toString(type).charAt(0);  // Return the corresponding symbol for other tile types.
        }
    }
    /**
     * Convert a mouse position to a tile index.
     * @param p - the point representing mouse coordinates
     * @return tile index based on the point
     */
    public static int tileFromPoint(Point p) {
        return (p.x / constants.tileSize) + (p.y / constants.tileSize * constants.width);
    }
}
