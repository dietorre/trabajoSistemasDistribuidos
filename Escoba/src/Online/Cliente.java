package Online;
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket; 
import java.util.ArrayList; 
import java.util.List;

import Partida.*;

public class Cliente {
    public static void main(String[] args) {
        try(Socket s = new Socket("localhost",55555);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))){
            
            Jugador jugador = new JugadorOffline("Jugador 1");
            
            boolean partidaTerminada = false;
            while(!partidaTerminada){
                String mensajeRecibido =  in.readLine();
                // System.out.println(mensajeRecibido);
                String[] textoPartido = mensajeRecibido.split(":");

                switch (textoPartido[0]) {
                    case "robarCarta":
                        Carta c = Carta.cartaFromText(textoPartido[1]);
                        jugador.robarCarta(c);
                        break;
                    
                    case "cogerCarta":
                        c = Carta.cartaFromText(textoPartido[1]);
                        jugador.cogerCarta(c);
                        break;

                    case "jugarTurno":
                        List <Carta> cartasMesa = new ArrayList<>();
                        for(int i=1;i<textoPartido.length;i++){
                            cartasMesa.add(Carta.cartaFromText(textoPartido[i]));
                        }
                        try {
                            Accion a = jugador.jugarTurno(cartasMesa);
                            out.write(a.toMensage());
                            out.newLine();
                            out.flush();
                        } catch (JugadaIncorrectaException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "mostrarMesa":
                        cartasMesa = new ArrayList<>();
                        for(int i=1;i<textoPartido.length;i++){
                            cartasMesa.add(Carta.cartaFromText(textoPartido[i]));
                        }
                        jugador.mostrarMesa(cartasMesa);
                        break;
                    case "mostrarMano":
                        jugador.mostrarMano();
                        break;
                    
                    case "getCartasRobadas":
                        String mensaje = "";
                        for (Carta carta : jugador.getCartasRobadas()) {
                            mensaje += carta.toMensaje();
                            if(jugador.getCartasRobadas().indexOf(carta) != jugador.getCartasRobadas().size()-1){
                                mensaje += ":";
                            }
                        }
                        out.write(mensaje);
                        out.newLine();
                        out.flush();

                        break;

                    case "getNombre":
                        out.write(jugador.getNombre());
                        out.newLine();
                        out.flush();
                        break;
                    case "terminarPartida":
                        partidaTerminada = true;
                        break;

                    default:
                        break;
                }

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
