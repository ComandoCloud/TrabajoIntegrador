package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import jdk.jfr.Timespan;

public class Reserva {
    private int id;
    private int idCancha;
    private int idUsuario;
    private int idPersonal;
    private int idReservaEstado;
    private Timestamp fechaHora;
    private int duracion;
    private Conexion oCon = new Conexion();

    public ReservasEstados verDisponiblidad(Timestamp fechaHora,int idCancha) throws SQLException{
        
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();          
        oCon.Conectar();
        oCon.CrearComando("SELECT reservas.id,reservas.id_cancha, reservas.id_usuario, reservas.id_reserva_estado,reservas.fecha_hora,reservas.duracion,reservas.borrado,reservas_estados.descripcion as estado "
                + " FROM reservas " 
                + " LEFT JOIN reservas_estados on reservas_estados.id = reservas.id_reserva_estado "
                + " where reservas.fecha_hora= ? and reservas.id_cancha = ? and reservas.borrado=0 ");
        oCon.comando.setTimestamp(1, fechaHora);
        oCon.comando.setInt(2, idCancha);
        oCon.EjecutarComando();
        dt = oCon.Tabla();
        oCon.Desconectar();
        if(dt.getRowCount()>0){
            return new ReservasEstados((Integer)dt.getValueAt(0,3),(String)dt.getValueAt(0, 7));
        }
        return null;
    }
    
    public ResponseObject getHorarios(int idCancha, String fecha) throws SQLException, ParseException{
        
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("hora");
        dt.addColumn("estado");
        for (int i = 8; i < 24; i++) {
            if(i<10)
               dt.addRow(new Object[]{"0" + i +":00", "libre"});
            else
               dt.addRow(new Object[]{i +":00", "libre"});
        }
        String horaAux = "";
        for (int row = 0; row < dt.getRowCount(); row++) {
            for (int col = 0; col < dt.getColumnCount(); col++) {
                if (col == 0) {
                    horaAux = dt.getValueAt(row, col).toString();
                    String fechaCompleta = fecha + " " + horaAux;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    Date parsedDate = dateFormat.parse(fechaCompleta);
                    Timestamp fechaTime = new java.sql.Timestamp(parsedDate.getTime());
                    ReservasEstados oEstado = verDisponiblidad(fechaTime, idCancha);
                    if(oEstado==null)
                        dt.setValueAt("LIBRE", row, col+1);
                    else
                        dt.setValueAt(oEstado.getDescripcion().toUpperCase(), row, col+1);
                }
            }
        }
        return new ResponseObject("",0,dt);
    }

    public ResponseObject Reservar(int idCancha, Timestamp fecha, int idUsuario, int estado) throws SQLException{
        try{
            oCon.Conectar();
            oCon.CrearComando("INSERT INTO reservas (id_cancha,id_usuario,id_reserva_estado,fecha_hora,duracion) VALUES (?,?,?,?,?)");
            oCon.comando.setInt(1, idCancha);
            oCon.comando.setInt(2, idUsuario);
            oCon.comando.setInt(3, estado);
            oCon.comando.setTimestamp(4, fecha);
            oCon.comando.setInt(5, 1);
            oCon.EjecutarComando();
            oCon.Desconectar();
            return new ResponseObject("Reserva ingresada correctamente",0,null);
        }
        catch(SQLException c){
            oCon.Desconectar();
            return new ResponseObject(c.toString(),-1,null);
                
        }
        
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdReservaEstado() {
        return idReservaEstado;
    }

    public void setIdReservaEstado(int idReservaEstado) {
        this.idReservaEstado = idReservaEstado;
    }

    public Timespan getFechaHora() {
        return (Timespan) fechaHora;
    }

    public void setFechaHora(Timespan fechaHora) {
        this.fechaHora = (Timestamp) fechaHora;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
