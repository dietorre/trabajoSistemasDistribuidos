package Partida;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public abstract class JugadorIA extends JugadorOffline {

    public JugadorIA(String nombre) {
        super(nombre);
    }

    public abstract Accion jugarTurno(List<Carta> cartasEnMesa);

    protected List<Accion> jugadasPosibles(List<Carta> cartasEnMesa){
        List<Accion> resultado = new ArrayList<>();
        for(List<Carta> posibleAccion : jugadasTotales(cartasEnMesa)){
            for (Carta cartaEnMano:this.mano) {
                try{
                    List<Carta> cartasRestantes = new ArrayList<>();
                    cartasRestantes.addAll(cartasEnMesa);
                    cartasRestantes.removeAll(posibleAccion);
                    Accion a = new Accion(cartaEnMano, posibleAccion, posibleAccion.size() !=0 && (posibleAccion.size() == cartasEnMesa.size()),cartasRestantes);
                    resultado.add(a);

                }catch(JugadaIncorrectaException e){

                }
            }
        }
        // System.out.println(resultado);
        return resultado;
    }

    

    private List<List<Carta>> jugadasTotales(List<Carta> cartasEnMesa){
        List<List<Carta>> resultado = new ArrayList<>();
        int n = cartasEnMesa.size();
        for (int i = 0; i < Math.pow(2, n); i++) {
           List<Carta> cartasElegidas = new ArrayList<>(); 
            for (int j = 0; j< cartasEnMesa.size();j++) {
                
                if((i & (int)Math.pow(2, j)) != 0){
                    cartasElegidas.add(cartasEnMesa.get(j));
                }
                
            }
            resultado.add(cartasElegidas);
        }
        return resultado;
    }

    @Override
    public void mostrarMesa(List<Carta> cartasEnMesa){
        
    }

    @Override
    public void mostrarMano(){
       
    }
    public void principioTurnoOtroJugador(Jugador j){
       
    }

    @Override
    public void finalTurnoOtroJugador(Jugador j, Accion a) {
        
    }

    @Override
    public void finalRonda(Dictionary<Jugador, Integer> puntuaciones) {
        
    }

}
