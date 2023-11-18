package Online;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import Partida.*; 

public class JugadorOnline implements Jugador{
    
    BufferedReader in;
    BufferedWriter out;

    public JugadorOnline(BufferedReader in, BufferedWriter out){
        this.in = in;
        this.out = out;
    }

    public void cogerCarta(Carta c){
        String mensaje = "";
        mensaje += "cogerCarta:";
        mensaje += c.toMensaje();
        try {
            out.write(mensaje);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void robarCarta(Carta c) {
        String mensaje = "";
        mensaje += "robarCarta:";
        mensaje += c.toMensaje();
        try {
            out.write(mensaje);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Accion jugarTurno(List<Carta> cartasMesa) throws JugadaIncorrectaException{
        try{
            String mensaje = "";
            mensaje += "jugarTurno:";
            for (Carta c : cartasMesa) {
                mensaje += c.toMensaje();
                if(cartasMesa.indexOf(c) != cartasMesa.size()-1){
                    mensaje += ":";
                }   
            }
            out.write(mensaje);
            out.newLine();
            out.flush();

            String respuesta = in.readLine();

            return Accion.fromMensaje(respuesta);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mostrarMesa(List<Carta> cartasEnMesa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarMesa'");
    }

    @Override
    public void mostrarMano() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarMano'");
    }

    @Override
    public List<Carta> geCartasRobadas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geCartasRobadas'");
    }
}
