package Online;
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket; 
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import Partida.*;

public class Cliente {
    public static void main(String[] args) {
        try(Socket s = new Socket("localhost",55555);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))){

            System.out.println("Introduce el nombre del jugador:");
            String nombre = teclado.readLine();
            
            Jugador jugador = new JugadorOffline(nombre);
            
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

                    case "getEscobas":
                        out.write(String.valueOf(jugador.getEscobas()));
                        out.newLine();
                        out.flush();
                        break;

                    case "setEscobas":
                        jugador.setEscobas(Integer.parseInt(textoPartido[1]));
                        break;
                    case "getNumeroCartas":
                        out.write(String.valueOf(jugador.getNumeroCartas()));
                        out.newLine();
                        out.flush();
                        break;
                    
                    case "getOros":
                        out.write(String.valueOf(jugador.getOros()));
                        out.newLine();
                        out.flush();
                        break;
                    case "getSietes":
                        out.write(String.valueOf(jugador.getSietes()));
                        out.newLine();
                        out.flush();
                        break;
                    case "getSieteVelo":
                        out.write(String.valueOf(jugador.sieteVelo()));
                        out.newLine();
                        out.flush();
                        break;    
                    case "nuevaRonda":
                        jugador.nuevaRonda();
                        break;

                    case "finalRonda":
                        Dictionary<Jugador, Integer> puntuaciones = new Hashtable<>();
                        for(int i=1;i<textoPartido.length;i+=2){
                            puntuaciones.put(new JugadorOffline(textoPartido[i]), Integer.parseInt(textoPartido[i+1]));
                        }
                        jugador.finalRonda(puntuaciones);
                        break;
                    
                    case "terminarPartida":
                        partidaTerminada = true;
                        s.close();
                        break;
                    case "principioTurnoOtroJugador":
                        Jugador j = new JugadorOffline(textoPartido[1]);
                        jugador.principioTurnoOtroJugador(j);
                        break;
                    case "finalTurnoOtroJugador":
                        j = new JugadorOffline(textoPartido[1]);
                        Accion a = Accion.fromMensaje(mensajeRecibido.substring(textoPartido[0].length()+textoPartido[1].length()+2));
                        jugador.finalTurnoOtroJugador(j, a);
                        break;
                    default:
                        break;
                }

            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (JugadaIncorrectaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
