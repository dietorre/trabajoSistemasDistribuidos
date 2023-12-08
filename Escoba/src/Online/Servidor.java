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
        try(ServerSocket ss = new ServerSocket(55555);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){
            int numeroJugadores = 0;
            while(!(numeroJugadores > 0)){
                System.out.println("Introduce el número de jugadores:");
                String leido = teclado.readLine();
                try{
                    numeroJugadores = Integer.parseInt(leido);
                    if(!(numeroJugadores > 0)){
                        System.out.println("Número no válido de jugadores");
                    }
                }catch(NumberFormatException e){
                    System.out.println("Entrada no válida");
                }
                
            }
            List<Jugador> jugadores = new ArrayList<Jugador>();
            List<Socket> sockets = new ArrayList<>();
            System.out.println("Esperando jugadores...");
            for(int i=0;i<numeroJugadores;i++){
                try{
                    Socket s = ss.accept();
                    sockets.add(s);
                    JugadorOnline j = new JugadorOnline(s);
                    System.out.println("Jugador " + j.getNombre() + " conectado");
                    jugadores.add(j);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            Baraja b = new Baraja();
            Mesa m = new Mesa(b);

            Partida p = new Partida(jugadores, m);

            p.jugarPartida();
            
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
