package Partida;

import java.util.List;

public class Partida {
    private List<Jugador> jugadores;
    private Mesa mesa;
    private boolean ultimas;

    public Partida(List<Jugador> jugadores, Mesa mesa) {
        ultimas = false;
        this.jugadores = jugadores;
        this.mesa = mesa;
    }

    public void jugarPartida(){
        try {

            for(int i = 0;i<4;i++){
                mesa.sacarCarta();
            }
            
            Jugador ultimoJugadorRobado = jugadores.get(0);

            while(!ultimas){
                System.out.println("Repartiendo cartas...");
                int cartasRepartidas = repartir();
        
                for(int i=0;i<cartasRepartidas;i++){
                    for (Jugador jugador : jugadores) {
                        System.out.println("Turno del jugador " + jugadores.indexOf(jugador) +":");
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
}
