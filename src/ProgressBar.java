
import java.awt.ComponentOrientation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
public class ProgressBar extends Thread implements Serializable {

    private static final long SerialVersionUID = 348L;
    private ArrayList<Zombie> zombies = new ArrayList();
    private JProgressBar pb;
    private JLabel label;
    private transient JPanel barPanel;
    private int limit;

    public ProgressBar() {
        super();
    }

    public ProgressBar(JProgressBar pb, JLabel label, JPanel barPanel) {
        this.pb = pb;
        this.label = label;
        this.barPanel = barPanel;

        pb.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    public JProgressBar getPb() {
        return pb;
    }

    public void setPb(JProgressBar pb) {
        this.pb = pb;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JPanel getBarPanel() {
        return barPanel;
    }

    public void setBarPanel(JPanel barPanel) {
        this.barPanel = barPanel;
    }

    public void run() {
        try {

            Thread.sleep(15000);
            
            final int movimiento = pb.getWidth() / limit;
            final int inicialPos = label.getX();
            pb.setMaximum(limit);
            
            while (pb.getValue() < pb.getMaximum()) {
                int value = 0;
                for (Zombie z : zombies) {
                    if (z.getHp() <= 0) {
                        value++;

                    }
                }
                
                final int value2 = value;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        label.setLocation(inicialPos - (movimiento * value2), label.getY());
                        barPanel.add(label, new AbsoluteConstraints(inicialPos - (movimiento * value2), label.getY(), label.getWidth(), label.getHeight()), 0);
                        if (value2 == pb.getMaximum()) {
                            label.setLocation(inicialPos - (movimiento * value2), label.getY());
                            barPanel.add(label, new AbsoluteConstraints(pb.getX(), label.getY(), label.getWidth(), label.getHeight()), 0);
                        }
                        barPanel.repaint();

                    }

                });
                Thread.sleep(100);
                pb.setValue(value2);
                Thread.sleep(100);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
