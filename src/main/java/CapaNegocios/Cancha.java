package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.JTable;

public class Cancha {
    private int IdCancha;
    private int IdDeporte;
    private String Descripcion;
    private String Ancho;
    private String Largo;
    private int Borrado;
    private Conexion oCon = new Conexion();
    
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
                return new ResponseObject("La cancha ya tiene un Id: ",-1);
        }
        return new ResponseObject("Cancha es null: ",-1);
    }
    
    public ResponseObject Listar ()throws SQLException
    {
        JTable oTabla = new JTable();
        
         try{
            getoCon().Conectar();
            getoCon().CrearComando("SELECT * FROM canchas WHERE borrado=0");
            //oTabla = oCon.Tabla();
            getoCon().Desconectar();
            return new ResponseObject("Eliminado correctamente",0,oTabla);
       }   
        catch(Exception e){
            getoCon().Desconectar();
            return new ResponseObject("Error: "+ e.toString(),-1, null);
        }
    }
    
    public ResponseObject Eliminar(int idCancha) throws SQLException
    {
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

        ResponseObject oRespuesta = oCanchita.Guardar(oCanchita);
        System.out.println(oRespuesta.getSalida());
        
    }

    /**
     * @return the IdCancha
     */
    public int getIdCancha() {
        return IdCancha;
    }

    /**
     * @param IdCancha the IdCancha to set
     */
    public void setIdCancha(int IdCancha) {
        this.IdCancha = IdCancha;
    }

    /**
     * @return the IdDeporte
     */
    public int getIdDeporte() {
        return IdDeporte;
    }

    /**
     * @param IdDeporte the IdDeporte to set
     */
    public void setIdDeporte(int IdDeporte) {
        this.IdDeporte = IdDeporte;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Ancho
     */
    public String getAncho() {
        return Ancho;
    }

    /**
     * @param Ancho the Ancho to set
     */
    public void setAncho(String Ancho) {
        this.Ancho = Ancho;
    }

    /**
     * @return the Largo
     */
    public String getLargo() {
        return Largo;
    }

    /**
     * @param Largo the Largo to set
     */
    public void setLargo(String Largo) {
        this.Largo = Largo;
    }

    /**
     * @return the Borrado
     */
    public int getBorrado() {
        return Borrado;
    }

    /**
     * @param Borrado the Borrado to set
     */
    public void setBorrado(int Borrado) {
        this.Borrado = Borrado;
    }

    /**
     * @return the oCon
     */
    public Conexion getoCon() {
        return oCon;
    }

    /**
     * @param oCon the oCon to set
     */
    public void setoCon(Conexion oCon) {
        this.oCon = oCon;
    }

}
