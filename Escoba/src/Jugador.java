import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private List<Carta> mano;

    public Jugador() {
        mano = new ArrayList<Carta>();
    }

    public void jugarTurno(List<Carta> cartasEnMesa){
        System.out.println("Cartas en la mesa:");
        System.out.println(Carta.mostrarCartas(cartasEnMesa));
        System.out.println("Cartas en mano:");
        System.out.println(Carta.mostrarCartas(mano));

    }

    public void robarCarta(Carta c){
        mano.add(c);
    }

}
