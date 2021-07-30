
package CapaNegocios;

public class ReservaRun implements Runnable{            
    private ResponseObject resultado;
    private Reserva reserva;
    Thread hilo;

    public ReservaRun(Reserva oReserva){
        this.reserva = oReserva;
        //hilo= new Thread(this,"hiloReserva");
    }
    public ResponseObject getResultado() {
        return this.resultado;
    }
    
    public void setReserva(Reserva oReserva){
        this.reserva = oReserva;
    }
    
    @Override
    public void run(){
        try{
            this.resultado = this.reserva.Reservar();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hilo secundario finalizado.");

    }
}
