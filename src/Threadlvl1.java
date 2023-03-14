
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Threadlvl1 extends Thread implements Serializable {

    private static final long SerialVersionUID = 66;
    private transient JPanel gamepanel = new JPanel();
    private JLabel finalWave = new JLabel();
    private ArrayList<Zombie> zombies = new ArrayList();
    private ArrayList<Plant> plants = new ArrayList();
    private ProgressBar p = new ProgressBar();
    private int cont = 0;

    public Threadlvl1() {
        super();
    }

    public Threadlvl1(JPanel gamepanel, JLabel finalWave, ProgressBar p) {
        super();
        this.gamepanel = gamepanel;
        this.finalWave = finalWave;
        this.p = p;

    }

    public JLabel getFinalWave() {
        return finalWave;
    }

    public void setFinalWave(JLabel finalWave) {
        this.finalWave = finalWave;
    }

    public JPanel getGamepanel() {
        return gamepanel;
    }

    public void setGamepanel(JPanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public void run() {
        //spawn zomies
        if (cont == 0) {
            for (int i = 0; i < 5; i++) {
                //y = 310
                Zombie z = new NormalZombie(800, 210, gamepanel);
                z.setPlantas(plants);
                z.setZombies(zombies);

                zombies.add(z);

            }
            cont++;
        }
        try {

            p.setLimit(3);
            if (!p.isAlive()) {
                p.start();
                Thread.sleep(15000);
            }
            int a = 0;
            zombies.get(0).start();
            while (a < 5) {
                Thread.sleep(100);
                if (zombies.get(a).getHp() <= 0) {
                    if (a < 2) {
                        a++;
                        zombies.get(a).start();
                    } else {
                        Thread.sleep(8000);
                        //final wave
                        finalWave.setVisible(true);
                        Thread.sleep(2000);
                        finalWave.setVisible(false);
                        a++;
                        zombies.get(a).start();
                        a++;
                        zombies.get(a).start();
                    }

                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Threadlvl1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
