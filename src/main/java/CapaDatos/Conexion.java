/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author ALeeh
 */
public class Conexion {
    Connection oCon;
    public static final String Url= "jdbc:mysql://179.51.237.45/db_canchas";
    public static final String Usuario= "root";
    public static final String Clave = "root2020";
    public PreparedStatement ps;
    ResultSet rs;
    public Conexion(){
        
    }
    
    //SECCION DE CONEXION
    public Connection getConection(){
                try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            oCon = DriverManager.getConnection(this.Url, this.Usuario, this.Clave);
             //oCon = DriverManager.getConnection("jdbc:mysql://localhost/db_java", "root", "15648836");
            System.out.println("Conexion exitosa.");
        } catch (Exception e) 
        {
            System.out.println("Conexion fallida.");
            System.out.println(e.getMessage().toString());
        }
        return oCon;
    }
    
    public Connection Conectar(){
        Connection con = null;
        try 
        {
            con = getConection();
        } catch (Exception e) 
        {
            System.out.println(e.getMessage().toString());
        }
        return con;
    }
    //FIN SECCION DE CONEXION
    
    public static void main(String[] args) throws SQLException {
        Conexion oCon = new Conexion();

        Connection con = null;
                con = oCon.Conectar();
        oCon.ps = con.prepareStatement("SELECT * FROM personal");
        oCon.rs = oCon.ps.executeQuery();
        if(oCon.rs.next()){
            JOptionPane.showMessageDialog(null, oCon.rs.getString("Nombre") + oCon.rs.getString("Apellido"));
        }
        else{
            JOptionPane.showMessageDialog(null,"No se encontraron archivos");
        }
        
       con.close();
    }
}
