package Partida;

import java.util.ArrayList;
import java.util.List;

public class Pruebas {
    // public static void main(String[] args) {
    //     Baraja b = new Baraja();
    //     Mesa m = new Mesa(b);
    //     Jugador j1 = new JugadorOffline("Jugador 1");
    //     Jugador j2 = new JugadorOffline("Jugador 2");
    //     List<Jugador> jugadores = new ArrayList<Jugador>();
    //     jugadores.add(j1);
    //     jugadores.add(j2);
    //     Partida p = new Partida(jugadores, m);
    //     p.jugarPartida();
    // }

    public static void main(String[] args) {
        Baraja b = new Baraja();
        Mesa m = new Mesa(b);
        Jugador ia = new JugadorIA("jIA");
        Jugador ia2 = new JugadorIA("jIA2");
        Jugador ia3 = new JugadorIA("jIA3");
        Jugador ia4 = new JugadorIA("jIA4");
        // Jugador yo = new JugadorOffline("yo");
        List<Jugador> jugadores = new ArrayList<Jugador>();
        // jugadores.add(yo);
        jugadores.add(ia);
        jugadores.add(ia2);
        jugadores.add(ia3);
        jugadores.add(ia4);
        Partida p = new Partida(jugadores, m);
        p.jugarPartida();

    }
}
