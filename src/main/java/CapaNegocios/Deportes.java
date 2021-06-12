/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Irene test
 */
public class Deportes {
    private int idDeportes;
    private String descripcion;
    private int borrado;  
    private Conexion oCon = new Conexion();
    
    public Deportes (int id, String descripcion){
        this.idDeportes = id;
        this.descripcion = descripcion;
    }
    public Deportes (){
        
    }
    public int getIdDeportes() {
        return idDeportes;
    }

    public void setDeportes(int idDeportes) {
        this.idDeportes = idDeportes;
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

     public ResponseObject Eliminar(int idDeporte) throws SQLException
    {
        try{
            oCon.Conectar();
            oCon.CrearComando("update deportes set borrado=1 where id = ?");
            oCon.comando.setInt(1, idDeporte);
            oCon.EjecutarComando();
            oCon.Desconectar();
            return new ResponseObject("Eliminado correctamente",0);
       }   
        catch(Exception e){
            oCon.Desconectar();
            return new ResponseObject("Error: "+ e.toString(),-1);
        }
    }
    
     public ResponseObject Guardar(Deportes oDeporte) throws SQLException {
        if(oDeporte!=null){
            int idNuevo=0;
            if(oDeporte.getIdDeportes()==0){
                try{
                    
                    oCon.Conectar();
                    oCon.CrearComando("INSERT INTO deportes (descripcion) VALUES (?)");
                    oCon.comando.setString(1, oDeporte.getDescripcion());
                    oCon.EjecutarComando();
                    oCon.Desconectar();
                    return new ResponseObject("Guardado correctamente",0);
                }   
                catch(Exception e) {
                    oCon.Desconectar();
                    return new ResponseObject("Error: "+ e.toString(),-1);
                }
            }
            else
            {
                try{
                    
                    oCon.Conectar();
                    oCon.CrearComando("UPDATE deportes SET descripcion = ? where id = ?");
                    oCon.comando.setString(1, oDeporte.getDescripcion());
                    oCon.comando.setInt(2, oDeporte.getIdDeportes());
                    oCon.EjecutarComando();
                    oCon.Desconectar();
                    return new ResponseObject("Editado correctamente",0);
                }   
                catch(Exception e) {
                    oCon.Desconectar();
                    return new ResponseObject("Error: "+ e.toString(),-1);
                }
            }
        }
        return new ResponseObject("Deporte es null: ",-1);
    }
    
    public ResponseObject Listar ()throws SQLException
    {
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();  
         try{
            oCon.Conectar();
            oCon.CrearComando("SELECT * FROM deportes WHERE borrado=0");
            dt = oCon.Tabla();
            oCon.Desconectar();
            return new ResponseObject("",0,dt);
       }   
        catch(Exception e){
            oCon.Desconectar();
            return new ResponseObject("Error: "+ e.toString(),-1, null);
        }
    }
    
    @Override
    public String toString(){
        return this.descripcion;
    }
}
