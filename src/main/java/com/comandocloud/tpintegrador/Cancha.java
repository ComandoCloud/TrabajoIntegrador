package com.comandocloud.tpintegrador;

import CapaDatos.Conexion;
import java.sql.SQLException;

public class Cancha {
    private int id_cancha;
    private int id_deporte;
    private String descripcion;
    private String ancho;
    private String largo;
    private int borrado;
    private Conexion oCon = new Conexion();
    
    public ResponseObject Guardar(Cancha oCancha) throws SQLException {
        if(oCancha!=null){
            int idNuevo=0;
            if(oCancha.getId_cancha()==0){
                try{
                    
                    oCon.Conectar();
                    oCon.CrearComando("INSERT INTO canchas (id_deporte,descripcion,ancho,largo) VALUES (?,?,?,?)");
                    
                    oCon.comando.setInt(1, oCancha.getId_deporte());
                    oCon.comando.setString(2, oCancha.getDescripcion());
                    oCon.comando.setString(3, oCancha.getAncho());
                    oCon.comando.setString(4, oCancha.getLargo());
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
                return new ResponseObject("La cancha ya tiene un Id: ",-1);
        }
        return new ResponseObject("Cancha es null: ",-1);
    }
    
    public ResponseObject Eliminar(int idCancha) throws SQLException
    {
        try{
            oCon.Conectar();
            oCon.CrearComando("update canchas set borrado=1 where id = ?");
            oCon.comando.setInt(1, idCancha);
            oCon.EjecutarComando();
            oCon.Desconectar();
            return new ResponseObject("Eliminado correctamente",0);
       }   
        catch(Exception e){
            oCon.Desconectar();
            return new ResponseObject("Error: "+ e.toString(),-1);
        }
    }
    
    public int getId_cancha() {
        return id_cancha;
    }

    public void setId_cancha(int id_canchas) {
        this.id_cancha = id_canchas;
    }

    public int getId_deporte() {
        return id_deporte;
    }

    public void setId_deporte(int id_deportes) {
        this.id_deporte = id_deportes;
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
    public static void main(String[] args) throws SQLException,InterruptedException {
        System.out.println("com.comandocloud.tpintegrador.Cancha.main()");
        Cancha oCanchita = new Cancha();
        oCanchita.setDescripcion("Fultbol 5");
        oCanchita.setId_deporte(2);
        oCanchita.setId_cancha(0);
        oCanchita.setAncho("2");
        oCanchita.setLargo("2");
        ResponseObject oRespuesta = oCanchita.Guardar(oCanchita);
        System.out.println(oRespuesta.getSalida());
        
    }

}
