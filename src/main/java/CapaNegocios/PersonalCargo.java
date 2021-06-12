package CapaNegocios;
import CapaDatos.Conexion;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class PersonalCargo {
    	private int Id;
	private String Descripcion;
	private int Borrado;
        
        public PersonalCargo(){
            comenzarCarga();
        }
        
        public PersonalCargo(int Id,String Descripcion){
            this.Id = Id;
            this.Descripcion = Descripcion;
            comenzarCarga();
        }
        
        private void comenzarCarga(){
            cargarDatos();
        }
        
        private void cargarDatos(){
            asignarDatos();
        }
        
        private void asignarDatos(){
            
        }
        
        public String getCargo(){
            return this.Descripcion;
        }
        
        public void setCargo(String descripcion){
            this.Descripcion = descripcion;
        }
        
        public int getIdCargo(){
            return this.Id;
        }
        
        public void setIdCargo(int id){
            this.Id = id;
        }
        
            
    public DefaultTableModel Listar() throws SQLException, InterruptedException
    {
        DefaultTableModel dt = new DefaultTableModel();
        Conexion oCon = new Conexion();          
        oCon.Conectar();
        oCon.CrearComando("SELECT * FROM personal_cargos where borrado= ? ");
        oCon.comando.setString(1, "0");
        oCon.EjecutarComando();
        dt = oCon.Tabla();
        oCon.Desconectar();
        return dt;
    }
    
    @Override
    public String toString(){
        return this.Descripcion;
    }
        
        
}
