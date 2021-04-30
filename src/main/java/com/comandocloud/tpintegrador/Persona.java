package com.comandocloud.tpintegrador;

public class Persona {
    int id;
    String nombre;
    String apellido;
    String Dni;
    String Email;
    String Telefono;
    int borrado;
    public Persona(){

    }
    public Persona(String Nombre, String Apellido, String Dni, String Mail, String Telefono){
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.Dni = Dni;
        this.Email = Mail;
        this.Telefono = Telefono;
        this.borrado =0;
    }

    public static void main(String[] args) {

    }

}
