
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */
public class NormalZombie extends Zombie {

    public NormalZombie() {

    }

    public NormalZombie(int x, int y, JPanel panel) {
        super();
        name = "Zombie";
        hp = 200;
        damage = 100;
        hitbox = new Rectangle(x, y, 80, 140);

        normal = new ImageIcon((Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagenes/zombieIdle.gif"))));

        gamePanel = panel;
        zombieLabel.setBounds(hitbox);
        zombieLabel.setIcon(normal);

        gamePanel.add(zombieLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints((int) hitbox.getX(), (int) hitbox.getY(), 80, 140), 1);

    }

    @Override
    public void run() {
        zombieLabel.setIcon(normal);
        //checkear si el zombie esta muerto o si se paso del limite
        while (hp > 0 && hitbox.getX() + hitbox.getWidth() > 0) {
            zombieLabel.setVisible(true);
            if (collisionCheck(plantas) == null) {
           
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        hitbox.setLocation((int) hitbox.getX() - 1, (int) hitbox.getY());
                        zombieLabel.setLocation((int) hitbox.getX() - 1, (int) hitbox.getY());
                        gamePanel.add(zombieLabel, new AbsoluteConstraints((int) hitbox.getX(), (int) hitbox.getY(), 80, 140), 0);
                    }

                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //si entra en contacto con una planta ataca;
                Plant p = collisionCheck(plantas);

                zombieLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagenes/zombieAttack.gif"))));

                //ataca hasta q muera alguno de los 2
                while (p.getHp() > 0 && hp > 0) {
                    attack(p);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Zombie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                zombieLabel.setIcon(normal);

            }

        }

        if (hp <= 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    hitbox.setBounds(5, 0, 0, 0);
                    gamePanel.remove(zombieLabel);
                    gamePanel.repaint();
                }

            });

        }

        gamePanel.revalidate();
    }

    @Override
    public void attack(Plant p) {
        p.damage(damage);

        if (p.getHp() <= 0) {

            plantas.remove(p);

        }
    }

}
