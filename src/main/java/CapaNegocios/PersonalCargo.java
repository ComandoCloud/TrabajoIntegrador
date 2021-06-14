package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class PersonalCargo {

    private int id;
    private String descripcion;
    private int borrado;
    private Conexion oCon = new Conexion();

    public PersonalCargo() {

    }

    public PersonalCargo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;

    }

    //METODO PARA LISTAR INFORMACION, EN ESTE CASO cargos Y OTROS DATOS 
    public ResponseObject Listar() throws SQLException, InterruptedException {
        //CREO UNA INSTACIA DE UNA "TABLA" LA CUAL SERA NUTRIDA POR EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        DefaultTableModel dt = new DefaultTableModel();
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
            oCon.CrearComando("SELECT * FROM personal_cargos where borrado = 0 ");
            //LA TABBLA QUE DEVUELVE LA CONSULTA REALIZADA, LA GUARDO EN UNA TABLA LOCAL QUE SERA LA QUE RETORNE ESTE METODO
            dt = oCon.Tabla();
            oCon.Desconectar();
            return new ResponseObject("", 0, dt);
        } catch (Exception e) {
            //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION ENTONCES CERRAMOS LA CONEXION
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1, null);
        }
    }

    //REGION DE GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    //SOBREESCRIBIMOS EL METODO TOSTRING() PARA IMPLEMENTAR EL CONTROL JCOMBOBOX MOSTRANDO LA DESCRPCION DEL OBJETO cargo
    @Override
    public String toString() {
        return this.getDescripcion();
    }
}
