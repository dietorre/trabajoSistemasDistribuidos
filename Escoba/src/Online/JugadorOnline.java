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

public class JugadorOnline implements Jugador{
    
    private Socket s;
    private BufferedReader in;
    private BufferedWriter out;

    public JugadorOnline(Socket s){
        try {
            this.s = s;
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void sendMensaje(String mensaje){
        try {
            out.write(mensaje);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cogerCarta(Carta c){
        String mensaje = "cogerCarta:";
        mensaje += c.toMensaje();
        sendMensaje(mensaje);
    }


    public void robarCarta(Carta c) {
        String mensaje = "robarCarta:";
        mensaje += c.toMensaje();
        sendMensaje(mensaje);
    }

    public Accion jugarTurno(List<Carta> cartasMesa) throws JugadaIncorrectaException{
        String mensaje = "jugarTurno:";
        for (Carta c : cartasMesa) {
            mensaje += c.toMensaje();
            if(cartasMesa.indexOf(c) != cartasMesa.size()-1){
                mensaje += ":";
            }   
        }

        sendMensaje(mensaje);

        try {
            String respuesta = in.readLine();
            return Accion.fromMensaje(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        
    }

    @Override
    public void mostrarMesa(List<Carta> cartasMesa) {
        String mensaje = "mostrarMesa:";
        for (Carta c : cartasMesa) {
            mensaje += c.toMensaje();
            if(cartasMesa.indexOf(c) != cartasMesa.size()-1){
                mensaje += ":";
            }   
        }
        sendMensaje(mensaje);
    }

    @Override
    public void mostrarMano() {
        String mensaje = "mostrarMano:";
        sendMensaje(mensaje);
    }

    @Override
    public List<Carta> getCartasRobadas() {
        List<Carta> resultado = new ArrayList<>();
        String mensaje = "getCartasRobadas:";
        sendMensaje(mensaje);
        try {
            String mensajeRecibido = in.readLine();
            for (String carta : mensajeRecibido.split(":")) {
                resultado.add(Carta.cartaFromText(carta));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;

        
    }

    @Override
    public int getEscobas() {
        String mensaje = "getEscobas:";
        sendMensaje(mensaje);
        try {
            String respuesta = in.readLine();
            return Integer.parseInt(respuesta);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setEscobas(int e) {
        String mensaje = "setEscobas:" + e;
        sendMensaje(mensaje);
    }

    @Override
    public int getNumeroCartas() {
        String mensaje = "getNumeroCartas:";
        sendMensaje(mensaje);
        try {
            String respuesta = in.readLine();
            return Integer.parseInt(respuesta);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getOros() {
        String mensaje = "getOros:";
        sendMensaje(mensaje);
        try {
            String respuesta = in.readLine();
            return Integer.parseInt(respuesta);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getSietes() {
        String mensaje = "getSietes:";
        sendMensaje(mensaje);
        try {
            String respuesta = in.readLine();
            return Integer.parseInt(respuesta);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getNombre() {
        String mensaje = "getNombre:";
        sendMensaje(mensaje);
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean sieteVelo() {
        String mensaje = "getSieteVelo:";
        sendMensaje(mensaje);
        try {
            String respuesta = in.readLine();
            return Boolean.parseBoolean(respuesta);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void nuevaRonda(){
        String mensaje = "nuevaRonda:";
        sendMensaje(mensaje);
    }

    @Override
    public void terminarPartida() {
        String mensaje = "terminarPartida:";
        sendMensaje(mensaje);
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnoOtroJugador(Jugador j, Accion a) {
        String mensaje = "turnoOtroJugador:";
        mensaje += j.getNombre();
        mensaje += ":";
        mensaje += a.toMensage();
        sendMensaje(mensaje);
    }

    

    
}
