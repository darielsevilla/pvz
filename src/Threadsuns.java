
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
public class Threadsuns extends Thread implements Serializable{
    private transient JPanel panel;
    private JLabel cont;
    private ArrayList<Sun> soles;
    private static final long SerialVersionUID = 69;
    
    public Threadsuns(JPanel panel, JLabel cont, ArrayList<Sun> soles){
        this.panel = panel;
        this.cont = cont;
        this.soles = soles;
     
    }

    public JLabel getCont() {
        return cont;
    }

    public void setCont(JLabel cont) {
        this.cont = cont;
    }

    public ArrayList<Sun> getSoles() {
        return soles;
    }

    public void setSoles(ArrayList<Sun> soles) {
        this.soles = soles;
    }
    
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    public void run(){
       
        while(true){
            try {
                Thread.sleep(10000);
                Sun sun = new Sun(panel, cont);
                
                sun.setSoles(soles);
               
                sun.start();
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Threadsuns.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
    }
}
