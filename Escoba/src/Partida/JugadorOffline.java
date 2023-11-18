package Partida;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JugadorOffline implements Jugador {
    private List<Carta> mano;
    private List<Carta> cartasRobadas;
    private int escobas;

    public JugadorOffline() {
        escobas = 0;
        cartasRobadas = new ArrayList<Carta>();
        mano = new ArrayList<Carta>();
    }

    @Override
    public Accion jugarTurno(List<Carta> cartasEnMesa){
        try{
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            mostrarMano();
            mostrarMesa(cartasEnMesa);
            while(true){
                System.out.println("Elige la carta a jugar: ");
                int i = Integer.parseInt(teclado.readLine());
                Carta jugada = mano.get(i);
                System.out.println("Has elegido: " + mano.get(i));
                System.out.println("Elige las cartas a robar:   (-1 para terminar)" );
                i = Integer.parseInt(teclado.readLine());
                List<Carta> robadas = new ArrayList<>();

                while(i != -1){
                    robadas.add(cartasEnMesa.get(i));
                    cartasRobadas.add(cartasEnMesa.get(i));

                    System.out.println("Has elegido: " + cartasEnMesa.get(i));
                    i = Integer.parseInt(teclado.readLine());
                }

                try {
                    Accion a = new Accion(jugada, robadas);
                    mano.remove(jugada);
                    if(cartasEnMesa.size() == robadas.size()){
                        escobas ++;
                    }
                    return a;
                } catch (JugadaIncorrectaException e) {
                    System.out.println("La suma de las cartas elegidas no es 15, es "+ e.getSumaCartas()); 
                    mostrarMano();
                    mostrarMesa(cartasEnMesa);
                }

            }
        }catch(IOException e){
                e.printStackTrace();
                return null;
        }
    }

    @Override
    public void robarCarta(Carta c){
        cartasRobadas.add(c);
    }

    @Override
    public void mostrarMesa(List<Carta> cartasEnMesa){
        System.out.println("Cartas en la mesa:");
        System.out.println(Carta.mostrarCartas(cartasEnMesa));
    }

    @Override
    public void mostrarMano(){
        System.out.println("Cartas en mano:");
        System.out.println(Carta.mostrarCartas(mano));
    }

    @Override
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    @Override
    public List<Carta> geCartasRobadas() {
        return cartasRobadas;
    }

}
