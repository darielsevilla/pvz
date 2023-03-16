
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
public class AdministradorUsuario {

    private File usersFile;
    private ArrayList<Usuario> usersList = new ArrayList();

    public AdministradorUsuario() {
    }

    public AdministradorUsuario(String path) {
        usersFile = new File(path);
    }

    public File getUsersFile() {
        return usersFile;
    }

    public void setUsersFile(File usersFile) {
        this.usersFile = usersFile;
    }

    public ArrayList<Usuario> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<Usuario> usersList) {
        this.usersList = usersList;
    }

    
    public void read() {
        usersList.clear();
        if (usersFile.exists()) {
            try {

                Scanner lea = new Scanner(usersFile);

                while (lea.hasNextLine()) {
                    String temporal = lea.nextLine();
                    String[] array = temporal.split(";");
                    
                    Usuario temp = new Usuario(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]));
                    usersList.add(temp);

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdministradorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void write() {
        try {
            FileWriter writer = new FileWriter(usersFile, false);
            BufferedWriter bw = new BufferedWriter(writer);
            for (Usuario usuario : usersList) {
                bw.write(usuario.getNombre() + ";" + usuario.getN1() + ";" + usuario.getN2() + ";" + usuario.getN3() + "\n");
            
            }
            bw.flush();
            bw.close();
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(AdministradorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String toString() {
        return "Administrador de " + usersFile;
    }
    
    
}
