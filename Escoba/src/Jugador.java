import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private List<Carta> mano;

    public Jugador() {
        mano = new ArrayList<Carta>();
    }

    public Accion jugarTurno(List<Carta> cartasEnMesa){
        try(BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Cartas en la mesa:");
            System.out.println(Carta.mostrarCartas(cartasEnMesa));
            System.out.println("Cartas en mano:");
            System.out.println(Carta.mostrarCartas(mano));
            while(true){
                System.out.println("Elige la carta a jugar: (Introduce la posición de la carta, la primera es la posición 0)");
                int i = Integer.parseInt(teclado.readLine());
                Carta jugada = mano.remove(i);
                System.out.println("Elige las carta a robar:");
                i = Integer.parseInt(teclado.readLine());
                List<Carta> robadas = new ArrayList<>();
                robadas.add(cartasEnMesa.get(i));
                try {
                    return new Accion(jugada, robadas);
                } catch (JugadaIncorrectaException e) {
                   System.out.println("La suma de las cartas no es 15."); 
                }

            }
        }catch(IOException e){
                e.printStackTrace();
                return null;
        }
    }

    public void robarCarta(Carta c){
        mano.add(c);
    }

}
