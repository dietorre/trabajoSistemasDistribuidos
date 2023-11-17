import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try(ServerSocket ss = new ServerSocket(55555)){
            try( Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))){
                
                JugadorOnline j = new JugadorOnline(in,out);

                Baraja b = new Baraja();
                Mesa m = new Mesa(b);
                try {
                    m.sacarCarta();
                    m.sacarCarta();
                    m.sacarCarta();
                    m.sacarCarta();
                } catch (NoMasCartasException e) {
                    e.printStackTrace();
                }

                j.robarCarta(m.robarCarta());
                j.robarCarta(m.robarCarta());
                j.robarCarta(m.robarCarta());

                j.jugarTurno(m.getCartasMesa());

                // String mensaje = "";
                // mensaje += "robarCarta:";
                // mensaje += m.robarCarta().toMensaje();
                // out.write(mensaje);
                // out.newLine();
                
                // mensaje = "";
                // mensaje += "robarCarta:";
                // mensaje += m.robarCarta().toMensaje();
                // out.write(mensaje);
                // out.newLine();

                // mensaje = "";
                // mensaje += "robarCarta:";
                // mensaje += m.robarCarta().toMensaje();
                // out.write(mensaje);
                // out.newLine();
                

                // mensaje = "";
                // mensaje += "jugarTurno:";
                // for (Carta c : m.getCartasMesa()) {
                //     mensaje += c.toMensaje();
                //     if(m.getCartasMesa().indexOf(c) != m.getCartasMesa().size()-1){
                //         mensaje += ":";
                //     }
                // }

                // out.write(mensaje);
                // out.newLine();


                String mensaje = "";
                mensaje += "terminarPartida:";
                out.write(mensaje);
                out.newLine();

                } catch (NoMasCartasException e) {
                    e.printStackTrace();
                } catch (JugadaIncorrectaException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
