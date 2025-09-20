import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class constants {

    public static boolean resizable = false;
    public static String title = "my game";

    //screen dementions
    public static short width = 17;
    public static short height = 14;

    public static Component windowRelitive = null;
    public static boolean AlwaysOnTop = false;
    public static int DefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
    public static Color backgroundColor = new Color(100, 100, 200);
    public static ImageIcon icon = new ImageIcon("images/icon.png");
    public static short inputW = 200;
    public static Object tileSizecontants;
    static final int imageSize =16;
    static final int imageScale = 3;
    static final int tileSize = imageScale*imageSize;
    static final double movementSpeed = 1;
    public static final int fps = 60;
    public static final double friction = .5;
    public static final int maxSpeed = 5;
    public static final double gravityValue = 1;
    public static final int startingXPos = 2;
    public static final int startingYPos = 11;
    public static final boolean gravity = true;
    public static final boolean develupmentMode = false;//TODO false when done
    public static final int numberOfMaps = 10;
    public static final boolean songOn = false;
    public static final int repititions = 10000;
    public static final int levelCount = 4;
    public static final boolean sound = true;  
}