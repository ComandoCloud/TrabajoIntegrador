package CapaNegocios;

public class ResponseObject {
    private String salida;
    private int codigoSalida;
   
    public ResponseObject(String salida, int codigoSalida){
        this.salida = salida;
        this.codigoSalida = codigoSalida;
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
