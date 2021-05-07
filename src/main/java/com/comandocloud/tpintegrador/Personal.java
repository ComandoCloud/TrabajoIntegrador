package com.comandocloud.tpintegrador;
import com.comandocloud.tpintegrador.Persona;

public class Personal extends Persona{
    String Usuario;
    String Password;
    int id_personal_cargo;
    public Personal(){

    }
    public Personal(String User, String Password, int id_personal_cargo){
        super();
        this.Usuario = User;
        this.Password = Password;
        this.id_personal_cargo = id_personal_cargo;
    }

    public static void main(String[] args) {
    }
}
