package CapaNegocios;

import CapaDatos.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Usuario extends Persona{
    
    private String email;
    private String pass;
    
    
    public Usuario(){
        super();
    }
    
    public Usuario IniciarSesion(String email, char[] clave) throws SQLException{
        
        Usuario oUsuario = new Usuario();
                
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();  
        String pass = String.valueOf(clave);
         try{
            oCon.Conectar();
            oCon.CrearComando("SELECT * FROM personal WHERE email = ? and clave = ? ");
            oCon.comando.setString(1, email);
            oCon.comando.setString(2, pass);
            dt = oCon.Tabla();
            oCon.Desconectar();
       }   
        catch(Exception e){
            oCon.Desconectar();
        }
        if(dt.getRowCount()>0){
            oUsuario.SetApellido((String)dt.getValueAt(0, 2));
            oUsuario.SetNombre((String)dt.getValueAt(0, 1));
            oUsuario.setEmail(email);
            oUsuario.setPass(pass);
            oUsuario.SetId((Integer)dt.getValueAt(0, 1));
            oUsuario.SetTelefono((String)dt.getValueAt(0, 4));
        }
        else{
            try{
                oCon.Conectar();
                oCon.CrearComando("SELECT * FROM usuarios WHERE email = ? and clave = ? ");
                oCon.comando.setString(1, email);
                oCon.comando.setString(2, pass);
                dt = oCon.Tabla();
                oCon.Desconectar();
            }   
            catch(Exception e){
                oCon.Desconectar();
            }
            if(dt.getRowCount()>0){
                oUsuario.SetApellido((String)dt.getValueAt(0, 2));
                oUsuario.SetNombre((String)dt.getValueAt(0, 1));
                oUsuario.setEmail(email);
                oUsuario.setPass(pass);
                oUsuario.SetId((Integer)dt.getValueAt(0, 1));
                oUsuario.SetTelefono((String)dt.getValueAt(0, 4));
                }

        }
        return oUsuario;
    }

    public static void main(String[] args) {

    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
