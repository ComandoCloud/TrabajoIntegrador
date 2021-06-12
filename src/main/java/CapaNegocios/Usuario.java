package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Usuario extends Persona {

    private String pass;
    private static Usuario oUsuario;

    public Usuario() {
        super();
    }

    public Usuario IniciarSesion(String email, char[] clave) throws SQLException {

        Usuario oUsuario = new Usuario();

        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();
        String pass = String.valueOf(clave);
        try {
            oCon.Conectar();
            oCon.CrearComando("SELECT * FROM personal WHERE email = ? and clave = ? ");
            oCon.comando.setString(1, email);
            oCon.comando.setString(2, pass);
            dt = oCon.Tabla();
            oCon.Desconectar();
        } catch (Exception e) {
            oCon.Desconectar();
        }
        if (dt.getRowCount() > 0) {
            oUsuario.SetApellido((String) dt.getValueAt(0, 2));
            oUsuario.SetNombre((String) dt.getValueAt(0, 1));
            oUsuario.SetEmail(email);
            oUsuario.setPass(pass);
            oUsuario.SetId((Integer) dt.getValueAt(0, 0));
            oUsuario.SetTelefono((String) dt.getValueAt(0, 4));
            oUsuario.setTipoPersona(1);
        } else {
            try {
                oCon.Conectar();
                oCon.CrearComando("SELECT * FROM usuarios WHERE email = ? and clave = ? ");
                oCon.comando.setString(1, email);
                oCon.comando.setString(2, pass);
                dt = oCon.Tabla();
                oCon.Desconectar();
            } catch (Exception e) {
                oCon.Desconectar();
            }
            if (dt.getRowCount() > 0) {
                oUsuario.SetApellido((String) dt.getValueAt(0, 2));
                oUsuario.SetNombre((String) dt.getValueAt(0, 1));
                oUsuario.SetEmail(email);
                oUsuario.setPass(pass);
                oUsuario.SetId((Integer) dt.getValueAt(0, 0));
                oUsuario.SetTelefono((String) dt.getValueAt(0, 4));
                oUsuario.setTipoPersona(2);
            } else {
                return null;
            }
        }
        return oUsuario;
    }

    public static void main(String[] args) {

    }

    //METODOS
    public DefaultTableModel Listar() throws SQLException, InterruptedException {
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();
        oCon.Conectar();
        oCon.CrearComando("SELECT * FROM USUARIOS where borrado= ? ");
        oCon.comando.setString(1, "0");
        oCon.EjecutarComando();
        dt = oCon.Tabla();
        oCon.Desconectar();
        return dt;
    }

    public ResponseObject Guardar(Usuario oUsuario) throws SQLException {
        if (oUsuario != null) {
            int idNuevo = 0;
            Conexion oCon = GetoCon();
            if (oUsuario.GetId() == 0) {
                try {
                    oCon.Conectar();
                    oCon.CrearComando("INSERT INTO usuarios (nombre,apellido,email,telefono,clave) VALUES (?,?,?,?,?)");

                    oCon.comando.setString(1, oUsuario.GetNombre());
                    oCon.comando.setString(2, oUsuario.GetApellido());
                    oCon.comando.setString(3, oUsuario.GetEmail());
                    oCon.comando.setString(4, oUsuario.GetTelefono());
                    oCon.comando.setString(5, oUsuario.getPass());
                    oCon.EjecutarComando();
                    oCon.Desconectar();
                    return new ResponseObject("Guardado correctamente", 0);
                } catch (Exception e) {
                    oCon.Desconectar();
                    String error = e.toString();
                    return new ResponseObject("Error: " + e.toString(), -1);
                }
            } else {
                try {
                    oCon.Conectar();
                    oCon.CrearComando("UPDATE usuarios SET nombre = ?,apellido = ?, email = ?,telefono=?,clave=? where id = ?");

                    oCon.comando.setString(1, oUsuario.GetNombre());
                    oCon.comando.setString(2, oUsuario.GetApellido());
                    oCon.comando.setString(3, oUsuario.GetEmail());
                    oCon.comando.setString(4, oUsuario.GetTelefono());
                    oCon.comando.setString(5, oUsuario.getPass());
                    oCon.comando.setInt(6, oUsuario.GetId());

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
