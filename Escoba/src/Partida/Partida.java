package Partida;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Partida {
    private List<Jugador> jugadores;
    private Mesa mesa;
    private boolean ultimas;
    private Dictionary<Jugador,Integer> puntuaciones;

    public Partida(List<Jugador> jugadores, Mesa mesa) {
        ultimas = false;
        this.jugadores = jugadores;
        this.mesa = mesa;
        puntuaciones = new Hashtable<>();
        for (Jugador jugador : jugadores) {
            puntuaciones.put(jugador, 0);
        }
    }

    public void jugarPartida(){
        try {

            for(int i = 0;i<34;i++){
                mesa.sacarCarta();
            }
            
            Jugador ultimoJugadorRobado = jugadores.get(0);

            while(!ultimas){
                System.out.println("Repartiendo cartas...");
                int cartasRepartidas = repartir();
        
                for(int i=0;i<cartasRepartidas;i++){
                    for (Jugador jugador : jugadores) {
                        System.out.println("Turno del jugador " + jugador.getNombre() + ":");
                        Accion a = jugador.jugarTurno(mesa.getCartasMesa());
                        mesa.ejecutarAccion(a);
                        if(a.getCartasRobadas().size() > 0){
                            ultimoJugadorRobado = jugador;
                        }
                    }
                }
            }

            for (Carta carta:mesa.getCartasMesa()) {
                ultimoJugadorRobado.robarCarta(carta);
            }

            addPuntos();

            System.out.println(puntuaciones);
            

        } catch (NoMasCartasException e) {
            e.printStackTrace();
        } catch (JugadaIncorrectaException e) {
            e.printStackTrace();
        }
    }

    public int repartir(){
        int cartasARepartir;
        if(mesa.cartasRestantes() >= 3*jugadores.size()){
            cartasARepartir = 3;
        }
        else{
            cartasARepartir = mesa.cartasRestantes()/jugadores.size();
            ultimas = true;
        }
        for (Jugador jugador : jugadores) {
            try {
                for(int i=0;i<cartasARepartir;i++){
                    jugador.cogerCarta(mesa.robarCarta());
                }
            } catch (NoMasCartasException e) {
                e.printStackTrace();
            }
        }
        return cartasARepartir;
    }

    private void addPuntos(){
        Jugador masOros = getJugadorMasOros();
        Jugador masSietes = getJugadorMasSietes();
        Jugador masCartas = getJugadorMasCartas();
        if(masOros != null){
            puntuaciones.put(masOros, puntuaciones.get(masOros) + 1);
        }
        if(masSietes != null){
            puntuaciones.put(masSietes, puntuaciones.get(masSietes) + 1);
        }
        if(masCartas != null){
            puntuaciones.put(masCartas, puntuaciones.get(masCartas) + 1);
        }

        for (Jugador jugador : jugadores) {
            puntuaciones.put(jugador, puntuaciones.get(jugador) + jugador.getEscobas());
        }

    }

    private Jugador getJugadorMasOros(){

        if(jugadores.size()==0){
            return null;
        }

        Jugador masOros = jugadores.get(0);

        for(int i=1;i<jugadores.size();i++){
            if(jugadores.get(i).getOros() > masOros.getOros()){
                masOros = jugadores.get(i);
            }
            if(jugadores.get(i).getOros() == masOros.getOros()){
                return null;
            }
        }

        return masOros;

    }

    private Jugador getJugadorMasSietes(){
        
        if(jugadores.size()==0){
            return null;
        }

        Jugador masSietes = jugadores.get(0);

        for(int i=1;i<jugadores.size();i++){
            if(jugadores.get(i).getSietes() > masSietes.getSietes()){
                masSietes = jugadores.get(i);
            }
            if(jugadores.get(i).getSietes() == masSietes.getSietes()){
                return null;
            }
        }

        return masSietes;

    }
    
    private Jugador getJugadorMasCartas(){
        
        if(jugadores.size()==0){
            return null;
        }

        Jugador masCartas = jugadores.get(0);

        for(int i=1;i<jugadores.size();i++){
            if(jugadores.get(i).getNumeroCartas() > masCartas.getNumeroCartas()){
                masCartas = jugadores.get(i);
            }
            if(jugadores.get(i).getNumeroCartas() == masCartas.getNumeroCartas()){
                return null;
            }
        }

        return masCartas;

    }
    
}
