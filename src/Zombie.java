
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public abstract class Zombie extends Thread implements Serializable {
    protected String name;
    protected int hp;
    protected ImageIcon normal;
    protected Rectangle hitbox;
    protected int damage;
    protected transient ArrayList<Plant> plantas = new ArrayList();
    protected transient ArrayList<Zombie> zombies = new ArrayList();
    protected  JLabel zombieLabel = new JLabel();
    protected transient JPanel gamePanel;

    private static final long SerialVersionUID=777;
    public Zombie() {
    }

    public Zombie(String name, int hp, ImageIcon normal,  Rectangle hitbox, JPanel gamePanel) {
        //80, 140
        this.name = name;
        this.hp = hp;
        this.normal = normal;
        
        this.hitbox = hitbox;
        zombieLabel.setBounds(hitbox);
        zombieLabel.setIcon(normal);
        this.gamePanel = gamePanel;
        
        
        gamePanel.add(zombieLabel,new org.netbeans.lib.awtextra.AbsoluteConstraints((int) hitbox.getX(), (int)hitbox.getY(), 80, 140),1);
        
    }

    

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Plant> getPlantas() {
        return plantas;
    }

    public void setPlantas(ArrayList<Plant> plantas) {
        this.plantas = plantas;
    }

    public JLabel getZombieLabel() {
        return zombieLabel;
    }

    public void setZombieLabel(JLabel zombieLabel) {
        this.zombieLabel = zombieLabel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public ImageIcon getNormal() {
        return normal;
    }

    public void setNormal(ImageIcon normal) {
        this.normal = normal;
    }


    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
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

    
    @Override
    public String toString() {
        return "Zombie{" + "name=" + name + ", hp=" + hp + ", normal=" + normal +  '}';
    }
    
    public abstract void attack(Plant p);
    
    public void damage(int dmg) {
        this.hp -= dmg;
       
   
    }
    
    public Plant collisionCheck(ArrayList<Plant> plants){
        for (Plant plant : plants) {
        
            if(hitbox.intersects(plant.getHitbox()) && (plant.getHitbox().getY() > hitbox.getY())){
                return plant;
            }
        }
        return null;
    }

 
        
    
    
    }
