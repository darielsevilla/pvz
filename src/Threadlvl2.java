
import java.io.Serializable;
import java.security.SecureRandom;
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
public class Threadlvl2 extends Thread implements Serializable {

    private static final long SerialVersionUID = 67;
    private transient JPanel gamepanel = new JPanel();
    private JLabel finalWave = new JLabel();
    private JLabel hugeWave = new JLabel();
    private ProgressBar p = new ProgressBar();
    private ArrayList<Zombie> zombies = new ArrayList();
    private ArrayList<Plant> plants = new ArrayList();
    private int cont = 0;

    public Threadlvl2() {
        super();
    }

    public Threadlvl2(JPanel gamepanel, JLabel finalWave, JLabel hugeWave, ProgressBar p) {
        super();
        this.gamepanel = gamepanel;
        this.finalWave = finalWave;
        this.hugeWave = hugeWave;
        this.p = p;
    }

    public JPanel getGamepanel() {
        return gamepanel;
    }

    public void setGamepanel(JPanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public JLabel getFinalWave() {
        return finalWave;
    }

    public void setFinalWave(JLabel finalWave) {
        this.finalWave = finalWave;
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
        //y base = 260 
        if (cont == 0) {
            SecureRandom random = new SecureRandom();
            int pos = (random.nextInt(3) + 1) * 100;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            pos = (random.nextInt(3) + 1) * 100;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            pos = (random.nextInt(3) + 1) * 100;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));

            pos = (random.nextInt(3) + 1) * 100;
            int pos1 = pos;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            while (pos1 == pos) {
                pos = (random.nextInt(3) + 1) * 100;
            }
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));

            pos = (random.nextInt(3) + 1) * 100;
            pos1 = pos;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            while (pos1 == pos) {
                pos = (random.nextInt(3) + 1) * 100;
            }
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));

            //final wave
            zombies.add(new FlagZombie(800, 110, gamepanel));

            zombies.add(new NormalZombie(800, 310, gamepanel));

            zombies.add(new NormalZombie(800, 310, gamepanel));

            zombies.add(new NormalZombie(800, 210, gamepanel));

            zombies.add(new NormalZombie(800, 210, gamepanel));
            cont++;
        }
        for (Zombie zomby : zombies) {
            zomby.setPlantas(plants);
            zomby.setZombies(zombies);
        }

        try {
            //62
            int wave = 0;
            p.setLimit(7);
            if(!p.isAlive()){
            p.start();
            Thread.sleep(15000);
            }
            zombies.get(0).start();

            while (wave != 7) {
                Thread.sleep(100);
                if (zombies.get(wave).getHp() <= 0) {
                    if (wave < 2) {
                        wave++;
                        zombies.get(wave).start();
                    } else if (wave < 6) {
                        wave++;
                        zombies.get(wave).start();

                        Thread.sleep(500);
                        wave++;
                        zombies.get(wave).start();

                    } else {
                        wave++;
                    }
                }

            }

            //final wave 
            Thread.sleep(5000);
            hugeWave.setVisible(true);

            Thread.sleep(5000);
            hugeWave.setVisible(false);
            finalWave.setVisible(true);

            Thread.sleep(2000);
            finalWave.setVisible(false);

            zombies.get(7).start();
            Thread.sleep(1000);
            zombies.get(8).start();
            Thread.sleep(1000);
            zombies.get(9).start();
            Thread.sleep(1000);
            zombies.get(10).start();
            Thread.sleep(1000);
            zombies.get(11).start();
            /**
             * Thread.sleep(15000); zombies.get(0).start(); Thread.sleep(14000);
             * zombies.get(1).start(); Thread.sleep(23000);
             * zombies.get(2).start(); Thread.sleep(11000);
             * zombies.get(3).start(); Thread.sleep(1000);
             * zombies.get(4).start(); Thread.sleep(13000);
             * zombies.get(5).start(); zombies.get(6).start();
             * Thread.sleep(17000); hugeWave.setVisible(true);
             * Thread.sleep(5000); hugeWave.setVisible(false);
             * finalWave.setVisible(true); Thread.sleep(2000);
             * finalWave.setVisible(false); zombies.get(7).start();
             * Thread.sleep(1000); zombies.get(8).start(); Thread.sleep(500);
             * zombies.get(9).start(); Thread.sleep(1000);
             * zombies.get(10).start(); Thread.sleep(500);
             * zombies.get(11).start();
             */
        } catch (InterruptedException ex) {
            Logger.getLogger(Threadlvl2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
