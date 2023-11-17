import java.util.ArrayList;
import java.util.List;

public class Accion {
    private Carta cartaJugada;
    private List<Carta> cartasRobadas;
    private int sumaCartas;

    public Accion(Carta cartaJugada, List<Carta> cartasRobadas) throws JugadaIncorrectaException {
        sumaCartas = 0;
        sumaCartas += cartaJugada.getNumero();
        for(Carta c:cartasRobadas){
            sumaCartas += c.getNumero();
        }
        if(sumaCartas== 15 || cartasRobadas.size() == 0){
            this.cartaJugada = cartaJugada;
            this.cartasRobadas = cartasRobadas;
        }
        else{
            throw new JugadaIncorrectaException(sumaCartas);
        }
    }


    public String toString() {
        return "{" +
            " cartaJugada='" + cartaJugada + "'" +
            ", cartasRobadas='" + Carta.mostrarCartas(cartasRobadas) + "'" +
            "}";
    }

    public String toMensage(){
        String mensaje = "";
        mensaje += cartaJugada.toMensaje();
        mensaje += ":";
        for (Carta carta : cartasRobadas) {
            mensaje += carta.toMensaje();
            if(cartasRobadas.indexOf(carta) != cartasRobadas.size() - 1){
                mensaje += ":";
            }
        }
        return mensaje;
    }

    public static Accion fromMensaje(String s) throws JugadaIncorrectaException{
        String[] stringCortada = s.split(":");
        Carta cartaJugada = Carta.cartaFromText(stringCortada[0]);
        List<Carta> cartasRobadas = new ArrayList<>();
        for(int i=1;i<stringCortada.length;i++){
            cartasRobadas.add(Carta.cartaFromText(stringCortada[i]));
        }
        return new Accion(cartaJugada, cartasRobadas);
    }

}
