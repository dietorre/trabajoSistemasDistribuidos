package Partida;

import java.util.ArrayList;
import java.util.List;

public class Pruebas {
    public static void main(String[] args) {
        Baraja b = new Baraja();
        Mesa m = new Mesa(b);
        Jugador j1 = new JugadorOffline("Jugador 1");
        Jugador j2 = new JugadorOffline("Jugador 2");
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(j1);
        jugadores.add(j2);
        Partida p = new Partida(jugadores, m);
        p.jugarPartida();
    }
}
