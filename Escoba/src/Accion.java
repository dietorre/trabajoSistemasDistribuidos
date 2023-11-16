import java.util.List;

public class Accion {
    private Carta cartaJugada;
    private List<Carta> cartasRobadas;


    public Accion(Carta cartaJugada, List<Carta> cartasRobadas) {
        this.cartaJugada = cartaJugada;
        this.cartasRobadas = cartasRobadas;
    }
    

    @Override
    public String toString() {
        return "{" +
            " cartaJugada='" + cartaJugada + "'" +
            ", cartasRobadas='" + Carta.mostrarCartas(cartasRobadas) + "'" +
            "}";
    }

}
