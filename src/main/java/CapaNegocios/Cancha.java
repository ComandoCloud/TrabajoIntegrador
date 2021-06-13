package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Cancha {
    private int idCancha;
    private int idDeporte;
    private String descripcion;
    private String ancho;
    private String largo;
    private int borrado;
    private Conexion oCon = new Conexion();
    
    public Cancha(int idCancha, String descripcion){
        this.idCancha = idCancha;
        this.descripcion = descripcion;
        
    }
    public Cancha(){
               
    }
    public ResponseObject Guardar(Cancha oCancha) throws SQLException {
        if(oCancha!=null){
            int idNuevo=0;
            if(oCancha.getIdCancha()==0){
                try{
                    getoCon().Conectar();
                    getoCon().CrearComando("INSERT INTO canchas (id_deporte,descripcion,ancho,largo) VALUES (?,?,?,?)");
                    
                    getoCon().comando.setInt(1, oCancha.getIdDeporte());
                    getoCon().comando.setString(2, oCancha.getDescripcion());
                    getoCon().comando.setString(3, oCancha.getAncho());
                    getoCon().comando.setString(4, oCancha.getLargo());
                    getoCon().EjecutarComando();
                    getoCon().Desconectar();
                    return new ResponseObject("Guardado correctamente",0);
                }   
                catch(Exception e) {
                    getoCon().Desconectar();
                    return new ResponseObject("Error: "+ e.toString(),-1);
                }
            }
            else
            {
                try{
                    getoCon().Conectar();
                    getoCon().CrearComando("UPDATE canchas SET id_deporte = ?,descripcion = ?, ancho = ?,largo=? where id = ?");
                    
                    getoCon().comando.setInt(1, oCancha.getIdDeporte());
                    getoCon().comando.setString(2, oCancha.getDescripcion());
                    getoCon().comando.setString(3, oCancha.getAncho());
                    getoCon().comando.setString(4, oCancha.getLargo());
                    getoCon().comando.setInt(5, oCancha.getIdCancha());

                    getoCon().EjecutarComando();
                    getoCon().Desconectar();
                    return new ResponseObject("Editado correctamente",0);
                }   
                catch(Exception e) {
                    getoCon().Desconectar();
                    return new ResponseObject("Error: "+ e.toString(),-1);
                }
            }
        }
        return new ResponseObject("Cancha es null: ",-1);
    }
    
    public ResponseObject Listar ()throws SQLException{
        DefaultTableModel dt = new DefaultTableModel();
        
         try{
            Conexion oCon = new Conexion();  
            oCon.Conectar();
            oCon.CrearComando("SELECT canchas.*, deportes.descripcion as deporte FROM canchas left join deportes on deportes.id = canchas.id_deporte WHERE canchas.borrado=0");
            dt = oCon.Tabla();
            oCon.Desconectar();
            return new ResponseObject("Eliminado correctamente",0,dt);
       }   
        catch(Exception e){
            oCon.Desconectar();
            return new ResponseObject("Error: "+ e.toString(),-1, null);
        }
    }
    
    public ResponseObject Eliminar(int idCancha) throws SQLException{
        try{
            getoCon().Conectar();
            getoCon().CrearComando("update canchas set borrado=1 where id = ?");
            getoCon().comando.setInt(1, idCancha);
            getoCon().EjecutarComando();
            getoCon().Desconectar();
            return new ResponseObject("Eliminado correctamente",0);
       }   
        catch(Exception e){
            getoCon().Desconectar();
            return new ResponseObject("Error: "+ e.toString(),-1);
        }
    }

    public static void main(String[] args) throws SQLException,InterruptedException {
        System.out.println("com.comandocloud.tpintegrador.Cancha.main()");
        Cancha oCanchita = new Cancha();

        ResponseObject oRespuesta = oCanchita.Listar();
        System.out.println(oRespuesta.getSalida());
    }

   

    public Conexion getoCon() {
        return oCon;
    }

    public void setoCon(Conexion oCon) {
        this.oCon = oCon;
    }

    /**
     * @return the idCancha
     */
    public int getIdCancha() {
        return idCancha;
    }

    /**
     * @param idCancha the idCancha to set
     */
    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    /**
     * @return the idDeporte
     */
    public int getIdDeporte() {
        return idDeporte;
    }

    /**
     * @param idDeporte the idDeporte to set
     */
    public void setIdDeporte(int idDeporte) {
        this.idDeporte = idDeporte;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the ancho
     */
    public String getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the largo
     */
    public String getLargo() {
        return largo;
    }

    /**
     * @param largo the largo to set
     */
    public void setLargo(String largo) {
        this.largo = largo;
    }

    /**
     * @return the borrado
     */
    public int getBorrado() {
        return borrado;
    }

    /**
     * @param borrado the borrado to set
     */
    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString(){
        return this.descripcion;
    }
}
