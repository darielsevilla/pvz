/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Usuario {
    private String nombre;
    private int n1 = 0;
    private int n2 = 0;
    private int n3 = 0;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario(String nombre, int n1, int n2, int n3) {
        this.nombre = nombre;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }
    
    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
