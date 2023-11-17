package Partida;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private List<Carta> mano;

    public Jugador() {
        mano = new ArrayList<Carta>();
    }

    public Accion jugarTurno(List<Carta> cartasEnMesa){
        try(BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Cartas en mano:");
            System.out.println(Carta.mostrarCartas(mano));
            System.out.println("Cartas en la mesa:");
            System.out.println(Carta.mostrarCartas(cartasEnMesa));
            while(true){
                System.out.println("Elige la carta a jugar: ");
                int i = Integer.parseInt(teclado.readLine());
                Carta jugada = mano.get(i);
                System.out.println("Elige las cartas a robar:   (-1 para terminar)" );
                i = Integer.parseInt(teclado.readLine());
                List<Carta> robadas = new ArrayList<>();

                while(i != -1){
                    robadas.add(cartasEnMesa.get(i));
                    System.out.println("Has elegido: " + cartasEnMesa.get(i));
                    i = Integer.parseInt(teclado.readLine());
                }

                try {
                    Accion a = new Accion(jugada, robadas);
                    mano.remove(jugada);
                    return a;
                } catch (JugadaIncorrectaException e) {
                   System.out.println("La suma de las cartas elegidas no es 15, es "+ e.getSumaCartas()); 
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
