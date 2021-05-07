/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comandocloud.tpintegrador;

/**
 *
 * @author Irene
 */
public class Canchas {
    private int id_canchas;
    private int id_deportes;
    private String descripcion;
    private String ancho;
    private String largo;
    private int borrado;

    public int getId_canchas() {
        return id_canchas;
    }

    public void setId_canchas(int id_canchas) {
        this.id_canchas = id_canchas;
    }

    public int getId_deportes() {
        return id_deportes;
    }

    public void setId_deportes(int id_deportes) {
        this.id_deportes = id_deportes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }
    
    
}
