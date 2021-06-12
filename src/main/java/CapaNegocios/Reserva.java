package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public boolean VerDisponiblidad(Timestamp fechaHora) throws SQLException{
        
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();          
        oCon.Conectar();
        oCon.CrearComando("SELECT * FROM reservas where fecha_hora= ? ");
        oCon.comando.setTimestamp(1, fechaHora);
        oCon.EjecutarComando();
        dt = oCon.Tabla();
        oCon.Desconectar();
        return true;
        
        
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
