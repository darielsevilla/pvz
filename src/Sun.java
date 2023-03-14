
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
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
public class Sun extends Thread implements Serializable{
    private static final long SerialVersionUID = 432L;
    private ImageIcon icono;
    private SecureRandom caida = new SecureRandom();
    private JLabel sunIcon = new JLabel();
    private transient JPanel panel = new JPanel();
    private Rectangle hitbox = new Rectangle();
    private int x = 0;
    private int y = 0;
    private ArrayList<Sun> soles = new ArrayList();
    private int op1 = 0;

    private int n = 0;

    public Sun() {

    }

    //constructor para sunflowers
    public Sun(JPanel panel, JLabel cont, int x, int y) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        icono = new javax.swing.ImageIcon(getClass().getResource("/imagenes/sol.png"));
        op1 = 1;
        hitbox.setBounds(x, y, 40, 40);
        sunIcon.setBounds(hitbox);
        sunIcon.setIcon(icono);

        this.panel.add(sunIcon, new AbsoluteConstraints((int) hitbox.getX(), (int) hitbox.getY(), 40, 40), 0);
        soles.add(this);

        sunIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cont.setText(Integer.toString(Integer.parseInt(cont.getText()) + 25));
                        n++;

                        sunIcon.setVisible(false);
                        panel.remove(sunIcon);
                      
                        //panel.repaint();
                        soles.remove(this);
                    }

                });
                /**
                 * cont.setText(Integer.toString(Integer.parseInt(cont.getText())
                 * + 25)); n++;
                 *
                 * sunIcon.setVisible(false); panel.remove(sunIcon);
                 * panel.revalidate(); //panel.repaint(); soles.remove(this);
                 */

            }

        });
    }

    //constructor para thread soles
    public Sun(JPanel panel, JLabel cont) {
        this.panel = panel;
        icono = new javax.swing.ImageIcon(getClass().getResource("/imagenes/sol.png"));

        x = caida.nextInt(621) + 40;
        y = 106;
        hitbox.setBounds(x, y, 40, 40);
        sunIcon.setBounds(hitbox);
        sunIcon.setIcon(icono);

        this.panel.add(sunIcon, new AbsoluteConstraints((int) hitbox.getX(), (int) hitbox.getY(), 40, 40), 0);
        sunIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cont.setText(Integer.toString(Integer.parseInt(cont.getText()) + 25));
                        n++;

                        sunIcon.setVisible(false);
                        panel.remove(sunIcon);
                        panel.revalidate();
                        //panel.repaint();
                        soles.remove(this);
                    }

                });
            }

        });

    }

    public void run() {

        int finalPos = caida.nextInt(405) + 106;
        if (op1 == 0) {
            finalPos = caida.nextInt(405) + 106;
        } else {
            finalPos = (int) hitbox.getY() + 90;
        }

        while (hitbox.getY() <= finalPos && n == 0) {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    sunIcon.setLocation((int) hitbox.getX(), (int) hitbox.getY() + 5);
                    hitbox.setLocation((int) hitbox.getX(), (int) hitbox.getY() + 5);
                    panel.add(sunIcon, new AbsoluteConstraints(x, y, 40, 40), 1);;
                }

            });

            x = (int) hitbox.getX();
            y = (int) hitbox.getY();

            try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pea.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                panel.add(sunIcon, new AbsoluteConstraints(x, y, 40, 40), 1);
                Timer t = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sunIcon.setVisible(false);
                        panel.remove(sunIcon);

                        soles.remove(this);
                    }

                });
                t.start();
            }

        });

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public ArrayList<Sun> getSoles() {
        return soles;
    }

    public void setSoles(ArrayList<Sun> soles) {
        this.soles = soles;
        soles.add(this);
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public SecureRandom getCaida() {
        return caida;
    }

    public void setCaida(SecureRandom caida) {
        this.caida = caida;
    }

    public JLabel getSunIcon() {
        return sunIcon;
    }

    public void setSunIcon(JLabel sunIcon) {
        this.sunIcon = sunIcon;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public String toString() {
        return "Sun{" + "icono=" + icono + ", caida=" + caida + ", sunIcon=" + sunIcon + ", panel=" + panel + '}';
    }

}
