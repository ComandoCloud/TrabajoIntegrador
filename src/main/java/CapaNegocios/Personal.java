package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Personal extends Persona implements IOperacionesBasicas<Object> {

    private int idPersonal;
    private String usuario;
    private String password;
    private int idPersonalCargo;

    public Personal() {
        super();
    }

    public Personal(String user, String password, int idPersonalCargo) {
        super();
        this.usuario = user;
        this.password = password;
        this.idPersonalCargo = idPersonalCargo;
    }

    @Override
    public ResponseObject Listar() throws SQLException {
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();
        try {
            oCon.Conectar();
            oCon.CrearComando("SELECT * FROM personal where borrado= ? ");
            oCon.comando.setString(1, "0");
            oCon.EjecutarComando();
            dt = oCon.Tabla();
            oCon.Desconectar();
            return new ResponseObject("", 0, dt);
        } catch (SQLException e) {
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1,null);
        }

    }

    //REGION DE METODOS
    //METODO PARA INSERTAR O EDITAR UN REGISTRO EN LA TABLA PERSONAL, SI EL OBEJETO oPersonal TIENE Id>0 QUIERE DECIR QUE YA EXISTE POR LO TANTO SE HARA UN UPDATE
    //SI TIENE id = 0 POR ENDE AUN NO EXISTE POR LO TANTO SE INSERTARA EN LA BASE DE DATOS
    //SE USA ESTA LOGICA EN TODO EL PROYECTO, EN TODOS LOS METODOS GUARDAR
    @Override
    public ResponseObject Guardar(Object oPersonal) throws SQLException {
        //VALIDACION PARA EVITAR UNA EXCEPCION DE NULLPOINTER
        if (oPersonal != null) {
            int idNuevo = 0;
            if (this.getId() == 0) {
                try {
                    //SE ABRE UNA CONEXION A LA BBDD
                    getoCon().Conectar();
                    //SE CREA UNA ESTRUCTURA DE CONSULTA SQL, EN ESTE CASO UNA INSERSION 
                    getoCon().CrearComando("INSERT INTO personal (nombre,apellido,email,telefono,clave,id_personal_cargo,dni) VALUES (?,?,?,?,?,?,?)");
                    //SE TERMINA DE PREPARAR LA CONSULTA REEMPLAZANDO LOS SIGNOS DE INTERROGACION POR CADA DATO CORRESPONDIENTE
                    getoCon().comando.setString(1, this.getNombre());
                    getoCon().comando.setString(2, this.getApellido());
                    getoCon().comando.setString(3, this.getEmail());
                    getoCon().comando.setString(4, this.getTelefono());
                    getoCon().comando.setString(5, this.getPassword());
                    getoCon().comando.setInt(6, this.getIdPersonalCargo());
                    getoCon().comando.setString(7, this.getDni());
                    //UNA VEZ DEFINIDA LA CONSULTA, ES EJECUTADA POR EL MOTOR
                    getoCon().EjecutarComando();
                    //CIERRA A CONEXION A LA BBDD
                    getoCon().Desconectar();
                    //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA
                    return new ResponseObject("Guardado correctamente", 0);
                } catch (Exception e) {
                    //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION (UN ERROR AL INSERTAR EL REGISTRO O PREPARAR LA CONSULTA), ENTONCES CERRAMOS LA CONEXION
                    getoCon().Desconectar();
                    //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            } else {
                try {
                    getoCon().Conectar();
                    getoCon().CrearComando("UPDATE personal SET nombre = ?,apellido = ?, email = ?,telefono=?,clave=?, id_personal_cargo = ?, dni = ? where id = ?");
                    getoCon().comando.setString(1, this.getNombre());
                    getoCon().comando.setString(2, this.getApellido());
                    getoCon().comando.setString(3, this.getEmail());
                    getoCon().comando.setString(4, this.getTelefono());
                    getoCon().comando.setString(5, this.getPassword());
                    getoCon().comando.setInt(6, this.getIdPersonalCargo());
                    getoCon().comando.setString(7, this.getDni());
                    getoCon().comando.setInt(8, this.getId());
                    getoCon().EjecutarComando();
                    getoCon().Desconectar();
                    return new ResponseObject("Editado correctamente", 0);
                } catch (Exception e) {
                    getoCon().Desconectar();
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            }
        }
        return new ResponseObject("Cancha es null: ", -1);
    }

    @Override
    public ResponseObject Eliminar(int idPersonal) throws SQLException {
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            getoCon().Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE POR LO TANTO SI QUEREOS "ELIMINAR UN REGISTRO LOGICAMENTE", ACTUALIZAMOS EL VALOR BORRADO EN 1
            getoCon().CrearComando("update perrosnal set borrado=1 where id = ?");
            getoCon().comando.setInt(1, idPersonal);
            getoCon().EjecutarComando();
            //DESCONECTAMOS LA BASE DE DATOS
            getoCon().Desconectar();
            return new ResponseObject("Eliminado correctamente", 0);
        } catch (Exception e) {
            //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION ENTONCES CERRAMOS LA CONEXION
            getoCon().Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1);
        }
    }

    //REGION DE GETTERS Y SETTERS
    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdPersonalCargo() {
        return idPersonalCargo;
    }

    public void setIdPersonalCargo(int idPersonalCargo) {
        this.idPersonalCargo = idPersonalCargo;
    }

}
