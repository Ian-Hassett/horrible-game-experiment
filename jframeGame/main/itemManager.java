import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class itemManager {
    // Fields to store player, items list, player's hitbox, and sound system
    private player player;
    private static ArrayList<item> staticItems = new ArrayList<item>();
    private static int itemTypesCount = 4;
    
    private Rectangle playerhitBox;
    private soundSystem soundSystem;

    // Constructor that initializes itemManager with a player object
    public itemManager(player player){
        this.player = player;
        playerhitBox = player.getHitbox();  // Gets the player's hitbox from the player object
        item.setIM(this);  // Sets a reference of this itemManager in the item class
    }

    // Overloaded constructor that also includes soundSystem as a parameter
    public itemManager(player player, soundSystem soundSystem){
        this.player = player;
        playerhitBox = player.getHitbox();  // Gets the player's hitbox from the player object
        this.soundSystem = soundSystem;  // Sets the sound system
        item.setIM(this);  // Sets a reference of this itemManager in the item class
    }

    // Checks for collision between the player and each item in the items list
    public void checkItems(){
        for(item item : staticItems){
            if(item.checkPlayerColition(playerhitBox)){  // If player collides with the item
                item.activate(player);  // Activates the item for the player
                deleteItem(item);  // Deletes the item after activation
                break;  // Exits loop after finding the first collision
            }
        }
    }

    // Checks for collision in a given list of items and removes an item if collision is detected
    public void checkItems(ArrayList<item> items){
        for(item item : items){
            if(item.checkPlayerColition(playerhitBox)){  // If player collides with the item
                item.activate(player);  // Activates the item for the player
                deleteItem(items, item);  // Deletes the item from the given list
                break;  // Exits loop after finding the first collision
            }
        }
    }

    // Deletes a specific item from a given list of items
    private void deleteItem(ArrayList<item> items, item item) {
        items.remove(item);
    }

    // Draws items on the screen using a Graphics object
    public void drawitems(Graphics g){
        for(item item : staticItems){
            item.draw(g);  // Draws each item from the items list
        }
        for(item item : tileManager.getMap().getItems()){
            item.draw(g);  // Draws items from the map's list of items
        }
    }

    // Deletes a specific item from the main items list
    public void deleteItem(item item){
        staticItems.remove(item);
    }

    // Getter and setter for soundSystem
    public soundSystem getSoundSystem() {
        return soundSystem;
    }
    public void setSoundSystem(soundSystem soundSystem) {
        this.soundSystem = soundSystem;
    }

    // Static getter and setter for the items list
    public static ArrayList<item> getItems() {
        return staticItems;
    }
    public static void setItems(ArrayList<item> items) {
        itemManager.staticItems = items;
    }
    
    public static int getItemTypesCount() {
        return itemTypesCount;
    }

    public static void setItemTypesCount(int itemTypesCount) {
        itemManager.itemTypesCount = itemTypesCount;
    }
}

