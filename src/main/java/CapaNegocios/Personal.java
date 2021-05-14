package CapaNegocios;
import CapaDatos.Conexion;
import CapaNegocios.Persona;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Personal extends Persona{
    String Usuario;
    String Password;
    int id_personal_cargo;
    public Personal(){

    }
    public Personal(String User, String Password, int id_personal_cargo){
        super();
        this.Usuario = User;
        this.Password = Password;
        this.id_personal_cargo = id_personal_cargo;
    }
    
    public JTable Listar() throws SQLException, InterruptedException
    {
        JTable dt = new JTable();
        Conexion oCon = new Conexion();          
        oCon.Conectar();
        oCon.CrearComando("SELECT * FROM personal where borrado= ? ");
        oCon.comando.setString(1, "0");
        oCon.EjecutarComando();
        dt = oCon.Tabla();
        oCon.Desconectar();
        return dt;
    }
    
    public static void main(String[] args) throws SQLException, InterruptedException {
            Personal oPers = new Personal();
            oPers.Listar();
    }
    

}
