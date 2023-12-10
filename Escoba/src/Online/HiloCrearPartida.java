package Online;

public class HiloCrearPartida implements Runnable {

    private int puerto;
    private int numeroJugadores;
    private int numeroIA;

    public HiloCrearPartida(int puerto, int numeroJugadores, int numeroIA) {
        this.puerto = puerto;
        this.numeroIA = numeroIA;
        this.numeroJugadores = numeroJugadores;
    }

    @Override
    public void run() {
        try {
            Servidor.crearPartida(puerto, numeroJugadores, numeroIA);
        } catch (Exception e) {
            System.out.println("Error al crear partida");
        }
    }

}
