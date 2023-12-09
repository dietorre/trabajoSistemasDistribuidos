package Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        // Baraja b = new Baraja();
        // Mesa m = new Mesa(b);
        // Jugador iaN = new JugadorIANormal("IA Normal");
        // Jugador iaF = new JugadorIAFacil("IA facil");
        // Jugador iaN2 = new JugadorIANormal("IA Normal2");
        // Jugador iaF2 = new JugadorIAFacil("IA facil2");

        // // Jugador yo = new JugadorOffline("yo");
        // List<Jugador> jugadores = new ArrayList<Jugador>();
        // // jugadores.add(yo);
        // jugadores.add(iaF);
        // jugadores.add(iaN);
        // jugadores.add(iaF2);
        // jugadores.add(iaN2);

        // int facil = 0;
        // int normal = 0;

        ExecutorService pool = Executors.newFixedThreadPool(12);

        List<Future<Integer>> resultados = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            resultados.add(pool.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    Baraja b = new Baraja();
                    Mesa m = new Mesa(b);
                    Jugador iaN = new JugadorIANormal("IA Normal");
                    Jugador iaF = new JugadorIAFacil("IA facil");
                    Jugador iaN2 = new JugadorIANormal("IA Normal");
                    Jugador iaF2 = new JugadorIAFacil("IA facil");
                    List<Jugador> jugadores = new ArrayList<Jugador>(); 
                    jugadores.add(iaF);
                    jugadores.add(iaN);
                    jugadores.add(iaF2);
                    jugadores.add(iaN2);
                    Partida p = new Partida(jugadores, m);
                    
                    p.jugarPartida();
                    if(p.jugadorMasPuntos().equals(iaN) || p.jugadorMasPuntos().equals(iaN2)){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
                
            }));
        }
        
        int normal = 0;

        for (Future<Integer> future : resultados) {
            try {
                normal += future.get();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println(normal);
        pool.shutdown();

        // for(int i=0;i<1000;i++){
        //     Partida p = new Partida(jugadores, m);
        //     p.jugarPartida();

        //     if(p.jugadorMasPuntos().equals(iaN) || p.jugadorMasPuntos().equals(iaN2)){
        //         normal++;
        //     }
        //     else{
        //         facil++;
        //     }
        // }

        // System.out.println("Fácil: " + facil);
        // System.out.println("Normal: " + normal);
        

    }
}
