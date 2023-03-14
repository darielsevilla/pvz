
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.GroupLayout.Alignment.CENTER;
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
public abstract class Plant extends Thread implements Serializable{

    private static final long SerialVersionUID=501;
    
    private int running = 0;
    protected int hp;
    protected String name;
    protected ImageIcon normal;

    protected Rectangle hitbox;
    protected transient JPanel panel;
    protected JLabel plantLabel = new JLabel();
    protected int sunCost;
    protected ArrayList<Zombie> zombies = new ArrayList();
    protected JLabel casilla = new JLabel();

    public Plant() {
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Plant(int hp, String name, ImageIcon normal, JPanel panel, int x, int y, int suncost, JLabel casilla) {
        this.hp = hp;
        this.name = name;
        this.normal = normal;

        this.hitbox = new Rectangle(x, y, 80, 90);

        this.panel = panel;
        this.casilla = casilla;

    }

    public void create() {
        plantLabel = new JLabel();
        plantLabel.setBounds(hitbox);

        plantLabel.setIcon(normal);
        plantLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(plantLabel, new AbsoluteConstraints((int) hitbox.getX(), (int) hitbox.getY(), 80, 90), 1);
        plantLabel.setVisible(true);

    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }


    public int getSunCost() {
        return sunCost;
    }

    public void setSunCost(int sunCost) {
        this.sunCost = sunCost;
    }

    public JLabel getCasilla() {
        return casilla;
    }

    public void setCasilla(JLabel casilla) {
        this.casilla = casilla;
    }

    
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public ImageIcon getNormal() {
        return normal;
    }

    public JLabel getPlantLabel() {
        return plantLabel;
    }

    public void setPlantLabel(JLabel plantLabel) {
        this.plantLabel = plantLabel;
    }

    public void setNormal(ImageIcon normal) {
        this.normal = normal;
    }

    public abstract void action();

    @Override
    public String toString() {
        return "Plant{" + "hp=" + hp + ", name=" + name + ", normal=" + normal + '}';
    }

    public void run() {

        while (hp > 0) {

            if (running == 0) {
                try {
                    if(this instanceof CherryBomb){
                        Thread.sleep(1000);
                    }else{
                        Thread.sleep(1500);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
                }
                running++;
            }
            action();

        }
        plantLabel.setIcon(normal);
        if (hp <= 0) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    plantLabel.setVisible(false);
                    panel.remove(plantLabel);
                    casilla.setEnabled(true);

                }

            });

            hitbox = new Rectangle(-100, -100, -1, -1);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void damage(int x) {
        hp -= x;
        if (hp <= 0) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    plantLabel.setVisible(false);
                    panel.remove(plantLabel);
                    hitbox = new Rectangle(-100, -100, -1, -1);
                    casilla.setEnabled(true);

                }

            });

        }

    }

}
