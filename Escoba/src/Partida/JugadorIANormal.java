package Partida;

import java.util.List;

public class JugadorIANormal extends JugadorIA{

    public JugadorIANormal(String nombre) {
        super(nombre);
    }

    public Accion jugarTurno(List<Carta> cartasEnMesa){
        // System.out.println();
        mostrarMano();
        mostrarMesa(cartasEnMesa);
        List<Accion> accionesPosibles = jugadasPosibles(cartasEnMesa);
        Accion a = accionesPosibles.get(0);
        for (Accion accion : accionesPosibles) {
            if(puntuarAccion(accion) > puntuarAccion(a)){
                a = accion;
            }
        }
        
        this.cartasRobadas.addAll(a.getCartasRobadas());
        if(a.isEscoba()){
            escobas++;
        }
        this.cartasRobadas.add(a.getCartaJugada());
        mano.remove(a.getCartaJugada());
        return a;
    }

    private int puntuarAccion(Accion a){
        if(a.getCartasRobadas().size() > 0){
            int valor = 0;
            for (Carta carta : a.getCartasRobadas()) {
                valor ++;
                if(carta.getPalo() == Palo.Oros){
                    valor++;
                }
                if(carta.getNumero() == 7){
                    valor ++;
                }
                if(a.isEscoba()){
                    valor = 999;
                }
            }
            return valor;
        }else{
            int valor = 0;
            if(a.getCartaJugada().getPalo() == Palo.Oros){
                valor -= 5;
            }
            if(a.getCartaJugada().getNumero() == 7){
                valor -=10;
            }
            int sumaRestantes = 0;
            for (Carta carta : a.getCartasRestantes()) {
                sumaRestantes += carta.getNumero();
            }
            if(sumaRestantes < 15){
                valor -= 10;
            }
            return valor;
        }
        
    }

    // private int puntuarAccion(Accion a){
    //     if(a.getCartasRobadas().size() > 0){
    //         int n=a.getCartasRobadas().size();

    //         List<Carta> cartasTotales = new ArrayList<>();
    //         for (Carta carta : a.getCartasRobadas()) {
    //             cartasTotales.add(carta);
    //         }

    //         cartasTotales.add(a.getCartaJugada());

    //         if(cartasTotales.contains(new Carta(7,Palo.Oros))){
    //             n += 20;
    //         }

    //         for (Carta carta : cartasTotales) {
    //             if(carta.getPalo() == Palo.Oros){
    //                 n += 5;
    //             }
    //             if(carta.getNumero() == 7){
    //                 n += 10;
    //             }
    //         }

    //         return n;
    //     }
    //     else{
    //         return - a.getCartaJugada().getNumero();
    //     }
    // }

}
