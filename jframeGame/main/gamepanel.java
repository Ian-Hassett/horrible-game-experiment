import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;



public class gamepanel extends JPanel {
    private player player;
    private tileManager tm;

    private itemManager im;
    
    public gamepanel(){
        player = game.getPlayer();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        setFocusable(true);
        this.setBackground(new Color(180, 200, 230));
        tm = game.getTileManager();
        im = game.getItemMandager();
    }

    private void setLayout(FlowLayout flowLayout) {
        super.setLayout(flowLayout);
    }

    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        tm.draw(g2);
        im.drawitems(g2);
        player.draw(g2);
        g2.dispose();
        
    }
    public tileManager getTileManager(){
        return tm;
    }
}