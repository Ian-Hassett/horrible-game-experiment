import java.io.File;  // Import the File class for file handling
import java.io.IOException;  // Import IOException to handle input/output errors
import java.io.FileWriter;  // Import FileWriter to write to files
import java.awt.Point;  // Import Point to represent x, y coordinates

// The mapEditor class provides functionality to modify, save, and load a game map
public class mapEditor {
    private static map map;            // The map object representing the game map
    private static File mapFile;       // File object to represent the map file
    private static String mapString;   // String to store map data in text format
    private static FileWriter mapWriter; // FileWriter to write map data to a file

    // Constructor initializes a new map and prepares it for editing
    public mapEditor() {
        changeMap();
    }

    /**
     * Change a specific item in the map string at a given tile and item index.
     * @param tile - the tile position in the map
     * @param itemIndex - index of the item to change
     */
    public static void changeItem(int tile, int itemIndex) {
        int tileIndex = locateTileIndex(tile);
        String start = mapString.substring(0, tileIndex + 1);
        String ItemSymble = ""+itemIndex;
        String end;
        if(mapString.substring(locateTileIndex(tile)+1,tileIndex+2).equals(",")){
            end = mapString.substring(tileIndex + 1, mapString.length());
        }else{
            end = mapString.substring(tileIndex + 2, mapString.length());
        }
        
        mapString = start+ItemSymble+end;
        System.out.println(mapString);
    }
    /**
     * Change a specific item in the map string at a given tile and item index.
     * @param tile - the tile position in the map
     */
    public static void changeItem(int tile) {
        int tileIndex = locateTileIndex(tile);
        String start = mapString.substring(0, tileIndex + 1);
        int itemnum ;
         
        String itemSymble;
        String end;
        if(mapString.substring(tileIndex+1,tileIndex+2).equals(",")){
            end = mapString.substring(tileIndex + 1, mapString.length());
            itemSymble = "0";
        }else if(Integer.parseInt(mapString.substring(tileIndex+1,tileIndex+2))+1>itemManager.getItemTypesCount()-1){
            end = mapString.substring(tileIndex + 2, mapString.length());
            itemSymble = "";
        }else{
            end = mapString.substring(tileIndex + 2, mapString.length());
            itemSymble = (Integer.parseInt(mapString.substring(tileIndex+1,tileIndex+2))+1)+"";
        }
        
        mapString = start+itemSymble+end;
        System.out.println(mapString);
    }

    // Loads the current map and prepares it for editing
    public static void changeMap() {
        map = tileManager.getMap();
        mapFile = new File("map" + map.getIndex());  // Set file based on map index
        setMapString(map.getMapsString());           // Load map data as a string
    }

    /**
     * Change a tile's type in the map string at a specific tile position.
     * @param tile - the tile position in the map
     * @param tiletype - new type of tile to set
     */
    private static void changeTile(int tile, int tiletype) {
        String start = mapString.substring(0, locateTileIndex(tile));
        String end = mapString.substring(locateTileIndex(tile) + 1, mapString.length());
        mapString = start + tileManager.intToSymble(tiletype) + end;
    }

    /**
     * Locate the index position of a tile in the map string.
     * @param tile - the tile index to locate
     * @return the position index in the map string
     */
    private static int locateTileIndex(int tile) {
        int commas = 0;
        if (tile == 0) return 0;  // Return start if tile is the first
        for (int i = 0; i < mapString.length(); i++) {
            if (mapString.charAt(i) == ',') {
                commas++;
                if (commas == tile) {
                    return i + 1;
                }
            }
        }
        System.out.println("Error: tile index not found");
        return 0; // Return 0 if tile not found
    }

    /**
     * Save the modified map to a file, updating the map with any changes.
     */
    static void save() {
        try {
            mapWriter = new FileWriter("maps/" + mapFile + ".txt");
            mapWriter.write(insertLineBreaks(mapString));
            mapWriter.close();
            System.out.println("Map saved successfully");
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace for any errors
        }
        updateMap();
        tileManager.updateMap();
    }

    /**
     * Insert line breaks into the map string to format the output.
     * @param mapString - the string representing map data
     * @return formatted map string with line breaks
     */
    public static String insertLineBreaks(String mapString) {
        int commas = 0;
        String newMapString = "";
        String oldMapString = mapString;
        for (int i = 0; i <= oldMapString.length(); i++) {
            if (commas == constants.width) {
                newMapString += "\n" + oldMapString.substring(0, i);
                oldMapString = oldMapString.substring(i);
                i = 0;
                commas = 0;
            }
            if (!oldMapString.isEmpty() && oldMapString.charAt(i) == ',') {
                commas++;
            }
        }
        return newMapString;
    }

    /**
     * Save a new map file with default map layout.
     */
    public static void saveAsNewMap() {
        try {
            mapWriter = new FileWriter("maps/" + mapFile + ".txt");
            // Write default map layout with tile type 9 and a bottom row of tile type 1
            mapWriter.write("9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,1,\n" +
                            "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,");
            mapWriter.close();
            System.out.println("Map cleared and saved as new");
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace for any errors
        }
        updateMap();
    }

    /**
     * Change the tile type where the mouse was clicked.
     */
    public static void changeTileFromClick() {
        Point mouse = game.getMousePoint();  // Get current mouse position
        int tile = tileFromPoint(mouse);     // Calculate tile from mouse position
        changeTile(tile, tileManager.simbleToInt(tileManager.getTileSimble(tile)) + 1);
        save();
        updateMap();
    }
     /**
     * Change an item in the map where the mouse was clicked.
     */
    public static void changeItemFromClick() {
        int tile = tileFromPoint(game.getMousePoint());
        changeItem(tile);
        
        save();
        map.gatherItems();
        updateMap();
    }

    /**
     * Convert a mouse position to a tile index.
     * @param p - the point representing mouse coordinates
     * @return tile index based on the point
     */
    public static int tileFromPoint(Point p) {
        return (p.x / constants.tileSize) + (p.y / constants.tileSize * constants.width);
    }

    /**
     * Update the current map data to reflect changes.
     */
    public static void updateMap() {
        tileManager.updateMap();
        setMapString(tileManager.getMapString());
    }

    // Getters and setters for mapString
    public static String getMapString() {
        return mapString;
    }

    public static void setMapString(String mapString) {
        mapEditor.mapString = mapString;
    }

   
}
