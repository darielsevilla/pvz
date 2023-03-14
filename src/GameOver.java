
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */
public class GameOver extends Thread implements Serializable{

    private ArrayList<Zombie> zombies = new ArrayList();
    private ArrayList<Plant> plants = new ArrayList();
    private ArrayList<Thread> threads = new ArrayList();
    private ArrayList<Sun> suns = new ArrayList();
    private transient JPanel win, loose, lvl1;
    private int resultado = 0;
    private static final long SerialVersionUID = 227L;

    public GameOver() {
        super();
    }

    public GameOver(JPanel win, JPanel loose, JPanel lvl1) {
        this.win = win;
        this.loose = loose;
        this.lvl1 = lvl1;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public ArrayList<Sun> getSuns() {
        return suns;
    }

    public void setSuns(ArrayList<Sun> suns) {
        this.suns = suns;
    }

    public JPanel getLvl1() {
        return lvl1;
    }

    public void setLvl1(JPanel lvl1) {
        this.lvl1 = lvl1;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }

    public JPanel getWin() {
        return win;
    }

    public void setWin(JPanel win) {
        this.win = win;
    }

    public JPanel getLoose() {
        return loose;
    }

    public void setLoose(JPanel loose) {
        this.loose = loose;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public void run() {
        
        boolean isAlive = true;

        while (isAlive) {
            if (zombies.size() > 0) {
                Zombie z1 = zombies.get(0);

                for (Zombie z : zombies) {
                    if (z.getHitbox().getX()  <= 0) {
                        isAlive = false;
                        resultado = 1;
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }

                int cont = 0;
                
                    for (Zombie z : zombies) {
                        if(z.getHp() <= 0){
                            cont++;
                        }
                       /** if (z.getHitbox().getX() == 12 && z.getHitbox().getY() == -1000) {
                            cont++;
                        }*/
                    }
               
                if (cont == zombies.size()) {
                    isAlive = false;
                    resultado = 2;
                    break;
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Thread t : threads) {
            t.stop();
        }
        for (Zombie z : zombies) {
            if (lvl1.getComponentZOrder(z.getZombieLabel()) != -1) {
                lvl1.remove(z.getZombieLabel());
            }
            z.stop();
        }
        for (Plant p : plants) {
            if (lvl1.getComponentZOrder(p.getPlantLabel()) != -1) {
                lvl1.remove(p.getPlantLabel());
            }
            p.stop();
        }
        for (Sun s : suns) {
            if (lvl1.getComponentZOrder(s.getSunIcon()) != -1) {
                lvl1.remove(s.getSunIcon());
            }
        }
        plants.clear();
        zombies.clear();
        suns.clear();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        }

        lvl1.setEnabled(false);
        if (resultado == 2) {
            lvl1.setVisible(false);
            win.setVisible(true);

        } else {
         
            
            loose.setVisible(true);

        }
        
    }

    @Override
    public String toString() {
        return "GameOver{" + "zombies=" + zombies + '}';
    }

}
