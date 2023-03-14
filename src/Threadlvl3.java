
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
public class Threadlvl3 extends Thread implements Serializable {

    private static final long SerialVersionUID = 68;
    private ArrayList<Plant> plantas = new ArrayList();
    private ArrayList<Zombie> zombies = new ArrayList();
    private transient JPanel gamepanel = new JPanel();
    private JLabel finalWave = new JLabel();
    private JLabel hugeWave = new JLabel();
    private ProgressBar p = new ProgressBar();
    private int[] waves = new int[5];
    private int contador = 0;

    public Threadlvl3() {
        super();
    }

    public Threadlvl3(JPanel gamepanel, JLabel finalWave, JLabel hugeWave, ProgressBar p) {
        super();
        this.gamepanel = gamepanel;
        this.finalWave = finalWave;
        this.hugeWave = hugeWave;
        this.p = p;
    }

    public ArrayList<Plant> getPlantas() {
        return plantas;
    }

    public void setPlantas(ArrayList<Plant> plantas) {
        this.plantas = plantas;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
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

    public JLabel getHugeWave() {
        return hugeWave;
    }

    public void setHugeWave(JLabel hugeWave) {
        this.hugeWave = hugeWave;
    }

    @Override
    public void run() {
        if (contador == 0) {
            SecureRandom random = new SecureRandom();
            //crear los zombies
            int pos = (random.nextInt(3) + 1) * 100;

            zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            pos = (random.nextInt(3) + 1) * 100;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            pos = (random.nextInt(3) + 1) * 100;
            zombies.add(new NormalZombie(800, pos + 10, gamepanel));

            waves[0] = zombies.size();
            int op = random.nextInt(2);
            if (op == 1) {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            } else {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new coneHead(800, pos + 10, gamepanel));
            }
            waves[1] = zombies.size();
            op = random.nextInt(2);
            if (op == 1) {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            } else {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new coneHead(800, pos + 10, gamepanel));
            }
            waves[2] = zombies.size();
            op = random.nextInt(2);
            if (op == 1) {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            } else {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new coneHead(800, pos + 10, gamepanel));
            }

            waves[3] = zombies.size();
            op = random.nextInt(2);
            if (op == 1) {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new NormalZombie(800, pos + 10, gamepanel));
            } else {
                pos = (random.nextInt(3) + 1) * 100;
                zombies.add(new coneHead(800, pos + 10, gamepanel));
            }

            zombies.add(new NormalZombie(800, 110, gamepanel));
            waves[4] = zombies.size();
            //final wave zombies
            zombies.add(new FlagZombie(800, 310, gamepanel));
            zombies.add(new NormalZombie(800, 210, gamepanel));
            zombies.add(new coneHead(800, 110, gamepanel));
            zombies.add(new NormalZombie(800, 210, gamepanel));

            zombies.add(new NormalZombie(800, 110, gamepanel));
            zombies.add(new NormalZombie(800, 210, gamepanel));

            for (Zombie z : zombies) {
                z.setPlantas(plantas);
                z.setZombies(zombies);
            }
            contador++;
        }

        //inciar nivel
        try {

            p.setLimit(waves[4]);
            if (!p.isAlive()) {
                p.start();
                Thread.sleep(15000);
            }
            int puntos = 0;
            int zActual = 0;
            
            zombies.get(0).start();

            int c = 0;
            int cont = 0;

            while (zActual < waves[4]) {
                Thread.sleep(100);
                if (zombies.get(zActual).getHp() <= 0) {
                    if (zActual < waves[0] - 1) {
                        zActual++;

                        zombies.get(zActual).start();
                    } else if (zActual < waves[1] - 1) {
                        for (int i = 0; i < waves[1] - waves[0]; i++) {

                            zActual++;

                            zombies.get(zActual).start();
                        }
                    } else if (zActual < waves[2] - 1) {
                        for (int i = 0; i < waves[2] - waves[1]; i++) {
                            zActual++;

                            zombies.get(zActual).start();
                        }
                    } else if (zActual < waves[3] - 1) {
                        for (int i = 0; i < waves[3] - waves[2]; i++) {
                            zActual++;

                            zombies.get(zActual).start();
                        }
                    } else if (zActual < waves[4]) {
                        for (int i = 0; i < (waves[4] - waves[3]) - 1; i++) {
                            zActual++;

                            zombies.get(zActual).start();
                        }

                    }

                }

            }

            hugeWave.setVisible(true);
            Thread.sleep(7000);
            hugeWave.setVisible(false);
            finalWave.setVisible(true);
            Thread.sleep(2000);
            finalWave.setVisible(false);
            zActual++;

            zombies.get(zActual).start();

            zActual++;

            zombies.get(zActual).start();
            zActual++;

            Thread.sleep(1500);
            zombies.get(zActual).start();
            zActual++;

            zombies.get(zActual).start();
            zActual++;

            Thread.sleep(2000);
            zombies.get(zActual).start();

        } catch (InterruptedException ex) {
            Logger.getLogger(Threadlvl3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
