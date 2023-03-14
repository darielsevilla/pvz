
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
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
public class Peashooter extends Plant {

    public Peashooter() {
        super();
    }

    public Peashooter(JPanel panel, int x, int y, JLabel casilla) {
        super();
        sunCost = 100;

       normal = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagenes/peashooterGif.gif")));
       
        hp = 300;
        this.panel = panel;
        this.hitbox = new Rectangle(x, y, 80, 90);
        this.casilla = casilla;

        create();

    }

    /**
     *
     * @param panel
     */
    public void action() {
        try {

            int contact = 0;
            for (Zombie zombie : zombies) {
                int zombieWid = ((int) zombie.getHitbox().getX() + ((int) zombie.getHitbox().getWidth()) / 2);
                int zombieHeight = ((int) zombie.getHitbox().getY() + (int) zombie.getHitbox().getHeight());
                int plantWid = ((int) this.getHitbox().getX() + (int) this.getHitbox().getWidth()) / 2;
                int plantHeight = ((int) this.getHitbox().getY() + ((int) this.getHitbox().getHeight()) / 2);
                //ver si un zombie esta en range
                if ((zombie.getHitbox().getX() > plantWid && zombie.getHitbox().getX() < panel.getWidth()) && (plantHeight > zombie.getHitbox().getY() && plantHeight <= zombieHeight)&& this.hitbox.getY()>zombie.getHitbox().getY()) {

                    //cambiar a icono de ataque 
                    /**EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                           plantLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagenes/PeaShooter_Spit.gif"))));
                        }

                    });*/

                    Thread.sleep(1000);

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Pea pea = new Pea(((int) hitbox.getX()) + 80, ((int) hitbox.getY()) + 30, panel);
                            pea.setZombies(zombies);
                            pea.start();
                        }

                    });
                    
                    

                    Thread.sleep(800);

                    contact++;

                }
                if (contact == 0 && !plantLabel.getIcon().equals(normal)) {
                    plantLabel.setIcon(normal);
                    Thread.sleep(10);

                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Peashooter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
