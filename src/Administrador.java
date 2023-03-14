
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Administrador {
    private File pausa;
    private ArrayList<Plant> plantas = new ArrayList();
    private ArrayList<Zombie> zombies = new ArrayList();
    private ArrayList<Thread> others = new ArrayList();
    private ArrayList<ClickeablePlant> casillas = new ArrayList();
    private int op = 0;
    private String value = "";
    private GameOver over;

    public Administrador() {
    }

    public Administrador(String path) {
        this.pausa = new File(path);
    }

    public ArrayList<ClickeablePlant> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<ClickeablePlant> casillas) {
        this.casillas = casillas;
    }

    
    public File getPausa() {
        return pausa;
    }

    public void setPausa(File pausa) {
        this.pausa = pausa;
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

    public ArrayList<Thread> getOthers() {
        return others;
    }

    public void setOthers(ArrayList<Thread> others) {
        this.others = others;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GameOver getOver() {
        return over;
    }

    public void setOver(GameOver over) {
        this.over = over;
    }
    
    
    public void save(){
        try {
            
            FileOutputStream fo = new FileOutputStream(pausa, false);
            try {
                ObjectOutputStream output = new ObjectOutputStream(fo);
                output.writeObject(op);
                output.writeObject(value);
                for (Plant planta : plantas) {
                    output.writeObject(planta);     
                }
                
                for (Zombie zombie : zombies) {        
                    output.writeObject(zombie);
                }
                
               for (Thread other : others) {
                    output.writeObject(other);
                }
                
               for (ClickeablePlant p : casillas) {
                    output.writeObject(p);
                }
                output.writeObject(over);
                output.flush();
                output.close();
                fo.close();
            } catch (IOException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load(){
        if(pausa.exists()){
            try {
                FileInputStream in = new FileInputStream(pausa);
                
                try{
                  ObjectInputStream input = new ObjectInputStream(in);
                  Object temp;
                  while((temp = input.readObject()) != null){
                      if(temp instanceof Integer){
                          op = (int)temp;
                      }else if(temp instanceof Plant){
                          plantas.add((Plant) temp);
                      }else if(temp instanceof GameOver){
                          over = (GameOver)temp;
                      }else if(temp instanceof Zombie){
                          zombies.add((Zombie) temp);
                      }else if(temp instanceof Thread){
                          others.add((Thread) temp);
                      }else if(temp instanceof String){
                          value = (String)temp;
                      }else if(temp instanceof ClickeablePlant){
                          casillas.add((ClickeablePlant) temp);
                      }
                      
                  }
                  
                  in.close();
                  input.close();
                }catch(EOFException ex){
                    
                }catch(Exception e){
                    
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void reset(){
        try {
            FileOutputStream out = new FileOutputStream(pausa, false);
            ObjectOutputStream output = new ObjectOutputStream(out);
            output.writeObject(0);
            output.flush();
            output.close();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
