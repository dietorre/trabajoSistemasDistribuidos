import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class JugadorOnline {
    
    BufferedReader in;
    BufferedWriter out;

    public JugadorOnline(BufferedReader in, BufferedWriter out){
        this.in = in;
        this.out = out;
    }

    public void robarCarta(Carta c){
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

    public Accion jugarTurno(List<Carta> cartasMesa) throws IOException, JugadaIncorrectaException{
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
    }
}
