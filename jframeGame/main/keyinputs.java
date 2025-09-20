import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyinputs implements KeyListener {
    boolean wPressed,aPressed,sPressed,dPressed,qPressed,ePressed,spacePressed;
    tileManager tm; 
    public keyinputs(tileManager tm){
        this.tm = tm;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            wPressed = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            aPressed = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            sPressed = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            dPressed = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_Q){
            qPressed = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            ePressed = true;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            spacePressed = true;
        }

        if (e.getKeyCode()>=48&&e.getKeyCode()<=57){
            tm.ChangeMap(e.getKeyCode()-48-1);
        }

        if (e.getKeyCode()== KeyEvent.VK_ENTER){
            mapEditor.save();
        }
        if (e.getKeyCode()== KeyEvent.VK_BACK_SPACE){
            mapEditor.saveAsNewMap();
        }
        if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            System.out.println("Keys ctrl+z pressed!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            wPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            aPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            sPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            dPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_Q){
            qPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            ePressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }
    
}
