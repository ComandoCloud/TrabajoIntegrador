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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ALeeh
 */
public class Conexion {
    Connection oCon = null;
    public static final String Url= "jdbc:mysql://179.51.237.45/db_canchas";
    public static final String Usuario= "root";
    public static final String Clave = "root2020";
    public PreparedStatement comando;
    public ResultSet comandoResult;
    public Conexion(){
        
    }
    
//SECCION DE CONEXION
    
    public void Conectar() throws SQLException{
        if(this.oCon != null && this.oCon.isValid(1))
        {
            throw new SQLException("Conexion abierta");          
        }
        try 
        {
            if(this.oCon == null)
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.oCon = DriverManager.getConnection(this.Url, this.Usuario, this.Clave);
                System.out.println("Conexion exitosa.");
            }
        } catch (Exception e) 
        {
            throw new SQLException("Error al conectarse con la base de datos");
        }
    }
    
    public void Desconectar() throws SQLException{
            if(this.oCon.isValid(3000))
            {
                this.oCon = null;
                this.comando=null;
                this.comandoResult=null;
            }
    }
    public void CrearComando(String Comando) throws SQLException{
        this.comando = oCon.prepareStatement(Comando.toString().toLowerCase());
    }
    
    public void EjecutarComando() throws SQLException{
        this.comandoResult = this.comando.executeQuery();
        //while(this.comandoResult.next()){
          //JOptionPane.showMessageDialog(null, this.comandoResult.getString("Nombre") + this.comandoResult.getString("Apellido"));
        //}
    }
    
    public JTable Tabla() throws SQLException{
        ResultSetMetaData metaDatos = this.comandoResult.getMetaData();
        DefaultTableModel dt = new DefaultTableModel();
        JTable tabla = new JTable();
        tabla.setModel(dt);
        int NumColumn = metaDatos.getColumnCount();
        Object[] nombreColumnas = new Object[NumColumn];
        Object [] fila = new Object[NumColumn];
        for(int i=0; i<NumColumn; i++)
        {
            nombreColumnas[i] = metaDatos.getColumnLabel(i + 1);
        }
        dt.setColumnIdentifiers(nombreColumnas);
        while(this.comandoResult.next())
        {
            for (int i=0;i<NumColumn;i++)
            {
                fila[i] = this.comandoResult.getObject(i+1);
            }    
            dt.addRow(fila);
        }
        

        return tabla;
    }
    
    
    //FIN SECCION DE CONEXION
 
    public static void main(String[] args) throws SQLException {
            
    }
}
