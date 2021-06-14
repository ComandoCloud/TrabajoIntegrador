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

    //REGION DE PROPIEDADES
    private int id;
    private int idCancha;
    private int idUsuario;
    private int idPersonal;
    private int idReservaEstado;
    private Timestamp fechaHora;
    private int duracion;
    private Conexion oCon = new Conexion();

    //REGION DE METODOS
    //METODO PARA VER EL ESTADO DE UN UN TURNO (COMPUESTO POR EL ID_CANCHA Y LA FECHA,
    //SI YA EXITE UNA RESERVA EN ESA FECHA Y EN ESA CANCHA, ENTONCES DEVUELVE EL ESTADO DE LA RESERVA
    //SI NO EXISTE DEVUELVE LIBRE
    public ReservasEstados verEstado(Timestamp fechaHora, int idCancha) throws SQLException {

        DefaultTableModel dt = new DefaultTableModel();
        //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
        oCon.Conectar();
        //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
        oCon.CrearComando("SELECT reservas.id,reservas.id_cancha, reservas.id_usuario, reservas.id_reserva_estado,reservas.fecha_hora,reservas.duracion,reservas.borrado,reservas_estados.descripcion as estado "
                + " FROM reservas "
                + " LEFT JOIN reservas_estados on reservas_estados.id = reservas.id_reserva_estado "
                + " where reservas.fecha_hora= ? and reservas.id_cancha = ? and reservas.borrado=0 ");
        oCon.comando.setTimestamp(1, fechaHora);
        oCon.comando.setInt(2, idCancha);
        //LA TABBLA QUE DEVUELVE LA CONSULTA REALIZADA, LA GUARDO EN UNA TABLA LOCAL QUE SERA LA QUE RETORNE ESTE METODO
        dt = oCon.Tabla();
        //DESCONECTAMOS LA BASE DE DATOS
        oCon.Desconectar();
        // SI ENTONCTRO AL MENOS UN REGISTRO DEVUELVE EL ESTADO DE LA RESERVA
        if (dt.getRowCount() > 0) {
            return new ReservasEstados((Integer) dt.getValueAt(0, 3), (String) dt.getValueAt(0, 7));
        }
        return null;
    }

    //METODO PARA ARMAR LA GRILLA DE HORARIOS, GENERO UNA ESCTRUCTURA DE DOS COLUMNAS POR AHORA, LA HORA, Y EL ESTADO
    public ResponseObject getHorarios(int idCancha, String fecha) throws SQLException, ParseException {
        //CREO LA ESTRUCTURA DE LA TABLA RESULTANTE
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("hora");
        dt.addColumn("estado");
        //AGREGO LOS HORARIOS, POR DEFECTO EL ESTADO DEL TURNO ES LIBRE
        for (int i = 8; i < 24; i++) {
            if (i < 10) {
                dt.addRow(new Object[]{"0" + i + ":00", "libre"});
            } else {
                dt.addRow(new Object[]{i + ":00", "libre"});
            }
        }
        String horaAux = "";
        //RECORRO TODOS LOS HORARIOS Y CON EL ID DE CANCHA Y LA FECHA COMPLETA LLAMO AL METODO VERESTADO
        for (int row = 0; row < dt.getRowCount(); row++) {
            for (int col = 0; col < dt.getColumnCount(); col++) {
                if (col == 0) {
                    //ARMO LA FECHA COMPLETA CONCATENANDO LA FECHA  + LA HORA
                    try {
                        horaAux = dt.getValueAt(row, col).toString();
                        String fechaCompleta = fecha + " " + horaAux;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                        Date parsedDate = dateFormat.parse(fechaCompleta);
                        Timestamp fechaTime = new java.sql.Timestamp(parsedDate.getTime());
                        ReservasEstados oEstado = verEstado(fechaTime, idCancha);
                        if (oEstado == null) {
                            dt.setValueAt("LIBRE", row, col + 1);
                        } else {
                            dt.setValueAt(oEstado.getDescripcion().toUpperCase(), row, col + 1);
                        }
                    } catch (ParseException e) {
                        dt.setValueAt("ERROR AL CONSULTAR TURNO: " + e.toString(), row, col + 1);
                    } catch (SQLException e) {
                        dt.setValueAt("ERROR AL CONSULTAR TURNO: " + e.toString(), row, col + 1);
                    }

                }
            }
        }
        //RETORNA LA TABLA FINAL ARMADA CON LOS HORARIOS Y EL ESTADO
        return new ResponseObject("", 0, dt);
    }

    //METODO PARA RESERVAR TURNO PARA UNA CANCHA EN UNA DETERMINADA FECHA Y HORA
    public ResponseObject Reservar(int idCancha, Timestamp fecha, int idUsuario, int estado) throws SQLException {
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //SE CREA UNA ESTRUCTURA DE CONSULTA SQL, EN ESTE CASO UNA INSERSION 
            oCon.CrearComando("INSERT INTO reservas (id_cancha,id_usuario,id_reserva_estado,fecha_hora,duracion) VALUES (?,?,?,?,?)");
            //SE TERMINA DE PREPARAR LA CONSULTA REEMPLAZANDO LOS SIGNOS DE INTERROGACION POR CADA DATO CORRESPONDIENTE
            oCon.comando.setInt(1, idCancha);
            oCon.comando.setInt(2, idUsuario);
            oCon.comando.setInt(3, estado);
            oCon.comando.setTimestamp(4, fecha);
            oCon.comando.setInt(5, 1);
            //UNA VEZ DEFINIDA LA CONSULTA, ES EJECUTADA POR EL MOTOR
            oCon.EjecutarComando();
            //CIERRA A CONEXION A LA BBDD
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA
            return new ResponseObject("Reserva ingresada correctamente", 0, null);
        } catch (SQLException e) {
            //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION (UN ERROR AL INSERTAR EL REGISTRO O PREPARAR LA CONSULTA), ENTONCES CERRAMOS LA CONEXION
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1);

        }

    }

    //REGION DE GETTERS Y SETTERS
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
