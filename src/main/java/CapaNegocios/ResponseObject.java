package CapaNegocios;

import javax.swing.JTable;

public class ResponseObject {
    private String salida;
    private int codigoSalida;
    private JTable JTResultado;
    
            
    public ResponseObject(String salida, int codigoSalida){
        this.salida = salida;
        this.codigoSalida = codigoSalida;
    }
    
        public ResponseObject(String salida, int codigoSalida, JTable tabla){
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
}
