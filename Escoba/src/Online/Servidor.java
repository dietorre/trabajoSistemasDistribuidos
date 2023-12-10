package Online;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Partida.*;

public class Servidor { 
    public static void main(String[] args) {
        try(BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
            int numeroJugadores = 0;
            int numeroIA = 0;
            while(numeroJugadores + numeroIA == 0){
                numeroJugadores = -1;
                while(!(numeroJugadores >= 0)){
                    System.out.println("Introduce el número de jugadores:");
                    String leido = teclado.readLine();
                    try{
                        numeroJugadores = Integer.parseInt(leido);
                        if(!(numeroJugadores >= 0)){
                            System.out.println("Número no válido de jugadores");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Entrada no válida");
                    }
                    
                }

                numeroIA = -1;
                while(!(numeroIA >= 0)){
                    System.out.println("Introduce el número de bots:");
                    String leido = teclado.readLine();
                    try{
                        numeroIA = Integer.parseInt(leido);
                        if(!(numeroIA >= 0)){
                            System.out.println("Número no válido de bots");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Entrada no válida");
                    }
                }
                if(numeroJugadores + numeroIA == 0 ){
                    System.out.println("El número total de jugadores debe ser mayor que 0");
                }
            }
            int puerto = 0;
            while(!(puerto > 0)){
                System.out.println("Introduce el puerto en el que crear  la partida:");
                String leido = teclado.readLine();
                try{
                    puerto = Integer.parseInt(leido);
                    if(!(puerto > 0)){
                        System.out.println("Número de puerto no válido");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Entrada no válida");
                }
            }

            crearPartida(puerto, numeroJugadores, numeroIA);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    public static void crearPartida(int puerto, int nJugadores, int nIA) throws IOException {
        try (ServerSocket ss = new ServerSocket(puerto)) {
            List<Jugador> jugadores = new ArrayList<Jugador>();
            System.out.println("Esperando jugadores...");
            for(int i=0;i<nJugadores;i++){
                try{
                    Socket s = ss.accept();
                    JugadorOnline j = new JugadorOnline(s);
                    System.out.println("Jugador " + j.getNombre() + " conectado");
                    jugadores.add(j);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < nIA; i++) {
                JugadorIA j = new JugadorIANormal("IA "+ (i+1));
                jugadores.add(j);
            }

            Baraja b = new Baraja();
            Mesa m = new Mesa(b);

            Partida p = new Partida(jugadores, m);

            p.jugarPartida();
        }
            
        
    }


}
