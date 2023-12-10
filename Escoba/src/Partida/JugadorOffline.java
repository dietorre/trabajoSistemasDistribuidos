package Partida;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class JugadorOffline implements Jugador {
    protected List<Carta> mano;
    protected List<Carta> cartasRobadas;
    protected int escobas;
    public String nombre;

    public JugadorOffline(String nombre) {
        escobas = 0;
        cartasRobadas = new ArrayList<Carta>();
        mano = new ArrayList<Carta>();
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    @Override
    public Accion jugarTurno(List<Carta> cartasEnMesa){
        try{
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println();
            System.out.println("TU TURNO:");
            mostrarMano();
            mostrarMesa(cartasEnMesa);
            while(true){
                try {
                    System.out.println();
                    System.out.println("Elige la carta a jugar: ");
                    int i = Integer.parseInt(teclado.readLine());
                    Carta jugada = mano.get(i);
                    System.out.println("Has elegido: " + mano.get(i));
                    System.out.println("Elige las cartas a robar:   (linea en blanco para terminar)" );
                    String leido = teclado.readLine();
                    if(leido.isBlank()){
                        i = -1;
                    }
                    else{
                        i = Integer.parseInt(leido);
                    }
                    
                    List<Carta> robadas = new ArrayList<>();

                    while(i != -1){
                        if(robadas.indexOf(cartasEnMesa.get(i)) == -1){
                            robadas.add(cartasEnMesa.get(i));
                            cartasRobadas.add(cartasEnMesa.get(i));
                            cartasRobadas.add(jugada);
                            System.out.println("Has elegido: " + cartasEnMesa.get(i) + " (linea en blanco para terminar)");
                        }
                        else{
                            System.out.println("Esa carta ya ha sido elegida");
                        }

                        leido = teclado.readLine();

                        if(leido.isBlank()){
                            i = -1;
                        }
                        else{
                            i = Integer.parseInt(leido);
                        }
                    }

                    boolean escoba = false;

                    if((robadas.size() > 0) && (cartasEnMesa.size() == robadas.size())){
                        escobas ++;
                        System.out.println("¡ESCOBA!");
                        escoba = true;
                    }

                    Accion a = new Accion(jugada, robadas, escoba);

                    mano.remove(jugada);
                    
                    System.out.println("TURNO TERMINADO");
                    return a;
                } catch (JugadaIncorrectaException e) {
                    System.out.println("La suma de las cartas elegidas no es 15, es "+ e.getSumaCartas()); 
                    mostrarMano();
                    mostrarMesa(cartasEnMesa);
                } catch(NumberFormatException e ){
                    System.out.println("Valor inválido");
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Valor inválido");
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
        System.out.println();
        System.out.println("Cartas en la mesa:");
        System.out.println(Carta.mostrarCartas(cartasEnMesa));
    }

    @Override
    public void mostrarMano(){
        System.out.println();
        System.out.println("Cartas en mano:");
        System.out.println(Carta.mostrarCartas(mano));
    }

    @Override
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    @Override
    public List<Carta> getCartasRobadas() {
        return cartasRobadas;
    }

    @Override
    public int getEscobas() {
        return escobas;
    }

    @Override
    public void setEscobas(int e) {
        escobas = e;
    }

    @Override
    public int getNumeroCartas() {
        return cartasRobadas.size();
    }

    @Override
    public int getOros() {
        int oros=0;
        for (Carta carta : cartasRobadas) {
            if(carta.getPalo() == Palo.Oros){
                oros++;
            }
        }
        return oros;
    }

    @Override
    public int getSietes() {
        int sietes = 0;
        for (Carta carta : cartasRobadas) {
            if(carta.getNumero()==7){
                sietes++;
            }
        }
        return sietes;
    }

    @Override
    public boolean sieteVelo() {
        return cartasRobadas.indexOf(new Carta(7,Palo.Oros)) != -1;
    }

    @Override
    public void nuevaRonda() {
        this.cartasRobadas.clear();
        this.setEscobas(0);
        this.mano.clear();
    }

    @Override
    public void terminarPartida() {
    }

    public void principioTurnoOtroJugador(Jugador j){
        System.out.println();
        System.out.println("Turno de " + j.getNombre() + "...");
    }

    @Override
    public void finalTurnoOtroJugador(Jugador j, Accion a) {
        System.out.println();
        System.out.println("El jugador "+ j.getNombre() + " ha usado la carta:");
        System.out.println(a.getCartaJugada());
        if(a.getCartasRobadas().size() > 0){
            System.out.println("Y ha cogido las cartas:");
            for (Carta carta : a.getCartasRobadas()) {
                System.out.print(carta.toString() + "   ");
            }
            System.out.println();
        }
        if(a.isEscoba()){
            System.out.println("¡ESCOBA!");
        }
    }

    public String toString(){
        return this.nombre;
    }

    @Override
    public void finalRonda(Dictionary<Jugador, Integer> puntuaciones) {
        System.out.println();
        System.out.println("Fin de la ronda");
        System.out.println("Puntuaciones actuales:");
        Enumeration<Jugador> en = puntuaciones.keys();
        while (en.hasMoreElements()){
            Jugador j = en.nextElement();
            System.out.println("El jugador " + j + " tiene " + puntuaciones.get(j) +  " puntos");
        }
    }

}
