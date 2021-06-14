package CapaNegocios;

public class ReservasEstados {

    private int id;
    private String descripcion;
    private int borrado;

    //ENUM DE POSIBLES ESTADOS DE UNA RESERVA
    public enum ReservaEstado {
        RESERVADA(1), LIBRE(2), CANCELADA(3), TERMINADO(4);
        private int estado;

        //Añadir un Constructor
        ReservaEstado(int s) {
            estado = s;
        }
        //Añadir un método

        public int getEstado() {
            return estado;
        }
    }

    public ReservasEstados(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public ReservasEstados() {

    }

    public int getReser_est() {
        return id;
    }

    public void setId(int reser_est) {
        this.id = reser_est;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

}
