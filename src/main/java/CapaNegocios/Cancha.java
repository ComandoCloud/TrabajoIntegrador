package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Cancha{

    //REGION DE PROPIEDADES
    private int idCancha;
    private int idDeporte;
    private String descripcion;
    private String ancho;
    private String largo;
    private int borrado;
    private Conexion oCon = new Conexion();

    //CONSTRUCTORES
    public Cancha(int idCancha, String descripcion) {
        this.idCancha = idCancha;
        this.descripcion = descripcion;

    }

    public Cancha() {

    }

    //REGION DE METODOS
    //METODO PARA INSERTAR O EDITAR UN REGISTRO EN LA TABLA CANCHAS, SI EL OBEJETO oCancha TIENE Id>0 QUIERE DECIR QUE YA EXISTE POR LO TANTO SE HARA UN UPDATE
    //SI TIENE id = 0 POR ENDE AUN NO EXISTE POR LO TANTO SE INSERTARA EN LA BASE DE DATOS
    //SE USA ESTA LOGICA EN TODO EL PROYECTO, EN TODOS LOS METODOS GUARDAR
    public ResponseObject Guardar(Cancha oCancha) throws SQLException {
        //VALIDACION PARA EVITAR UNA EXCEPCION DE NULLPOINTER
        if (oCancha != null) {
            int idNuevo = 0;
            if (oCancha.getIdCancha() == 0) {
                try {
                    //SE ABRE UNA CONEXION A LA BBDD
                    oCon.Conectar();
                    //SE CREA UNA ESTRUCTURA DE CONSULTA SQL, EN ESTE CASO UNA INSERSION 
                    oCon.CrearComando("INSERT INTO canchas (id_deporte,descripcion,ancho,largo) VALUES (?,?,?,?)");
                    //SE TERMINA DE PREPARAR LA CONSULTA REEMPLAZANDO LOS SIGNOS DE INTERROGACION POR CADA DATO CORRESPONDIENTE
                    oCon.comando.setInt(1, oCancha.getIdDeporte());
                    oCon.comando.setString(2, oCancha.getDescripcion());
                    oCon.comando.setString(3, oCancha.getAncho());
                    oCon.comando.setString(4, oCancha.getLargo());
                    //UNA VEZ DEFINIDA LA CONSULTA, ES EJECUTADA POR EL MOTOR
                    oCon.EjecutarComando();
                    //CIERRA A CONEXION A LA BBDD
                    oCon.Desconectar();
                    //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA
                    return new ResponseObject("Guardado correctamente", 0);
                } catch (Exception e) {
                    //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION (UN ERROR AL INSERTAR EL REGISTRO O PREPARAR LA CONSULTA), ENTONCES CERRAMOS LA CONEXION
                    oCon.Desconectar();
                    //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            } else {
                try {
                    oCon.Conectar();
                    oCon.CrearComando("UPDATE canchas SET id_deporte = ?,descripcion = ?, ancho = ?,largo=? where id = ?");
                    oCon.comando.setInt(1, oCancha.getIdDeporte());
                    oCon.comando.setString(2, oCancha.getDescripcion());
                    oCon.comando.setString(3, oCancha.getAncho());
                    oCon.comando.setString(4, oCancha.getLargo());
                    oCon.comando.setInt(5, oCancha.getIdCancha());
                    oCon.EjecutarComando();
                    oCon.Desconectar();
                    return new ResponseObject("Editado correctamente", 0);
                } catch (Exception e) {
                    oCon.Desconectar();
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            }
        }
        return new ResponseObject("Cancha es null: ", -1);
    }

    //METODO PARA LISTAR INFORMACION, EN ESTE CASO CANCHAS Y OTROS DATOS 
    public ResponseObject Listar() throws SQLException {
        //CREO UNA INSTACIA DE UNA "TABLA" LA CUAL SERA NUTRIDA POR EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        DefaultTableModel dt = new DefaultTableModel();
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
            oCon.CrearComando("SELECT canchas.*, deportes.descripcion as deporte FROM canchas left join deportes on deportes.id = canchas.id_deporte WHERE canchas.borrado=0");
            //LA TABBLA QUE DEVUELVE LA CONSULTA REALIZADA, LA GUARDO EN UNA TABLA LOCAL QUE SERA LA QUE RETORNE ESTE METODO
            dt = oCon.Tabla();
            //DESCONECTAMOS LA BASE DE DATOS
            oCon.Desconectar();
            //RETORNAMOS UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTA OCACION, UNA TABLA
            return new ResponseObject("", 0, dt);
        } catch (Exception e) {
            //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION ENTONCES CERRAMOS LA CONEXION
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1, null);
        }
    }

    public ResponseObject Eliminar(int idCancha) throws SQLException {
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE POR LO TANTO SI QUEREOS "ELIMINAR UN REGISTRO LOGICAMENTE", ACTUALIZAMOS EL VALOR BORRADO EN 1
            oCon.CrearComando("update canchas set borrado=1 where id = ?");
            oCon.comando.setInt(1, idCancha);
            oCon.EjecutarComando();
            //DESCONECTAMOS LA BASE DE DATOS
            oCon.Desconectar();
            return new ResponseObject("Eliminado correctamente", 0);
        } catch (Exception e) {
            //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION ENTONCES CERRAMOS LA CONEXION
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1);
        }
    }

    //REGION DE GETTERS Y SETTERS
    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public int getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(int idDeporte) {
        this.idDeporte = idDeporte;
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

    //SOBREESCRIBIMOS EL METODO TOSTRING() PARA IMPLEMENTAR EL CONTROL JCOMBOBOX MOSTRANDO LA DESCRPCION DEL OBJETO CANCHA
    @Override
    public String toString() {
        return this.descripcion;
    }


    
}
