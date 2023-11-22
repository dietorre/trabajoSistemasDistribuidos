package Online;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Partida.*; 

public class JugadorOnline implements Jugador{
    
    BufferedReader in;
    BufferedWriter out;

    public JugadorOnline(BufferedReader in, BufferedWriter out){
        this.in = in;
        this.out = out;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEscobas'");
    }

    @Override
    public void setEscobas(int e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEscobas'");
    }

    @Override
    public int getNumeroCartas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumeroCartas'");
    }

    @Override
    public int getOros() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOros'");
    }

    @Override
    public int getSietes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSietes'");
    }

    @Override
    public String getNombre() {
        String mensaje = "getNombre:";
        sendMensaje(mensaje);
        try {
            return in.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean sieteVelo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sieteVelo'");
    }

    
}
