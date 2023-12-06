package Partida;

import java.util.Dictionary;
import java.util.List;

public interface Jugador {

    Accion jugarTurno(List<Carta> cartasEnMesa) throws JugadaIncorrectaException;

    void robarCarta(Carta c);

    void cogerCarta(Carta c);

    void mostrarMesa(List<Carta> cartasEnMesa);

    void mostrarMano();

    List<Carta> getCartasRobadas();

    int getEscobas();

    void setEscobas(int e);

    int getNumeroCartas();

    int getOros();

    int getSietes();

    String getNombre();

    boolean sieteVelo();

    void nuevaRonda();

    void finalRonda(Dictionary<Jugador,Integer> puntuaciones);

    void terminarPartida();

    void turnoOtroJugador(Jugador j, Accion a);

}