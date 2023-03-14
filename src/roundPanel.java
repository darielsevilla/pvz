
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class roundPanel extends JPanel {
    private Color color;
    public roundPanel(Color c) {
        color = c;
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10,10);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}

