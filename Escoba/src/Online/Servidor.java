package Online;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Partida.*;

public class Servidor {
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(55555)){
            try( Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))){
                
                JugadorOnline j1 = new JugadorOnline(in,out);

                Baraja b = new Baraja();
                Mesa m = new Mesa(b);
                List<Jugador> jugadores = new ArrayList<Jugador>();
                jugadores.add(j1);
                Partida p = new Partida(jugadores, m);

                p.jugarPartida();
                
        }catch (IOException e) {
            e.printStackTrace();
        }
    }catch(IOException e){
        e.printStackTrace();
    }
}
}
