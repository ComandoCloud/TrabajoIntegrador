/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

/**
 *
 * @author Irene test
 */
public class Deportes {
    private int deportes;
    
    private String descripcion;
    private int borrado;  

    public int getDeportes() {
        return deportes;
    }

    public void setDeportes(int deportes) {
        this.deportes = deportes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public ResponseObject Eliminar(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}