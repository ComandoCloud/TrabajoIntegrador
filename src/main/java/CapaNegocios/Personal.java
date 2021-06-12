package CapaNegocios;
import CapaDatos.Conexion;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Personal extends Persona {
    private int idPersonal;
    private String Usuario;
    private String Password;
    private int IdPersonalCargo;
    
    public Personal(){
        super();
    }
    
    public Personal(String User, String Password, int id_personal_cargo){
        super();
        this.Usuario = User;
        this.Password = Password;
        this.IdPersonalCargo = id_personal_cargo;
    }
    
    public DefaultTableModel Listar() throws SQLException, InterruptedException
    {
        DefaultTableModel dt = new DefaultTableModel();
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
    
       public ResponseObject Guardar(Personal oPersonal) throws SQLException {
        if(oPersonal!=null){
            int idNuevo=0;
            Conexion oCon =GetoCon();
            if(oPersonal.GetId()==0){
                try{
                    oCon.Conectar();
                    oCon.CrearComando("INSERT INTO personal (nombre,apellido,email,telefono,clave,id_personal_cargo) VALUES (?,?,?,?,?,?)");
                    
                    oCon.comando.setString(1, oPersonal.GetNombre());
                    oCon.comando.setString(2, oPersonal.GetApellido());
                    oCon.comando.setString(3, oPersonal.GetEmail());
                    oCon.comando.setString(4, oPersonal.GetTelefono());
                    oCon.comando.setString(5, oPersonal.GetPassword());
                    oCon.comando.setInt(6, oPersonal.GetIdPersonalCargo());

                    oCon.EjecutarComando();
                    oCon.Desconectar();
                    return new ResponseObject("Guardado correctamente",0);
                }   
                catch(Exception e) {
                    oCon.Desconectar();
                    String error = e.toString();
                    return new ResponseObject("Error: "+ e.toString(),-1);
                }
            }
            else
                return new ResponseObject("La cancha ya tiene un Id: ",-1);
        }
        return new ResponseObject("Cancha es null: ",-1);
    }

    /**
     * @return the Usuario
     */
    public String GetUsuario() {
        return Usuario;
    }

    /**
     * @param Usuario the Usuario to set
     */
    public void SetUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    /**
     * @return the Password
     */
    public String GetPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void SetPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the IdPersonalCargo
     */
    public int GetIdPersonalCargo() {
        return IdPersonalCargo;
    }

    /**
     * @param IdPersonalCargo the IdPersonalCargo to set
     */
    public void SetIdPersonalCargo(int IdPersonalCargo) {
        this.IdPersonalCargo = IdPersonalCargo;
    }
    


}
