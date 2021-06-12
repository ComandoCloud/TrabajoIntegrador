package CapaNegocios;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResponseObject {
    private String salida;
    private int codigoSalida;
    private DefaultTableModel JTResultado;
    
            
    public ResponseObject(String salida, int codigoSalida){
        this.salida = salida;
        this.codigoSalida = codigoSalida;
    }
    
        public ResponseObject(String salida, int codigoSalida, DefaultTableModel tabla){
        this.salida = salida;
        this.codigoSalida = codigoSalida;
        this.JTResultado = tabla;
    }
         
    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public int getCodigoSalida() {
        return codigoSalida;
    }
    
    void setCodigoSalida(int codigoSalida) {
        this.codigoSalida = codigoSalida;
    }

    /**
     * @return the JTResultado
     */
    public DefaultTableModel getJTResultado() {
        return JTResultado;
    }

    /**
     * @param JTResultado the JTResultado to set
     */
    public void setJTResultado(DefaultTableModel JTResultado) {
        this.JTResultado = JTResultado;
    }
}
