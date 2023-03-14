
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class CherryBomb extends Plant {

    public CherryBomb() {
        super();
    }
    
    public CherryBomb(JPanel panel, int x, int y, JLabel casilla){
        super();
        sunCost = 150;
        
        hp = 300;
        this.panel = panel;
        this.panel = panel;
        this.hitbox = new Rectangle(x, y, 80, 90);
        this.casilla = casilla;
        plantLabel = new JLabel();
        normal = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagenes/cherryBomb.gif")));
        plantLabel.setIcon(normal);
        plantLabel.setBounds(x-80, y-90, 240, 270);
        panel.add(plantLabel, new AbsoluteConstraints(plantLabel.getX(), plantLabel.getY(), plantLabel.getWidth(), plantLabel.getHeight()),1);
        plantLabel.setVisible(true);
       
    }
    @Override
    public void action() {
        for (Zombie z : zombies) {
            Rectangle rect = new Rectangle(plantLabel.getBounds());
            if(z.getHitbox().intersects(rect)){
                z.damage(z.getHp());
                z.getZombieLabel().setVisible(false);
                
            }
        }
        
        
        this.hp = 0;
    }

    @Override
    public String toString() {
        return "CherryBomb - " + hp;
        
    }
    
    
    
    
}
