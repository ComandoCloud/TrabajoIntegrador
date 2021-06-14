package CapaNegocios;

import CapaDatos.Conexion;

public class Persona {

    //REGION DE PROPIEDADES
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String telefono;
    private int borrado;
    private int tipoPersona;
    private Conexion oCon = new Conexion();

    //CONSTRUCTORES
    public Persona() {

    }

    public Persona(String nombre, String apellido, String dni, String mail, String telefono, int tipoPersona, int borrado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = mail;
        this.telefono = telefono;
        this.borrado = borrado;
        this.tipoPersona = tipoPersona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public int getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Conexion getoCon() {
        return oCon;
    }

    public void setoCon(Conexion oCon) {
        this.oCon = oCon;
    }
}
