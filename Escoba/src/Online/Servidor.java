package Online;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Partida.*;

public class Servidor {
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(55555)){
            int numeroJugadores = 2;
            List<Jugador> jugadores = new ArrayList<Jugador>();
            List<Socket> sockets = new ArrayList<>();
            for(int i=0;i<numeroJugadores;i++){
                try{
                    Socket s = ss.accept();
                    sockets.add(s);
                    JugadorOnline j = new JugadorOnline(s);
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
