package CapaNegocios;

public class ReservasEstados {
    private int reser_est;
    private String descripción;
    private int borrado;

    public int getReser_est() {
        return reser_est;
    }

    public void setReser_est(int reser_est) {
        this.reser_est = reser_est;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public void setDescripcion(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
