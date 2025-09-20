import java.util.ArrayList;

/**
 * The `level` class represents a game level, which includes multiple maps, 
 * tracks collected coins, and marks the level completion status.
 */
public class level {
    private ArrayList<map> maps = new ArrayList<>();  // List of maps in the level
    private boolean completed;  // Flag to track if the level is completed
    private short coins = 0;  // Counter for the number of coins collected
    private levelButtons button;

    public levelButtons getButton() {
        return button;
    }

    public void setButton(levelButtons button) {
        this.button = button;
    }

    // Constructor initializes the level with an array of map objects
    public level(map[] maps) {
        completed = false;  // Initialize level as incomplete
        coins = 0;  // Initialize coins count to zero
        for (map map : maps) {
            this.maps.add(map);  // Add each map to the level's map list
        }
    }

    // Method to increment the coin count, called when a coin is collected
    public void collectCoin() {
        coins++;
    }
    public int getCoins() {
        return coins;
    }

    // Method to mark the level as completed
    public void complete() {
        completed = true;
    }

    // Getter method to retrieve the list of maps in the level
    public ArrayList<map> getMaps() {
        return maps;
    }

    // Setter method to update the list of maps
    public void setMaps(ArrayList<map> maps) {
        this.maps = maps;
    }

    public void setlevelButton(levelButtons levelButton) {
        this.button = levelButton;
    }
}
