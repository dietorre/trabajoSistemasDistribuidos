import java.util.List;

public class Accion {
    private Carta cartaJugada;
    private List<Carta> cartasRobadas;


    public Accion(Carta cartaJugada, List<Carta> cartasRobadas) throws JugadaIncorrectaException {
        int sumaCartas = 0;
        sumaCartas += cartaJugada.getNumero();
        for(Carta c:cartasRobadas){
            sumaCartas += c.getNumero();
        }
        if(sumaCartas== 15){
        this.cartaJugada = cartaJugada;
        this.cartasRobadas = cartasRobadas;
        }
        else{
            throw new JugadaIncorrectaException();
        }
    }
    

    public String toString() {
        return "{" +
            " cartaJugada='" + cartaJugada + "'" +
            ", cartasRobadas='" + Carta.mostrarCartas(cartasRobadas) + "'" +
            "}";
    }

}
