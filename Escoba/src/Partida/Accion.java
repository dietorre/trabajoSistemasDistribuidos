package Partida;
import java.util.ArrayList;
import java.util.List;

public class Accion {
    private Carta cartaJugada;
    private List<Carta> cartasRobadas;
    private int sumaCartas;
    private boolean escoba;
    private List<Carta> cartasRestantes;

    public Accion(Carta cartaJugada, List<Carta> cartasRobadas, boolean escoba) throws JugadaIncorrectaException {
        this.escoba = escoba;
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

    public Accion(Carta cartaJugada, List<Carta> cartasRobadas, boolean escoba, List<Carta> cartasRestantes) throws JugadaIncorrectaException {
        this.escoba = escoba;
        this.cartasRestantes = cartasRestantes;
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


    public List<Carta> getCartasRestantes(){
        return cartasRestantes;
    } 

    public Carta getCartaJugada() {
        return this.cartaJugada;
    }

    public List<Carta> getCartasRobadas() {
        return this.cartasRobadas;
    }

    public boolean isEscoba(){
        return escoba;
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
            mensaje += ":";
        }
        mensaje += Boolean.toString(escoba);

        return mensaje;
    }

    public static Accion fromMensaje(String s) throws JugadaIncorrectaException{
        String[] stringCortada = s.split(":");
        Carta cartaJugada = Carta.cartaFromText(stringCortada[0]);
        List<Carta> cartasRobadas = new ArrayList<>();
        for(int i=1;i<stringCortada.length-1;i++){
            cartasRobadas.add(Carta.cartaFromText(stringCortada[i]));
        }
        boolean escoba = Boolean.parseBoolean(stringCortada[stringCortada.length-1]);
        return new Accion(cartaJugada, cartasRobadas,escoba);
    }

}
