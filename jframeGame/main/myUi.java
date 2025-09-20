
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class myUi {

    private JFrame window;  // The main window (JFrame) that holds the UI
    private gamepanel panel;  // The main game panel where the game is displayed
    private JPanel levelSelect;  // Panel that holds the level selection screen
    
    // Constructor to initialize the level selection screen
    public myUi(game game){
        window = new JFrame();  // Create the main window (JFrame)
        levelSelect = new JPanel();  // Create a panel for level selection
        panel = new gamepanel(); // Create the game panel to display the game
        Levelselect();  // Initialize the level selection screen
    }
    
    // Start the game by switching from the level selection screen to the main game screen
    public void startGame(){
        window.remove(levelSelect);  // Remove the level select screen from the window
        window.add(panel);           // Add the game panel to the window
        
        window.repaint();            // Repaint the window to refresh the UI
        panel.requestFocus();        // Request focus for the game panel to capture user input
        intiulaizeWindow(window);    // Initialize the window (set size, title, etc.)
        panel.setSize((constants.width-1)*constants.tileSize, constants.height*constants.tileSize); // Set the size of the game panel
    }

    // Initializes the level select screen by setting up the window and adding components
    public void Levelselect() {
        window.remove(panel);
        window.add(levelSelect);     // Add the level selection panel to the window
        intiulaizeWindow(window);   // Initialize window properties (title, size, etc.)
        panel.setSize((constants.width-1)*constants.tileSize, constants.height*constants.tileSize); // Set the size of the game panel
    }

    // Initializes the window properties like size, title, resizability, etc.
    public void intiulaizeWindow(JFrame window){
        window.setAlwaysOnTop(constants.AlwaysOnTop); // Whether the window should stay on top of other windows
        window.setTitle(constants.title);             // Set the window's title
        window.setSize((constants.width-1)*constants.tileSize, constants.height*constants.tileSize); // Set the window's size
        window.setLocationRelativeTo(constants.windowRelitive); // Set the window's position on the screen
        window.setResizable(constants.resizable);     // Whether the window is resizable
        window.setDefaultCloseOperation(constants.DefaultCloseOperation); // Define the default close operation
        window.setIconImage(constants.icon.getImage()); // Set the window's icon
        window.setVisible(true); // Make the window visible on the screen
    }

    // Getter method for the game panel
    public JPanel getPanel(){
        return panel;  // Return the game panel
    }

    // Getter method for the window
    public JFrame getFrame(){
        return window;  // Return the window
    }

    // Repaints the game panel to refresh the screen
    public void fillScreen(){
        panel.repaint();  // Repaint the game panel to update the display
    }

    // Closes the application by dispatching the window close event
    public void closeApplication() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)); // Close the window
    }

    // Method to create and add buttons for each level on the level select screen
    public void createLevelSelectPanel(){
        addLevelButtons();  // Add level buttons to the level select panel
        window.repaint();
    }

    // Method that adds buttons to the level selection screen
    private void addLevelButtons() {
        try {
            new JButton("", new ImageIcon(ImageIO.read(new File("images/0Stars.png"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Loop through each level and create a button for each
        for (int i = 0; i < constants.levelCount; i++) {
            // Create a button labeled with the level number
            levelButtons b = new levelButtons("Level" + (i + 1), i);
            
            // Add an action listener to the button to handle clicks
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Print a message when a button is pressed
                    System.out.println("buttons pressed " + b.getIndex());
                    
                    // Call the game logic to start the selected level
                    game.levelSelect(b.getIndex());
                }
            });
            
            b.setSize(100, 100);  // Set the size of the button
            b.setLocation(new Point(b.getIndex() * 100, 0)); // Position the buttons horizontally
            levelSelect.add(b);  // Add the button to the level selection panel
            
            // Log the position where the button is added
            System.out.println("added button " + b.getX());
        }
    }
    
    // Setter method for setting the game panel
    public void setPanel(gamepanel panel) {
        this.panel = panel;  // Set the game panel
    }

    // Getter method for the window
    public JFrame getWindow() {
        return window;  // Return the window
    }

    // Setter method for setting the window
    public void setWindow(JFrame window) {
        this.window = window;  // Set the window
    }
}
