
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class SunFlower extends Plant{
    private JLabel sunCont = new JLabel();
    
    public SunFlower() {
        super();
        
    }
    
    public SunFlower(JPanel panel, int x, int y, JLabel Casilla, JLabel sunCont){
        super();
        hp = 300;
        sunCost = 50;
        this.panel = panel;
        this.normal = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagenes/girasolGIF.gif")));
        this.casilla = Casilla;
        this.sunCont = sunCont;
        this.hitbox = new Rectangle(x, y, 80, 90);
      
     
        create();
    }
    
    
    public void action(){
        try {
            Thread.sleep(20000);
       
           
            SecureRandom random = new SecureRandom();
            int pos = random.nextInt(plantLabel.getWidth()) + plantLabel.getX();
            Sun sun = new Sun(panel, sunCont, pos, plantLabel.getY());
            sun.start();
            plantLabel.setIcon(normal);
        } catch (InterruptedException ex) {
        
            Logger.getLogger(SunFlower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
