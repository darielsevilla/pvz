
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */
public class Pea extends Thread {

    private ImageIcon icono;
    private Rectangle hitbox;
    private int damage;
    private JLabel peaLabel = new JLabel();
    private JPanel panel = new JPanel();
    private ArrayList<Zombie> zombies = new ArrayList();

    public Pea() {

    }

    public Pea(int x, int y, JPanel panel) {
        icono = new javax.swing.ImageIcon(getClass().getResource("/imagenes/pea2.png"));
        hitbox = new Rectangle(x, y, 20, 20);

        peaLabel.setBounds(hitbox);
        peaLabel.setIcon(icono);
        this.panel = panel;
        damage = 20;
        this.panel.add(peaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints((int) hitbox.getX(), (int) hitbox.getY(), 20, 20), 0);

    }

    public void run() {

        while (collisionDetection(zombies) == null) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    peaLabel.setLocation((int) hitbox.getX() + 4, (int) hitbox.getY());
                    hitbox.setLocation((int) hitbox.getX() + 4, (int) hitbox.getY());
                    panel.add(peaLabel, new AbsoluteConstraints((int)hitbox.getX(),(int) hitbox.getY(),(int) hitbox.getWidth(), (int)hitbox.getHeight()), 0);
                }

            });

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (collisionDetection(zombies) != null) {
            Zombie p = collisionDetection(zombies);
            p.damage(this.damage);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    peaLabel.setVisible(false);
                    panel.remove(peaLabel);
                    p.getZombieLabel().setEnabled(false);
                }

            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pea.class.getName()).log(Level.SEVERE, null, ex);
            }
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                     p.getZombieLabel().setEnabled(true);
                }

            });
           

        }

        if (hitbox.getX() > panel.getWidth() + 10) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    peaLabel.setVisible(false);
                    panel.remove(peaLabel);

                }

            });
            
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pea.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public JLabel getPeaLabel() {
        return peaLabel;
    }

    public void setPeaLabel(JLabel peaLabel) {
        this.peaLabel = peaLabel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public String toString() {
        return "Pea{" + "icono=" + icono + ", hitbox=" + hitbox + ", damage=" + damage + ", peaLabel=" + peaLabel + ", panel=" + panel + '}';
    }

    public Zombie collisionDetection(ArrayList<Zombie> zombies) {

        for (Zombie zombie : zombies) {
            if (hitbox.intersects(zombie.getHitbox())) {

                return zombie;
            }
        }
        return null;
    }

}
