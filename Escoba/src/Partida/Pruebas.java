package Partida;
public class Pruebas {
    public static void main(String[] args) {
        Baraja b = new Baraja();
        Mesa m = new Mesa(b);
        try {
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
            m.sacarCarta();
        } catch (NoMasCartasException e) {
            e.printStackTrace();
        }
        Jugador j = new Jugador();
        try {
            j.robarCarta(m.robarCarta());
            j.robarCarta(m.robarCarta());
            j.robarCarta(m.robarCarta());
        } catch (NoMasCartasException e) {
            e.printStackTrace();
        }
        Accion a = j.jugarTurno(m.getCartasMesa());
        System.out.println(a);


        // Carta c1 = new Carta(1,Palo.Oros);
        // Carta c2 = new Carta(1,Palo.Bastos);
        // System.out.println(c1.equals(c2));
    }
}
