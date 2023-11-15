public class Pruebas {
    public static void main(String[] args) {
        Baraja b = new Baraja();
        Mesa m = new Mesa(b);
        try {
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
        j.jugarTurno(m.getCartasMesa());
    }
}
