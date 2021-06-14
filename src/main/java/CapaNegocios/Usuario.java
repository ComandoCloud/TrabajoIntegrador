package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Usuario extends Persona {

    //REGION DE PROPIEDADES
    private String pass;
    private static Usuario oUsuario;
    private Conexion oCon = new Conexion();

    //CONSTRUCTOR
    public Usuario() {
        super();
    }

    //REGION DE METODOS
    //METODO DE INICIO DE SESION CON EMAIL Y CONTRASEÑA
    //DEVUELVE UN OBJETO TIPO USUARIO CON LOS DATOS DEL USUARIO O PERSONAL
    //PRIMERO BUSCA CONICIDENCIAS EN LA TABLA PERSONAL, SINO HAY NINGUNA CONICIDENCIA, SE BUSCA
    //EN LA TABLA USUARIOS
    //DEPENDIENDO DE DONDE SE ENCUENTRE LA CONICIDENCIA, EL ARTRIBUTO TIPOPERSONAL ES 1 SI ES PERSONAL O 2 SI ES UN USUARIO COMUN
    public Usuario IniciarSesion(String email, char[] clave) throws SQLException {

        Usuario oUsuario = new Usuario();
        DefaultTableModel dt = new DefaultTableModel();
        String pass = String.valueOf(clave);

        //PRIMERO SE BUSCA CONICIDENCIA EN LA TABLA PERSONAL
        try {
            //SE ABRE UNA CONEXION A LA BBDD
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
            oCon.CrearComando("SELECT * FROM personal WHERE email = ? and clave = ? ");
            oCon.comando.setString(1, email);
            oCon.comando.setString(2, pass);
            //LA TABBLA QUE DEVUELVE LA CONSULTA REALIZADA, LA GUARDO EN UNA TABLA LOCAL QUE SERA LA QUE RETORNE ESTE METODO
            dt = oCon.Tabla();
            //DESCONECTAMOS LA BASE DE DATOS
            oCon.Desconectar();
        } catch (Exception e) {
            oCon.Desconectar();
        }
        //SI ENCONTRO COINCIDECNCIA LLENO UN OBJTO DE TIPO USUARIO PARA DEJARLO ESTATICO Y PUBLICO
        if (dt.getRowCount() > 0) {
            oUsuario.setApellido((String) dt.getValueAt(0, 2));
            oUsuario.setNombre((String) dt.getValueAt(0, 1));
            oUsuario.setEmail(email);
            oUsuario.setPass(pass);
            oUsuario.setId((Integer) dt.getValueAt(0, 0));
            oUsuario.setTelefono((String) dt.getValueAt(0, 4));
            oUsuario.setTipoPersona(1);
        } else {
            //SI NO ENCONTRO NADA EN LA TABLA PERSONAL, SE BUSCA EN LA TABLA USUARIOS
            try {
                //SE ABRE UNA CONEXION A LA BBDD
                oCon.Conectar();
                //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
                oCon.CrearComando("SELECT * FROM usuarios WHERE email = ? and clave = ? AND borrado = 0");
                oCon.comando.setString(1, email);
                oCon.comando.setString(2, pass);
                //LA TABBLA QUE DEVUELVE LA CONSULTA REALIZADA, LA GUARDO EN UNA TABLA LOCAL QUE SERA LA QUE RETORNE ESTE METODO
                dt = oCon.Tabla();
                //DESCONECTAMOS LA BASE DE DATOS
                oCon.Desconectar();
            } catch (Exception e) {
                oCon.Desconectar();
            }
            //SI ENCONTRO COINCIDECNCIA LLENO UN OBJTO DE TIPO USUARIO PARA DEJARLO ESTATICO Y PUBLICO
            if (dt.getRowCount() > 0) {
                oUsuario.setApellido((String) dt.getValueAt(0, 2));
                oUsuario.setNombre((String) dt.getValueAt(0, 1));
                oUsuario.setEmail(email);
                oUsuario.setPass(pass);
                oUsuario.setId((Integer) dt.getValueAt(0, 0));
                oUsuario.setTelefono((String) dt.getValueAt(0, 4));
                oUsuario.setTipoPersona(2);
            } else {
                return null;
            }
        }
        //RETNORNO EL OBJETO USUARIO
        return oUsuario;
    }

    //METODO PARA LISTAR INFORMACION, EN ESTE CASO USUARIOS 
    public ResponseObject Listar() throws SQLException, InterruptedException {
        DefaultTableModel dt = new DefaultTableModel();
        try {
            //SE ESTABLECE UNA COMUNICACION CON LA BASE DE DATOS
            oCon.Conectar();
            //PREPARAMOS LA CONSULTA O QUERY IMPLEMENTANDO UNA IDEA DE BORRADO LOGICO, LOS REGISTROS QUE ESTAN CON BORRADO=0 SON LOS QUE TIENEN QUE ESTAR VISIBLES Y/O ACCESIBLE
            oCon.CrearComando("SELECT * FROM USUARIOS where borrado= ? ");
            oCon.comando.setString(1, "0");
            //LA TABBLA QUE DEVUELVE LA CONSULTA REALIZADA, LA GUARDO EN UNA TABLA LOCAL QUE SERA LA QUE RETORNE ESTE METODO
            dt = oCon.Tabla();
            //DESCONECTAMOS LA BASE DE DATOS
            oCon.Desconectar();
            return new ResponseObject("", 0, dt);
        } catch (SQLException e) {
             //SI LLEGO A ESTE PUNTO ES PORQUE HUBO UNA EXCPCION ENTONCES CERRAMOS LA CONEXION
            oCon.Desconectar();
            //RETORNA UN OBJETO CREADO PARA ALMACENAR MAS DE UN TIPO DE RESPUESTA, EN ESTE CASO DEVUELVE EL MENSAJE DE LA EXEPCION Y UN CODIGO QUE HACE REFERENCUA A LA MISMA
            return new ResponseObject("Error: " + e.toString(), -1, null);
        }
    }

    //METODO PARA INSERTAR O EDITAR UN REGISTRO EN LA TABLA usuarios, SI EL OBEJETO oUsuario TIENE Id>0 QUIERE DECIR QUE YA EXISTE POR LO TANTO SE HARA UN UPDATE
    //SI TIENE id = 0 POR ENDE AUN NO EXISTE POR LO TANTO SE INSERTARA EN LA BASE DE DATOS
    //SE USA ESTA LOGICA EN TODO EL PROYECTO, EN TODOS LOS METODOS GUARDAR
    public ResponseObject Guardar(Usuario oUsuario) throws SQLException {
        if (oUsuario != null) {
            int idNuevo = 0;
            if (oUsuario.getId() == 0) {
                try {
                    //SE ABRE UNA CONEXION A LA BBDD
                    getoCon().Conectar();
                    //SE CREA UNA ESTRUCTURA DE CONSULTA SQL, EN ESTE CASO UNA INSERSION 
                    getoCon().CrearComando("INSERT INTO usuarios (nombre,apellido,email,telefono,clave) VALUES (?,?,?,?,?)");
                    //SE TERMINA DE PREPARAR LA CONSULTA REEMPLAZANDO LOS SIGNOS DE INTERROGACION POR CADA DATO CORRESPONDIENTE
                    getoCon().comando.setString(1, oUsuario.getNombre());
                    getoCon().comando.setString(2, oUsuario.getApellido());
                    getoCon().comando.setString(3, oUsuario.getEmail());
                    getoCon().comando.setString(4, oUsuario.getTelefono());
                    getoCon().comando.setString(5, oUsuario.getPass());
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
                    getoCon().CrearComando("UPDATE usuarios SET nombre = ?,apellido = ?, email = ?,telefono=?,clave=? where id = ?");
                    getoCon().comando.setString(1, oUsuario.getNombre());
                    getoCon().comando.setString(2, oUsuario.getApellido());
                    getoCon().comando.setString(3, oUsuario.getEmail());
                    getoCon().comando.setString(4, oUsuario.getTelefono());
                    getoCon().comando.setString(5, oUsuario.getPass());
                    getoCon().comando.setInt(6, oUsuario.getId());
                    getoCon().EjecutarComando();
                    getoCon().Desconectar();
                    return new ResponseObject("Editado correctamente", 0);
                } catch (Exception e) {
                    getoCon().Desconectar();
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            }
        }
        return new ResponseObject("Usuario es null: ", -1);
    }

    //GETTERS Y SETTERS
    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static Usuario getoUsuario() {
        return oUsuario;
    }

    public static void setoUsuario(Usuario aoUsuario) {
        oUsuario = aoUsuario;
    }

}
