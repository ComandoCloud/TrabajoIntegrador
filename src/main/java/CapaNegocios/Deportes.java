package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Deportes {

    //REGION DE PROPIEDADES
    private int idDeportes;
    private String descripcion;
    private int borrado;
    private Conexion oCon = new Conexion();

    //CONSTRUCTORES
    public Deportes(int id, String descripcion) {
        this.idDeportes = id;
        this.descripcion = descripcion;
    }

    public Deportes() {

    }

    //REGION DE METODOS
    //METODO PARA INSERTAR O EDITAR UN REGISTRO EN LA TABLA DEPORTES, SI EL OBEJETO oDeporte TIENE Id>0 QUIERE DECIR QUE YA EXISTE POR LO TANTO SE HARA UN UPDATE
    //SI TIENE id = 0 POR ENDE AUN NO EXISTE POR LO TANTO SE INSERTARA EN LA BASE DE DATOS
    //SE USA ESTA LOGICA EN TODO EL PROYECTO, EN TODOS LOS METODOS GUARDAR
    public ResponseObject Guardar(Deportes oDeporte) throws SQLException {
        //VALIDACION PARA EVITAR UNA EXCEPCION DE NULLPOINTER
        if (oDeporte != null) {
            int idNuevo = 0;
            if (oDeporte.getIdDeportes() == 0) {
                try {
                    //SE ABRE UNA CONEXION A LA BBDD
                    oCon.Conectar();
                    //SE CREA UNA ESTRUCTURA DE CONSULTA SQL, EN ESTE CASO UNA INSERSION 
                    oCon.CrearComando("INSERT INTO deportes (descripcion) VALUES (?)");
                    //SE TERMINA DE PREPARAR LA CONSULTA REEMPLAZANDO LOS SIGNOS DE INTERROGACION POR CADA DATO CORRESPONDIENTE
                    oCon.comando.setString(1, oDeporte.getDescripcion());
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
                    //SE ABRE UNA CONEXION A LA BBDD
                    oCon.Conectar();
                    oCon.CrearComando("UPDATE deportes SET descripcion = ? where id = ?");
                    //SE TERMINA DE PREPARAR LA CONSULTA REEMPLAZANDO LOS SIGNOS DE INTERROGACION POR CADA DATO CORRESPONDIENTE
                    oCon.comando.setString(1, oDeporte.getDescripcion());
                    oCon.comando.setInt(2, oDeporte.getIdDeportes());
                    //UNA VEZ DEFINIDA LA CONSULTA, ES EJECUTADA POR EL MOTOR
                    oCon.EjecutarComando();
                    //CIERRA A CONEXION A LA BBDD
                    oCon.Desconectar();
                    //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA
                    return new ResponseObject("Editado correctamente", 0);
                } catch (Exception e) {
                    //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION (UN ERROR AL EDITAR EL REGISTRO O PREPARAR LA CONSULTA), ENTONCES CERRAMOS LA CONEXION
                    oCon.Desconectar();
                    //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            }
        }
        return new ResponseObject("Deporte es null: ", -1);
    }

    //METODO PARA LISTAR INFORMACION, EN ESTE CASO DEPORTES 
    public ResponseObject Listar() throws SQLException {
        //CREO UNA INSTACIA DE UNA "TABLA" LA CUAL SERA NUTRIDA POR EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        DefaultTableModel dt = new DefaultTableModel();
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
            oCon.CrearComando("SELECT * FROM deportes WHERE borrado=0");
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

    public ResponseObject Eliminar(int idDeporte) throws SQLException {
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE POR LO TANTO SI QUEREOS "ELIMINAR UN REGISTRO LOGICAMENTE", ACTUALIZAMOS EL VALOR BORRADO EN 1
            oCon.CrearComando("update deportes set borrado=1 where id = ?");
            oCon.comando.setInt(1, idDeporte);
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
    
    //SOBREESCRIBIMOS EL METODO TOSTRING() PARA IMPLEMENTAR EL CONTROL JCOMBOBOX MOSTRANDO LA DESCRPCION DEL OBJETO DEPORTE
    @Override
    public String toString() {
        return this.descripcion;
    }
}
