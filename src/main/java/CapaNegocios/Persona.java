package CapaNegocios;
import CapaDatos.Conexion;
import java.sql.SQLException;

public class Persona {
    private int Id;
    private String Nombre;
    private String Apellido;
    private String Dni;
    private String Email;
    private String Telefono;
    private int Borrado;
    private Conexion oCon = new Conexion();

    public Persona(){

    }
    public Persona(String Nombre, String Apellido, String Dni, String Mail, String Telefono){
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Dni = Dni;
        this.Email = Mail;
        this.Telefono = Telefono;
        this.Borrado =0;
    }
    
  
    public static void main(String[] args) throws SQLException,InterruptedException {

    }

    public int GetId() {
        return Id;
    }

    public void SetId(int Id) {
        this.Id = Id;
    }

    public String GetNombre() {
        return Nombre;
    }

    public void SetNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String GetApellido() {
        return Apellido;
    }

    public void SetApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String GetDni() {
        return Dni;
    }

    public void SetDni(String Dni) {
        this.Dni = Dni;
    }

    public String GetEmail() {
        return Email;
    }

    public void SetEmail(String Email) {
        this.Email = Email;
    }

    public String GetTelefono() {
        return Telefono;
    }

    public void SetTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int GetBorrado() {
        return Borrado;
    }

    public void SetBorrado(int Borrado) {
        this.Borrado = Borrado;
    }

    public Conexion GetoCon() {
        return oCon;
    }



}