package CapaNegocios;

import javax.swing.table.DefaultTableModel;

//CLASE USADA PARA LA DEVUCION DE OTROS METODOS DE OTRAS CLASES, COONTIENE VARIOS ATRIBUTOS DE 
//VARIOS TIPOS DISTINTOS PARA QUE SE ADAPTE A DIFERENTES CIRCUNSTANCIAS
public class ResponseObject {

    private String salida;
    private int codigoSalida;
    private DefaultTableModel jTResultado;

    public ResponseObject(String salida, int codigoSalida) {
        this.salida = salida;
        this.codigoSalida = codigoSalida;
    }

    public ResponseObject(String salida, int codigoSalida, DefaultTableModel tabla) {
        this.salida = salida;
        this.codigoSalida = codigoSalida;
        this.jTResultado = tabla;
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

    public DefaultTableModel getjTResultado() {
        return jTResultado;
    }

    public void setjTResultado(DefaultTableModel jTResultado) {
        this.jTResultado = jTResultado;
    }
}
