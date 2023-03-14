/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class ClickeablePlant extends JLabel implements Serializable{

    private transient JPanel panel;
    private ArrayList<Plant> placedPlants = new ArrayList();
    private ArrayList<Zombie> zombies = new ArrayList();
    private final static long SerialVersionUID = 432;
    
    private JLabel sunCount = new JLabel();
    private int plantSelected = 0;

    public ClickeablePlant() {
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){          
                place(sunCount, plantSelected);         
            }
        });
    }

    public ClickeablePlant(JPanel panel, ArrayList<Plant> placedPlants, ArrayList<Zombie> zombies) {
        this.panel = panel;
        this.placedPlants = placedPlants;
        this.zombies = zombies;
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){             
                place(sunCount, plantSelected);            
            }
        });
    }

    public ClickeablePlant(JPanel panel, int x, int y, int width, int height, JLabel sunCount) {
        this.panel = panel;
        this.setBounds(x, y, width, height);
        this.sunCount = sunCount;
        this.setVisible(true);
       
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                place(sunCount, plantSelected);
             
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ArrayList<Plant> getPlacedPlants() {
        return placedPlants;
    }

    public void setPlacedPlants(ArrayList<Plant> placedPlants) {
        this.placedPlants = placedPlants;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public JLabel getSunCount() {
        return sunCount;
    }

    public void setSunCount(JLabel sunCount) {
        this.sunCount = sunCount;
    }

    public int getPlantSelected() {
        return plantSelected;
    }

    public void setPlantSelected(int plantSelected) {
        this.plantSelected = plantSelected;
    }

    
    public void place(JLabel x1, int plantSelected) {
        int x = Integer.parseInt(x1.getText());
        if (isEnabled()) {
            if(plantSelected == 1 && x >= 100){
                this.setEnabled(false);
                
                Plant p = new Peashooter(panel, this.getX() , this.getY(), (JLabel) this);
                p.setZombies(zombies);
                placedPlants.add(p);
                p.start();
                x-=100;
            }else if(plantSelected == 2 && x >= 50){
                this.setEnabled(false);
                Plant p = new SunFlower(panel, this.getX(), this.getY(), (JLabel) this, sunCount);
                p.setZombies(zombies);
                placedPlants.add(p);
                
                p.start();
                x -= 50;
            }else if(plantSelected == 3 && x >= 150){
                this.setEnabled(false);
                x -= 150;
                Plant p = new CherryBomb(panel, this.getX(), this.getY(), (JLabel) this);
                p.setZombies(zombies);
                placedPlants.add(p);
                p.start();
            }
            x1.setText(Integer.toString(x));
            
        }
        
    }

}
