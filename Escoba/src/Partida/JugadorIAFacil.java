package Partida;

import java.util.List;

public class JugadorIAFacil extends JugadorIA{

    public JugadorIAFacil(String nombre) {
        super(nombre);
    }

    public Accion jugarTurno(List<Carta> cartasEnMesa){
        List<Accion> accionesPosibles = jugadasPosibles(cartasEnMesa);
        Accion a = accionesPosibles.get(accionesPosibles.size()-1);
        this.cartasRobadas.addAll(a.getCartasRobadas());
        this.cartasRobadas.add(a.getCartaJugada());
        if(a.isEscoba()){
            escobas++;
        }
        mano.remove(a.getCartaJugada());
        return a;
    }

}
